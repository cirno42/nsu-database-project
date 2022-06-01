package ru.nsu.nikolotov.dbfrontend.dtos;


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
