package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalWardFullInfoEntity {
    private Integer id;
    private String wardNumber;
    private Integer totalPlaces;
    private Integer departmentId;
    private String departmentName;
    private Integer hospitalId;
    private String hospitalName;
}
