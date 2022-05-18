--1

Select Doctors.id as doctorId, Doctors.name as doctorName, Hospitals.id as hospitalId, Hospitals.name as hospitalName, 
DoctorWorksAtHospital.salary, DoctorWorksAtHospital.contractStartDate, DoctorWorksAtHospital.contractEndDate, Doctors.vacationStart, 
Doctors.vacationEnd, Doctors.salaryCoefficient
from (Dentists INNER JOIN DoctorWorksAtHospital on (Dentists.id = DoctorWorksAtHospital.doctorId) 
INNER JOIN Doctors on (Dentists.id = Doctors.id)) 
INNER JOIN Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id);

Select  Hospitals.id, Hospitals.name, count(*)
from (Dentists INNER JOIN DoctorWorksAtHospital on (Dentists.id = DoctorWorksAtHospital.doctorId) 
INNER JOIN Doctors on (Dentists.id = Doctors.id)) 
INNER JOIN Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id)
GROUP BY Hospitals.id;

Select Doctors.id as doctorId, Doctors.name as doctorName, Polyclinics.id as polyclinicId, Polyclinics.name as polyclinicName, 
DoctorWorksAtPolyclinic.salary, DoctorWorksAtPolyclinic.contractStartDate, 
DoctorWorksAtPolyclinic.contractEndDate, Doctors.vacationStart, 
Doctors.vacationEnd, Doctors.salaryCoefficient
from (Surgeons INNER JOIN DoctorWorksAtPolyclinic on (Surgeons.id = DoctorWorksAtPolyclinic.doctorId) 
INNER JOIN Doctors on (Surgeons.id = Doctors.id)) 
INNER JOIN Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id);


Select Polyclinics.id, Polyclinics.name, count(*)
from (Surgeons INNER JOIN DoctorWorksAtPolyclinic on (Surgeons.id = DoctorWorksAtPolyclinic.doctorId) 
INNER JOIN Doctors on (Surgeons.id = Doctors.id)) 
INNER JOIN Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id)
GROUP BY Polyclinics.id;



Select Polyclinics.id, Polyclinics.name, count(*)
from (Surgeons INNER JOIN DoctorWorksAtPolyclinic on (Surgeons.id = DoctorWorksAtPolyclinic.doctorId) 
INNER JOIN Doctors on (Surgeons.id = Doctors.id)) 
INNER JOIN Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id)
GROUP BY Polyclinics.id  
UNION
Select  Hospitals.id, Hospitals.name, count(*)
from (Dentists INNER JOIN DoctorWorksAtHospital on (Dentists.id = DoctorWorksAtHospital.doctorId) 
INNER JOIN Doctors on (Dentists.id = Doctors.id)) 
INNER JOIN Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id)
GROUP BY Hospitals.id ;

--2

Select Hospitals.id as institutionId, Hospitals.name as institutionName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient, 
ServiceStaffWorksAtHospital.salary, ServiceStaffWorksAtHospital.employmentDate, ServiceStaffWorksAtHospital.dismissalDate 
from 
(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions
where (ServiceStaffPositions.positionName = 'Медсестра')) as positions 
inner join ServiceStaffWorksAtHospital on (ServiceStaffWorksAtHospital.positionId = positions.positionId)
inner join Hospitals on (ServiceStaffWorksAtHospital.hospitalId = Hospitals.id)
inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtHospital.workerId);

Select Hospitals.id as hospitalId, Hospitals.name as hospitalName, count(*)
from 
(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions
where (ServiceStaffPositions.positionName = 'Медсестра')) as positions 
inner join ServiceStaffWorksAtHospital on (ServiceStaffWorksAtHospital.positionId = positions.positionId)
inner join Hospitals on (ServiceStaffWorksAtHospital.hospitalId = Hospitals.id)
inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtHospital.workerId)
GROUP BY Hospitals.id;



Select Polyclinics.id as institutionId, Polyclinics.name as institutionName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient, 
ServiceStaffWorksAtPolyclinic.salary, ServiceStaffWorksAtPolyclinic.employmentDate, ServiceStaffWorksAtPolyclinic.dismissalDate 
from 
(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions
where (ServiceStaffPositions.positionName = 'Медсестра')) as positions 
inner join ServiceStaffWorksAtPolyclinic on (ServiceStaffWorksAtPolyclinic.positionId = positions.positionId)
inner join Polyclinics on (ServiceStaffWorksAtPolyclinic.polyclinicId = Polyclinics.id)
inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtPolyclinic.workerId);


Select Polyclinics.id as polyclinicId, Polyclinics.name as polyclinicName, count(*)
from 
(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions
where (ServiceStaffPositions.positionName = 'Медсестра')) as positions 
inner join ServiceStaffWorksAtPolyclinic on (ServiceStaffWorksAtPolyclinic.positionId = positions.positionId)
inner join Polyclinics on (ServiceStaffWorksAtPolyclinic.polyclinicId = Polyclinics.id)
inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtPolyclinic.workerId)
GROUP BY Polyclinics.id;

Select Hospitals.id, Hospitals.name, count(*)
from 
(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions
where (ServiceStaffPositions.positionName = 'Медсестра')) as positions 
inner join ServiceStaffWorksAtHospital on (ServiceStaffWorksAtHospital.positionId = positions.positionId)
inner join Hospitals on (ServiceStaffWorksAtHospital.hospitalId = Hospitals.id)
inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtHospital.workerId)
GROUP BY Hospitals.id
UNION
Select Polyclinics.id, Polyclinics.name, count(*)
from 
(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions
where (ServiceStaffPositions.positionName = 'Медсестра')) as positions 
inner join ServiceStaffWorksAtPolyclinic on (ServiceStaffWorksAtPolyclinic.positionId = positions.positionId)
inner join Polyclinics on (ServiceStaffWorksAtPolyclinic.polyclinicId = Polyclinics.id)
inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtPolyclinic.workerId)
GROUP BY Polyclinics.id;

--3

Select Doctors.id, Doctors.name from DoneOperationsForHospitals 
INNER JOIN (Select Hospitals.id as id from Hospitals where (name = 'Городская больница №2')) as t_hospitals on (DoneOperationsForHospitals.hospitalId = t_hospitals.id) 
inner join Doctors on (DoneOperationsForHospitals.doctorId = Doctors.id)
group by Doctors.id
Having count(*) >= 3;


--4

Select Doctors.id, Doctors.name, sum(dismissaldate - employmentdate) as workDays 
from
Doctors inner join DoctorWorkedAtHospital on (Doctors.id = DoctorWorkedAtHospital.doctorId)
inner join Hospitals on (Hospitals.id = DoctorWorkedAtHospital.hospitalId)
where Hospitals.name = 'Городская больница №1'
group by Doctors.id;



--5

Select Doctors.id, Doctors.name from
Doctors 
inner join Docents on (Doctors.id = Docents.id) 
inner join DoctorWorksAtHospital on (DoctorWorksAtHospital.doctorId = Doctors.id) 
inner join Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id)
Where (Hospitals.name = 'Городская больница №2');

--6

--for ward

Select Patients.id as patientId, Patients.name as patientName, Doctors.id as doctorid, Doctors.name as doctorName, PatientTreatsInHospital.admissionDate ,PatientTreatsInHospital.admissionTemperature 
from
PatientTreatsInHospital inner join HospitalWards on (HospitalWards.id = PatientTreatsInHospital.wardId) 
inner join HospitalDepartments on (HospitalDepartments.id = HospitalWards.departmentId)
inner join Hospitals on (HospitalDepartments.hospitalId = Hospitals.id) 
inner join Patients on (Patients.id = PatientTreatsInHospital.patientId)
inner join Doctors on (PatientTreatsInHospital.doctorId = Doctors.id)
Where (HospitalWards.wardNumber = '1') and (HospitalDepartments.name = 'Инфекционное отделение') and (Hospitals.name = 'Городская больница №1');

--Добавить название больницы в where

--7


Select Patients.id, Patients.name, PatientTreatedInHospital.admissionDate, PatientTreatedInHospital.dateOfRecovery
from 
Patients inner join PatientTreatedInHospital on (Patients.id = PatientTreatedInHospital.patientId)
where (Hospitals.id = 1) and (PatientTreatedInHospital.admissionDate >= '15-Dec-2020') and (PatientTreatedInHospital.dateOfRecovery <= '28-Jan-2020');

--Перед показом добавить пациентов в таблицу PatientTreatedInHospital

--8

Select Patients.id, Patients.name as patientName, Doctors.name as doctorName, currentDisease 
from Patients inner join PatientTreatsInPolyclinic on (Patients.id = PatientTreatsInPolyclinic.patientId)
inner join Doctors on (Doctors.id = PatientTreatsInPolyclinic.doctorId)
inner join Dentists on (Doctors.id = Dentists.id);

--9

Select HospitalWards.id, HospitalWards.wardNumber, HospitalWards.totalPlaces, count(PatientTreatsInHospital.wardId) as busyPlaces, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces
from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)
group by HospitalWards.id;


-- ^ получение списка палат и их состояния

Select * from (
Select HospitalWards.id, HospitalWards.wardNumber, HospitalWards.totalPlaces, count(PatientTreatsInHospital.wardId) as busyPlaces, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces
from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)
group by HospitalWards.id) as wards where wards.freePlaces = wards.totalPlaces;

-- полностью свободные палаты


select HospitalDepartments.id, HospitalDepartments.name, sum(totalPlaces), sum(freePlaces) from
(Select HospitalWards.id, HospitalWards.departmentId, HospitalWards.wardNumber, 
	HospitalWards.totalPlaces, count(PatientTreatsInHospital.wardId) as busyPlaces, totalPlaces - count(PatientTreatsInHospital.wardId) as freePlaces
from HospitalWards left join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId)
group by HospitalWards.id) as wards
inner join HospitalDepartments on (wards.departmentId = HospitalDepartments.id)
group by HospitalDepartments.id;


--10

Select Polyclinics.id, Polyclinics.name, count(*) as count
from Polyclinics inner join PolyclinicCabinets on (Polyclinics.id = PolyclinicCabinets.polyclinicId)
group by Polyclinics.id;


Select Polyclinics.id, Polyclinics.name, PolyclinicCabinets.id, PolyclinicCabinets.cabinetName, count(*)
from Polyclinics inner join PolyclinicCabinets on (Polyclinics.id = PolyclinicCabinets.polyclinicId)
left join PatientsVisitPolyclinicCabinets on (PolyclinicCabinets.id = patientsvisitpolycliniccabinets.cabinetid)
where dateOfVisit >= '2022-03-14' and dateOfVisit <= '2022-03-27'
group by PolyclinicCabinets.id, Polyclinics.id;

--11

Select Doctors.id, Doctors.name, count(*)*1.0 / ( CAST(('27-Mar-2022') as DATE) - CAST(('14-Mar-2022') as DATE) + 1) as avaregeVisits
from Doctors inner join PatientsVisitPolyclinicCabinets on (Doctors.id = PatientsVisitPolyclinicCabinets.doctorId)
where dateOfVisit >= '14-Mar-2022' and dateOfVisit <= '27-Mar-2022'
group by Doctors.id;

--12

Select Doctors.id, Doctors.name, count(PatientTreatsInHospital.patientId)
from Doctors left join PatientTreatsInHospital on (Doctors.id = PatientTreatsInHospital.doctorId)
group by Doctors.id;

--13

Select Patients.id, Patients.name, DoneOperationsForHospitals.operationName
from Patients inner join DoneOperationsForHospitals on (Patients.id = DoneOperationsForHospitals.patientId);

--14

select Laboratories.id, Laboratories.name, count(*)*1.0 / ( CAST(('2020-01-23') as DATE) - CAST(('2020-01-21') as DATE) + 1) as averageServices  
from BiologicalLaboratoryServicesHospital inner join Laboratories on (BiologicalLaboratoryServicesHospital.laboratoryId = Laboratories.id)
inner join Hospitals on (BiologicalLaboratoryServicesHospital.hospitalId = Hospitals.id)
where Hospitals.id = 1 and BiologicalLaboratoryServicesHospital.serviceDate >= '2020-01-21' 
and  BiologicalLaboratoryServicesHospital.serviceDate <= '2020-01-23'
group by Laboratories.id;

