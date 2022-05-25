package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorTreatsPatientEntity {
    private Integer patientId;
    private String patientName;
    private Integer doctorId;
    private String doctorName;
    private Date admissionDate;
    private Double admissionTemperature;
}
