package ru.nsu.nikolotov.dbproject.backend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorExperienceEntity {
    Integer id;
    String name;
    Integer workDays;
}
