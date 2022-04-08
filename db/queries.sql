--1

Select Doctors.id as doctorId, Doctors.name as doctorName, Hospitals.id as hospitalId, Hospitals.name as hospitalName, DoctorWorksAtHospital.salary, DoctorWorksAtHospital.contractStartDate, DoctorWorksAtHospital.contractEndDate, Doctors.vacationStart, 
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

Select Hospitals.id as hospitalId, Hospitals.name as hospitalName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient, 
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



Select Polyclinics.id as polyclinicId, Polyclinics.name as polyclinicName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient, 
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
Having count(*) > 3;


--4


Select Doctors.name, (DoctorWorkedAtHospital.dismissalDate - DoctorWorkedAtHospital.employmentDate) as dateDiff from
Doctors inner join DoctorWorkedAtHospital on (Doctors.id = DoctorWorkedAtHospital.doctorId) inner join Hospitals on (DoctorWorkedAtHospital.hospitalId = Hospitals.id)
Where (Hospitals.name = 'Городская больница №2') and ((DoctorWorkedAtHospital.dismissalDate - DoctorWorkedAtHospital.employmentDate) > 365);


--5

Select Doctors.name from
Doctors 
inner join Docents on (Doctors.id = Docents.id) 
inner join DoctorWorksAtHospital on (DoctorWorksAtHospital.doctorId = Doctors.id) 
inner join Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id)
Where (Hospitals.name = 'Городская больница №2');

--6

--for ward

Select Patients.name as patientName, Doctors.name as doctorName, PatientTreatsInHospital.admissionDate ,PatientTreatsInHospital.admissionTemperature 
from
PatientTreatsInHospital inner join HospitalWards on (HospitalWards.id = PatientTreatsInHospital.wardId) 
inner join HospitalDepartments on (HospitalDepartments.id = HospitalWards.departmentId) 
inner join Patients on (Patients.id = PatientTreatsInHospital.patientId)
inner join Doctors on (PatientTreatsInHospital.doctorId = Doctors.id)
Where (dateOfRecovery IS NULL) and (HospitalWards.wardNumber = '1') and (HospitalDepartments.name = 'Инфекционное отделение');

--Добавить название больницы в where

--7


Select Patients.name, PatientTreatsInHospital.admissionDate, PatientTreatsInHospital.dateOfRecovery
from 
Patients inner join PatientTreatsInHospital on (Patients.id = PatientTreatsInHospital.patientId)
inner join HospitalWards on (PatientTreatsInHospital.wardId = HospitalWards.id)
inner join HospitalDepartments on (HospitalWards.departmentId = HospitalDepartments.id)
inner join Hospitals on (HospitalDepartments.hospitalId = Hospitals.id)
where (Hospitals.name = 'Городская больница №1') and (PatientTreatsInHospital.admissionDate >= '15-Dec-2020') and (PatientTreatsInHospital.dateOfRecovery <= '28-Jan-2020');

--8

--9

Select Hospitals.name, count(*)
