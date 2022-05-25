package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PatientTreatsInPolyclinicEntity {
    private Integer patientId;
    private String patientName;
    private Integer doctorId;
    private String doctorName;
    private String currentDisease;
    private String currentMeds;
    private String currentOperation;
    private String admissionDate;
    private String dateOfRecovery;
}
