package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class PatientTreatsInHospitalFullInfoEntity {
    private Integer patientId;
    private String patientName;
    private String doctorName;
    private String hospitalName;
    private String departmentName;
    private String wardNumber;
    private Date admissionDate;
    private Date dateOfRecovery;
    private Double admissionTemperature;
    private String currentDisease;
    private String currentOperation;
    private String currentMeds;
}
