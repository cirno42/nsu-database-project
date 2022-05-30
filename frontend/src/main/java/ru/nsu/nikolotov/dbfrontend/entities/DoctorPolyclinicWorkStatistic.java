package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorPolyclinicWorkStatistic implements EntityForInsertIntoJTable{
    private Integer id;
    private String name;
    private Double averageVisits;

    @Override
    public String[] getHeaders() {
        return new String[] {"ID", "Name", "Average visits"};
    }

    @Override
    public String[] getValues() {
        return new String[] {id.toString(), name, averageVisits.toString()};
    }
}
