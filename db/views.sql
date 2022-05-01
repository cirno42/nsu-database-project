CREATE VIEW PolyclinicsHistory as (

Select PatientTreatedInPolyclinic.patientId, PatientTreatedInPolyclinic.doctorId, PatientTreatedInPolyclinic.polyclinicId,
 PatientTreatedInPolyclinic.admissionDate, PatientTreatedInPolyclinic.dateOfRecovery, 
 PatientTreatedInPolyclinic.currentDisease, PatientTreatedInPolyclinic.currentOperation, PatientTreatedInPolyclinic.currentMeds
from PatientTreatedInPolyclinic
UNION
Select PatientTreatsInPolyclinic.patientId, PatientTreatsInPolyclinic.doctorId, DoctorWorksAtPolyclinic.polyclinicId,
 PatientTreatsInPolyclinic.admissionDate, PatientTreatsInPolyclinic.dateOfRecovery, 
 PatientTreatsInPolyclinic.currentDisease, PatientTreatsInPolyclinic.currentOperation, PatientTreatsInPolyclinic.currentMeds
 from PatientTreatsInPolyclinic inner join DoctorWorksAtPolyclinic on (PatientTreatsInPolyclinic.doctorId = DoctorWorksAtPolyclinic.doctorId)
 );

CREATE VIEW HospitalsHistory as (
Select    PatientTreatedInHospital.patientId, PatientTreatedInHospital.doctorId, PatientTreatedInHospital.wardId,
    PatientTreatedInHospital.admissionDate, PatientTreatedInHospital.dateOfRecovery, 
    PatientTreatedInHospital.currentDisease, PatientTreatedInHospital.currentOperation, PatientTreatedInHospital.currentMeds
    from PatientTreatedInHospital
UNION
Select PatientTreatsInHospital.patientId, PatientTreatsInHospital.doctorId, PatientTreatsInHospital.wardId,
 PatientTreatsInHospital.admissionDate, PatientTreatsInHospital.dateOfRecovery, 
 PatientTreatsInHospital.currentDisease, PatientTreatsInHospital.currentOperation, PatientTreatsInHospital.currentMeds
 from PatientTreatsInHospital
);