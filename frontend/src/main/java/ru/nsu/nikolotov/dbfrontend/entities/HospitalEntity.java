package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HospitalEntity {
    private int id;
    private String name;

    @Override
    public String toString() {
        return String.valueOf(id) + " " + name;
    }
}
