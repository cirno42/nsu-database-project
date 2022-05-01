CREATE OR REPLACE FUNCTION FinishPatientTreatmentInHospital(patientToDeleteId integer) RETURNS void
LANGUAGE plpgsql
AS $$
DECLARE
patientRecord record;
dateOfRecovery date;
wardId integer;
admissionTemperature double precision;
thisDoctorId int;
admissionDate date;
currentDisease varchar(256);
currentOperation varchar(256);
currentMeds varchar(256);
BEGIN
	Select * into patientRecord from PatientTreatsInHospital where PatientTreatsInHospital.patientId = patientToDeleteId;
	IF (patientRecord IS NULL) THEN 
		RAISE EXCEPTION 'No such patient treats now in hospital';
	END IF;
	dateOfRecovery = COALESCE(patientRecord.dateOfRecovery, now());
	thisDoctorId = patientRecord.doctorId;
	admissionDate = patientRecord.admissionDate;
	admissionTemperature = patientRecord.admissionTemperature;
	currentDisease = patientRecord.currentDisease;
	currentOperation = patientRecord.currentOperation;
	currentMeds = patientRecord.currentMeds;
	wardId = patientRecord.wardId;

	insert into PatientTreatedInHospital(patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds)
		values(patientToDeleteId, thisDoctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds);

	delete from PatientTreatsInHospital where PatientTreatsInHospital.patientId = patientToDeleteId;

	update Patients SET diseaseHistory = (diseaseHistory || ';') || COALESCE(currentDisease, ''), operationsHistory = (diseaseHistory || ';')|| COALESCE(currentDisease, ''), 
		medsHistory = (medsHistory || ';') || COALESCE(currentMeds, '')
		where id = patientToDeleteId;
END
$$;

CREATE OR REPLACE FUNCTION FinishPatientTreatmentInPolyclinic(patientToDeleteId integer) RETURNS void
LANGUAGE plpgsql
AS $$
DECLARE
patientRecord record;
dateOfRecovery date;
polyclinicId integer;
thisDoctorId int;
admissionDate date;
currentDisease varchar(256);
currentOperation varchar(256);
currentMeds varchar(256);
BEGIN
	Select * into patientRecord from PatientTreatsInPolyclinic where PatientTreatsInPolyclinic.patientId = patientToDeleteId;
	IF (patientRecord IS NULL) THEN 
		RAISE EXCEPTION 'No such patient treats now in polyclinic';
	END IF;
	dateOfRecovery = COALESCE(patientRecord.dateOfRecovery, now());
	thisDoctorId = patientRecord.doctorId;
	admissionDate = patientRecord.admissionDate;
	currentDisease = patientRecord.currentDisease;
	currentOperation = patientRecord.currentOperation;
	currentMeds = patientRecord.currentMeds;

	select DoctorWorksAtPolyclinic.polyclinicId into polyclinicId from DoctorWorksAtPolyclinic where (thisDoctorId = doctorId);

	insert into PatientTreatedInPolyclinic(patientId, doctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds)
		values(patientToDeleteId, thisDoctorId, polyclinicId, admissionDate, dateOfRecovery, currentDisease, currentOperation, currentMeds);

	delete from PatientTreatsInPolyclinic where PatientTreatsInPolyclinic.patientId = patientToDeleteId;

	update Patients SET diseaseHistory = (diseaseHistory || ';') || COALESCE(currentDisease, ''), operationsHistory = (diseaseHistory || ';')|| COALESCE(currentDisease, ''), 
		medsHistory = (medsHistory || ';') || COALESCE(currentMeds, '')
		where id = patientToDeleteId;
END
$$;

CREATE OR REPLACE FUNCTION FireDoctorFromHospital(doctorToFireId integer) RETURNS void 
LANGUAGE plpgsql
AS $$
DECLARE
patientOfDoctor record;
BEGIN

	FOR patientOfDoctor in 
	(Select PatientTreatsInHospital.patientId from PatientTreatsInHospital where PatientTreatsInHospital.doctorId = doctorToFireId)
	LOOP
		RAISE NOTICE 'Patient with id % finished treatment beacause his doctor was fired', patientOfDoctor.patientId;
		PERFORM FinishPatientTreatmentInHospital(patientOfDoctor.patientId);
	END LOOP;
	DELETE from DoctorWorksAtHospital where DoctorWorksAtHospital.doctorId = doctorToFireId;
END
$$;

CREATE OR REPLACE FUNCTION FireDoctorFromPolyclinic(doctorToFireId integer) RETURNS void 
LANGUAGE plpgsql
AS $$
DECLARE
patientOfDoctor record;
BEGIN

	FOR patientOfDoctor in 
	(Select PatientTreatsInPolyclinic.patientId from PatientTreatsInPolyclinic where PatientTreatsInPolyclinic.doctorId = doctorToFireId)
	LOOP
		RAISE NOTICE 'Patient with id % finished treatment beacause his doctor was fired', patientOfDoctor.patientId;
		PERFORM FinishPatientTreatmentInPolyclinic(patientOfDoctor.patientId);
	END LOOP;
	DELETE from DoctorWorksAtPolyclinic where DoctorWorksAtPolyclinic.doctorId = doctorToFireId;
END
$$;

CREATE OR REPLACE FUNCTION CheckIfWardIsFull() RETURNS TRIGGER 
LANGUAGE plpgsql
AS $$ 
DECLARE
busyPlaces integer;
wardTotalPlaces integer;
BEGIN
	SELECT totalPlaces into wardTotalPlaces from HospitalWards where NEW.wardId = HospitalWards.id;

	Select count(*) into busyPlaces from 
		(SELECT HospitalWards.id from  HospitalWards inner join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId) where wardId = NEW.wardId) as wards;
	RAISE NOTICE  'Total: %', wardTotalPlaces ;
	RAISE NOTICE 'Busy: %', busyPlaces ; 
	IF ((wardTotalPlaces - busyPlaces) = 0) THEN
		RAISE EXCEPTION 'This ward is full now';
	END IF;
	RETURN NEW;
END; 
$$;

CREATE OR REPLACE TRIGGER CheckIfWardIsFullOnInsertNewPatientTrigger 
	BEFORE INSERT ON PatientTreatsInHospital 
	FOR EACH ROW
	EXECUTE PROCEDURE CheckIfWardIsFull();


CREATE OR REPLACE TRIGGER CheckIfWardIsFullOnUpdatePatientTrigger 
	BEFORE UPDATE ON PatientTreatsInHospital 
	FOR EACH ROW
	WHEN (OLD.wardId != NEW.wardId)
	EXECUTE PROCEDURE CheckIfWardIsFull();





UPDATE PatientTreatsInHospital SET wardId = 1 where patientId =;

insert into PatientTreatsInHospital (patientId, doctorId, wardId, admissionDate, dateOfRecovery, admissionTemperature, currentDisease, currentOperation, currentMeds) values (39, 10, 2, '22-Dec-2020', NULL, 36.6, 'Радиологическое обследование в области 3.1', NULL, NULL);

SELECT count(*) from  HospitalWards inner join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId) group by HospitalWards.id;

Select count(*) from (SELECT HospitalWards.id from  HospitalWards inner join PatientTreatsInHospital on (HospitalWards.id = PatientTreatsInHospital.wardId) where wardId = 2) as wards;
