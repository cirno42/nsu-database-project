package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorWorksAtHospitalFullInfoEntity {
    private Integer doctorId;
    private String doctorName;
    private Integer hospitalId;
    private String hospitalName;
    private Integer salary;
    private Date contractStartDate;
    private Date contractEndDate;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;
}
