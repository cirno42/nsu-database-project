package ru.nsu.nikolotov.dbproject.backend.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDoctorWorksAtPolyclinicDTO {
    private Integer doctorId;
    private Integer polyclinicId;
    private Integer salary;
    private String contractStartDate;
    private String contractEndDate;
}
