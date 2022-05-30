package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LaboratoryServiceStatisticEntity implements EntityForInsertIntoJTable{
    private Integer id;
    private String name;
    private Double averageServices;

    @Override
    public String[] getHeaders() {
        return new String[] {"ID", "Name", "Average services"};
    }

    @Override
    public String[] getValues() {
        return new String[] {id.toString(), name, averageServices.toString()};
    }
}
