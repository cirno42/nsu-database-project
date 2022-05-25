package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalWardEntity {
    private Integer id;
    private String wardNumber;
    private Integer totalPlaces;
    private Integer departmentId;
}
