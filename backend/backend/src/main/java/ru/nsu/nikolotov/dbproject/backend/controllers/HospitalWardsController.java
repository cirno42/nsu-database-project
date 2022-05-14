package ru.nsu.nikolotov.dbproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalWardEntity;
import ru.nsu.nikolotov.dbproject.backend.services.HospitalWardService;

@RestController
@RequestMapping("api/v1")
public class HospitalWardsController {
    @Autowired
    HospitalWardService service;

    @GetMapping
    public ResponseEntity<HospitalWardEntity> getWardById(@RequestParam int id) {
        var entity = service.getById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping(path = "next")
    public ResponseEntity<HospitalWardEntity> getNext(@RequestParam int id) {
        var entity = service.getNextById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<HospitalWardEntity> createWard(@RequestBody HospitalWardEntity entity) {
        var created = service.create(entity);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void updateWard(@RequestBody HospitalWardEntity entity) {
        service.update(entity);
    }

    @DeleteMapping
    public void deleteWard(@RequestParam int id) {
        service.delete(id);
    }
}
