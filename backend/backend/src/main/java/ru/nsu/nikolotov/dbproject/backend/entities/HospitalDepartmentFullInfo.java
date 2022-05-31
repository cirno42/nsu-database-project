package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalDepartmentFullInfo {
    private Integer id;
    private String name;
    private Integer diseaseGroupSpecializationId;
    private String diseaseGroupSpecializationName;
    private Integer hospitalId;
    private String hospitalName;
}
