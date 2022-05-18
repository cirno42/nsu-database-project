package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LaboratoryServiceStatisticEntity {
    private Integer id;
    private String name;
    private Double averageServices;
}
