package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;

import java.util.Date;

@Setter
@Getter
public class DoctorEntity {
    private Integer id;
    private String name;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;
    private DoctorType doctorType;
    private DoctorSciencePosition doctorSciencePosition;
    private DoctorScienceRank doctorScienceRank;
    private Boolean canDoOperation;
}
