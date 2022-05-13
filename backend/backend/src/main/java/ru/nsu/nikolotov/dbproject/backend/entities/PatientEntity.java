package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PatientEntity {
    private Integer id;
    private String name;
    private String diseaseHistory;
    private String operationsHistory;
    private String medsHistory;
}
