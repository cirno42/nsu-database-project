package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicEntity;
import ru.nsu.nikolotov.dbproject.backend.services.PolyclinicService;

import java.util.List;

@RestController
@RequestMapping("api/v1/polyclinics")
public class PolyclinicController {
    @Autowired
    PolyclinicService service;

    @GetMapping
    public ResponseEntity<PolyclinicEntity> getById(@RequestParam int id) {
        var entity = service.getById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping(path = "getall")
    public List<PolyclinicEntity> getAll() {
        return service.getAll();
    }

    @GetMapping(path = "next")
    public ResponseEntity<PolyclinicEntity> getNext(@RequestParam int id) {
        var entity = service.getNextById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PolyclinicEntity> create(@RequestBody PolyclinicEntity entity) {
        var created = service.create(entity);
        if (created == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(created);
    }

    @PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    public void update(@RequestBody PolyclinicEntity entity) {
        service.update(entity);
    }

    @DeleteMapping
    public void delete(@RequestParam int id) {
        service.delete(id);
    }

    @GetMapping("cabinetscount")
    public DoctorStatisticEntity getPolyclinicCabinetsCount(@RequestParam Integer polyclinicId) {
        return service.getPolyclinicCabinetsCount(polyclinicId);
    }
}
