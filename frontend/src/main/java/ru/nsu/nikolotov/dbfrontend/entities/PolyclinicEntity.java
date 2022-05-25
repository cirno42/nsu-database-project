package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PolyclinicEntity {
    Integer id;
    String name;

    @Override
    public String toString() {
        return String.valueOf(id) + " " + name;
    }
}
