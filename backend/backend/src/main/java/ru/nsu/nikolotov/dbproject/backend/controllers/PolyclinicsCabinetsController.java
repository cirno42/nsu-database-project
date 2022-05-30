package ru.nsu.nikolotov.dbproject.backend.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicCabinetEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.PolyclinicCabinetVisitsCountEntity;
import ru.nsu.nikolotov.dbproject.backend.services.PolyclinicCabinetService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api/v1/polycliniccabinets")
public class PolyclinicsCabinetsController {
    @Autowired
    private PolyclinicCabinetService service;

    @GetMapping("visitscount")
    public List<PolyclinicCabinetVisitsCountEntity> getVisitStatistic(@RequestParam Integer polyclinicId,
                                                                      @RequestParam Date beginDate,
                                                                      @RequestParam Date endDate) {
        return service.getVisitStatistic(polyclinicId, beginDate, endDate);
    }

    @GetMapping("getall")
    public List<PolyclinicCabinetEntity> getAll() {
        return service.getAll();
    }
}
