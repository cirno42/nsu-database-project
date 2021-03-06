package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbproject.backend.services.DiseaseGroupService;
import ru.nsu.nikolotov.dbproject.backend.services.HospitalService;

@RestController
@RequestMapping("api/v1/deseasegroups")
public class DiseaseGroupsController {

    @Autowired
    DiseaseGroupService service;

    @GetMapping
    public ResponseEntity<DiseaseGroupEntity> getGroupById(@RequestParam int id) {
        var entity = service.getDiseaseGroupById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping(path = "next")
    public ResponseEntity<DiseaseGroupEntity> getNextGroup(@RequestParam int id) {
        var entity = service.getNextDiseaseGroupById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DiseaseGroupEntity> createGroup(@RequestBody DiseaseGroupEntity entity) {
        var created = service.createDiseaseGroup(entity);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateGroup(@RequestBody DiseaseGroupEntity entity) {
        service.updateDiseaseGroup(entity);
    }

    @DeleteMapping
    public void deleteGroup(@RequestParam int id) {
        service.deleteDiseaseGroup(id);
    }

}
