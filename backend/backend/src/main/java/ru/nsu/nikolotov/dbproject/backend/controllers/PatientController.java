package ru.nsu.nikolotov.dbproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreatePatientTreatsAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.*;
import ru.nsu.nikolotov.dbproject.backend.services.PatientService;
import ru.nsu.nikolotov.dbproject.backend.services.PatientTreatsAtHospitalService;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
    @Autowired
    private PatientService patientService;

    @Autowired
    private PatientTreatsAtHospitalService patientTreatsAtHospitalService;

    @GetMapping(path = "getall")
    public List<PatientEntity> getAll() {
        return patientService.getAll();
    }

    @GetMapping (path = "next")
    public ResponseEntity<PatientEntity> getNextPatientById(@RequestParam int id) {
        return ResponseEntity.ok(patientService.getNextPatientById(id));
    }

    @GetMapping
    public ResponseEntity<PatientEntity> getPatientById(@RequestParam int id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PatientEntity> createPatient(@RequestBody PatientEntity patient) { //переделать
        var createdPatient = patientService.createNewPatient(patient.getName(), patient.getDiseaseHistory(),
                patient.getOperationsHistory(), patient.getMedsHistory());
        if (createdPatient == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdPatient);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updatePatient(@RequestBody PatientEntity patient) {
        patientService.updatePatient(patient);
    }

    @DeleteMapping
    public void deletePatient(@RequestParam int id) {
        patientService.deletePatientById(id);
    }

    @GetMapping("patientswhotreatsinsuchplace")
    public List<DoctorTreatsPatientEntity> getPatientsWhoTreatsInSuchPlace(
            @RequestParam(required = false) Integer wardId,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Integer hospitalId) {

        return patientService.getPatientsWhoTreatsInSuchPlace(hospitalId, departmentId, wardId);
    }

    @GetMapping("patientstreatedbydoctor")
    public List<PatientTreatmentInfo> getPatientsTreatedByDoctorInHospital(
            @RequestParam Integer doctorId,
            @RequestParam Date admission,
            @RequestParam Date recovery) {
        return patientService.getPatientsTreatedByDoctorInHospital(doctorId, admission, recovery);
    }

    @GetMapping("patientstreatedinhospital")
    public List<PatientTreatmentInfo> getPatientsTreatedInHospital(@RequestParam Integer hospitalId,
                                                                   @RequestParam Date admission,
                                                                   @RequestParam Date recovery) {
        return patientService.getPatientsTreatedInHospital(hospitalId, admission, recovery);
    }

    @GetMapping ("patientstreatsinpolyclinic")
    public List<PatientTreatsInPolyclinicEntity> getPatientsTreatsInPolyclinic(@RequestParam Integer polyclinicId,
                                                                               @RequestParam String doctorType) {

       return patientService.getPatientsTreatsInPolyclinic(polyclinicId, DoctorType.fromString(doctorType));
    }

    @GetMapping("operations/hospital")
    public List<DoneOperationsForPatientEntity> getPatientsWhoHadOperationInHospital(@RequestParam Date beginDate,@RequestParam Date endDate,@RequestParam Integer hospitalId) {
        return patientService.getPatientWhoHadOperationsInHospital(beginDate, endDate, hospitalId);
    }

    @GetMapping("operations/polyclinic")
    public List<DoneOperationsForPatientEntity> getPatientsWhoHadOperationInPolyclinic(@RequestParam Date beginDate,@RequestParam Date endDate,@RequestParam Integer polyclinicId) {
        return patientService.getPatientWhoHadOperationsInPolyclinic(beginDate, endDate, polyclinicId);
    }

    @GetMapping("operations/doctor")
    public List<DoneOperationsForPatientEntity> getPatientWhoOperatedByDoctor(@RequestParam Date beginDate,@RequestParam Date endDate,@RequestParam Integer doctorId) {
        return patientService.getPatientWhoOperatedByDoctor(beginDate, endDate, doctorId);
    }

    @GetMapping("patienttreatsathospital/getall")
    public List<PatientTreatsInHospitalFullInfoEntity> getAllCurrentPatientsInHospital() {
        return patientTreatsAtHospitalService.getAllCurrentPatients();
    }

    @GetMapping("patienttreatedathospital/history")
    public List<PatientTreatsInHospitalFullInfoEntity> getAllHistoryPatientsInHospital() {
        return patientTreatsAtHospitalService.getHistoryOfPatients();
    }


    @PostMapping(path = "patienttreatsathospital/create", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addNewPatient(@RequestBody CreatePatientTreatsAtHospitalDTO dto) {
        patientService.addNewPatient(dto);
    }

    @GetMapping("patienttreatsatpolyclinic/getall")
    public List<PatientTreatsInPolyclinicEntity> getAllCurrentPatientsInPolyclinic() {
        return patientService.getPatientsTreatsInPolyclinic();
    }

    @GetMapping("patienttreatedatpolyclinic/history")
    public List<PatientTreatsInPolyclinicEntity> getHistoryInPolyclinic() {
        return patientService.getPatientsHistory();
    }

    @DeleteMapping("finish/polyclinic")
    public void finishPatientTreatmentInPolyclinic(@RequestParam Integer id) {
        patientService.finishPatientTreatmentInPolyclinic(id);
    }

    @DeleteMapping("finish/hospital")
    public void finishPatientTreatmentInHospital(@RequestParam Integer id) {
        patientService.finishPatientTreatmentInHospital(id);
    }
}
