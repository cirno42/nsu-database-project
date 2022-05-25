package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolyclinicCabinetVisitsCountEntity {
    Integer polyclinicId;
    String polyclinicName;
    Integer cabinetId;
    String cabinetName;
    Integer count;
}
