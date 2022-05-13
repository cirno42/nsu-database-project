package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbproject.backend.services.HospitalService;

@RestController
@RequestMapping("api/v1/hospitals")
public class HospitalController {
    @Autowired
    HospitalService hospitalService;

    @GetMapping
    public ResponseEntity<HospitalEntity> getHospitalById(@RequestParam int id) {
        var entity = hospitalService.getHospitalById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping(path = "next")
    public ResponseEntity<HospitalEntity> getNextHospital(@RequestParam int id) {
        var entity = hospitalService.getNextHospital(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalEntity> createNewHospital(@RequestBody HospitalEntity hospitalEntity) {
        var createdHospital = hospitalService.createNewHospital(hospitalEntity.getName());
        if (createdHospital == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(createdHospital);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateHospital(@RequestBody HospitalEntity hospitalEntity) {
        hospitalService.updateHospital(hospitalEntity);
    }

    @DeleteMapping
    public void deleteHospital(@RequestParam int id) {
        hospitalService.deleteHospital(id);
    }
}
