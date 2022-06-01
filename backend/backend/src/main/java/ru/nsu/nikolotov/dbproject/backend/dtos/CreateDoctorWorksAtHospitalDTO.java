package ru.nsu.nikolotov.dbproject.backend.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorWorksAtHospitalDTO {
    private Integer doctorId;
    private Integer hospitalId;
    private Integer salary;
    private String contractStartDate;
    private String contractEndDate;
}
