package ru.nsu.nikolotov.dbproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorTreatsPatientEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PatientEntity;
import ru.nsu.nikolotov.dbproject.backend.services.PatientService;

import java.util.List;

@RestController
@RequestMapping("api/v1/patients")
public class PatientController {
    @Autowired
    PatientService patientService;

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
    List<DoctorTreatsPatientEntity> getPatientsWhoTreatsInSuchPlace(
            @RequestParam(required = false) Integer wardId,
            @RequestParam(required = false) Integer departmentId,
            @RequestParam(required = false) Integer hospitalId) {

        return patientService.getPatientsWhoTreatsInSuchPlace(hospitalId, departmentId, wardId);
    }
}
