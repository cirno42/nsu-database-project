package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceStaffPositionEntity {
    private Integer id;
    private String positionName;
    private Double salaryCoefficient;
}
