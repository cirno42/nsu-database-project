package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbproject.backend.services.HospitalDepartmentService;

@RestController
@RequestMapping("api/v1/hospitaldepartments")
public class HospitalDepartmentController {
    @Autowired
    HospitalDepartmentService service;

    @GetMapping
    public ResponseEntity<HospitalDepartmentEntity> getDepartmentById(@RequestParam int id) {
        var entity = service.getHospitalDepartmentById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping(path = "next")
    public ResponseEntity<HospitalDepartmentEntity> getNextDepartmentById(@RequestParam int id) {
        var entity = service.getNextHospitalDepartmentById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalDepartmentEntity> createNewDepartment(@RequestBody HospitalDepartmentEntity department) {
        var entity = service.createHospitalDepartment(department);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateDepartment(@RequestBody HospitalDepartmentEntity department){
        service.updateHospitalDepartment(department);
    }

    @DeleteMapping
    public void deleteDepartment(@RequestParam int id) {
        service.deleteHospitalDepartment(id);
    }
}
