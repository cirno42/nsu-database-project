package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientTreatmentInfo {
    private Integer id;
    private String name;
    private String diseaseHistory;
    private String operationsHistory;
    private String medsHistory;
    private Date admissionDate;
    private Date dateOfRecovery;
}
