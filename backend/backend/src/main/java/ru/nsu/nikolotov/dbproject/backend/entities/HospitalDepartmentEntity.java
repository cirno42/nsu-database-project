package ru.nsu.nikolotov.dbproject.backend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalDepartmentEntity {
    Integer id;
    Integer diseaseGroupSpecializationId;
    String name;
    Integer hospitalId;
}
