package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PolyclinicCabinetEntity {
    private Integer cabinetId;
    private Integer polyclinicId;
    private String cabinetName;
    private String polyclinicName;
}
