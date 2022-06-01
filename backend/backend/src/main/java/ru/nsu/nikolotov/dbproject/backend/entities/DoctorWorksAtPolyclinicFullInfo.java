package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorWorksAtPolyclinicFullInfo {
    private Integer doctorId;
    private String doctorName;
    private Integer polyclinicId;
    private String polyclinicName;
    private Integer salary;
    private Date contractStartDate;
    private Date contractEndDate;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;
}
