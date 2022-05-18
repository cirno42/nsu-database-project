package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorStatisticEntity {
    private Integer id;
    private String name;
    private Integer count;

}
