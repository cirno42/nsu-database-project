package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ServiceStaffInfoEntity {
    private Integer institutionId;
    private String institutionName;
    private String workerName;
    private String position;
    private Double salaryCoefficient;
    private Integer salary;
    private Date employmentDate;
    private Date dismissalDate;
}
