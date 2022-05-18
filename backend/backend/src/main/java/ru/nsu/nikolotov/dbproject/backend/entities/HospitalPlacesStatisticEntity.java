package ru.nsu.nikolotov.dbproject.backend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HospitalPlacesStatisticEntity {
    private Integer id;
    private String name;
    private Integer count;
}
