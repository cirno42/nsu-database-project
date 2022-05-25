package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorPolyclinicWorkStatistic {
    private Integer id;
    private String name;
    private Double averageVisits;
}
