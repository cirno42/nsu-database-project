package ru.nsu.nikolotov.dbproject.backend.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import ru.nsu.nikolotov.dbproject.backend.entities.DiseaseGroupEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalPlacesStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalWardEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.HospitalWardFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.services.HospitalWardService;

import java.util.List;

@RestController
@RequestMapping("api/v1/hospitalwards")
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

    @GetMapping(path = "getall")
    public List<HospitalWardFullInfoEntity> getAll() {
        var all = service.getAll();
        return all;
    }

    @GetMapping(path = "next")
    public ResponseEntity<HospitalWardEntity> getNext(@RequestParam int id) {
        var entity = service.getNextById(id);
        if (entity == null) {
            return ResponseEntity.badRequest().build();
        }
        return ResponseEntity.ok(entity);
    }

    @GetMapping("indepartment")
    public List<HospitalWardEntity> getWardsInDepartment(@RequestParam Integer departmentId) {
        return service.getWardsInDepartment(departmentId);
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

    @GetMapping("statistic/all/wards/hospital")
    public List<HospitalPlacesStatisticEntity> getCountOfWardsInHospital(@RequestParam Integer id) {
        return service.getCountOfWardsInHospital(id);
    }

    @GetMapping("statistic/all/wards/department")
    public List<HospitalPlacesStatisticEntity> getCountOfWardsInDepartment(@RequestParam Integer id) {
        return service.getCountOfWardsInDepartment(id);
    }

    @GetMapping("statistic/all/places/hospital")
    public List<HospitalPlacesStatisticEntity> getCountOfPlacesInHospital(@RequestParam Integer id) {
        return service.getCountOfPlacesInHospital(id);
    }

    @GetMapping("statistic/all/places/department")
    public List<HospitalPlacesStatisticEntity> getCountOfPlacesInDepartment(@RequestParam Integer id) {
        return service.getCountOfPlacesInDepartment(id);
    }

    @GetMapping("statistic/free/wards/hospital")
    public List<HospitalPlacesStatisticEntity> getCountOfFreeWardsInHospital(@RequestParam Integer id) {
        return service.getCountOfFreeWardsInHospital(id);
    }

    @GetMapping("statistic/free/wards/department")
    public List<HospitalPlacesStatisticEntity> getCountOfFreeWardsInDepartment(@RequestParam Integer id) {
        return service.getCountOfFreeWardsInDepartment(id);
    }

    @GetMapping("statistic/free/places/hospital")
    public List<HospitalPlacesStatisticEntity> getCountOfFreePlacesInHospital(@RequestParam Integer id) {
        return service.getCountOfFreePlacesInHospital(id);
    }

    @GetMapping("statistic/free/places/department")
    public List<HospitalPlacesStatisticEntity> getCountOfFreePlacesInDepartment(@RequestParam Integer id) {
        return service.getCountOfFreePlacesInDepartment(id);
    }
}
