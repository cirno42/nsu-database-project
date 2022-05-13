package ru.nsu.nikolotov.dbproject.backend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalDepartmentEntity {
    Integer id;
    Integer diseaseGroupId;
    String name;
    Integer hospitalId;
}
