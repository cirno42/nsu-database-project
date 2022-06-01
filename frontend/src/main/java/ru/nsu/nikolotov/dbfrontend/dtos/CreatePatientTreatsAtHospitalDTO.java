package ru.nsu.nikolotov.dbfrontend.dtos;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CreatePatientTreatsAtHospitalDTO {
    private Integer patientId;
    private Integer doctorId;
    private Integer wardId;
    private String admissionDate;
    private String dateOfRecovery;
    private Double admissionTemperature;
    private String currentDisease;
    private String currentOperation;
    private String currentMeds;
}
