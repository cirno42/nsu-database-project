package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorStatisticEntity implements EntityForInsertIntoJTable{
    private Integer id;
    private String name;
    private Integer count;

    @Override
    public String[] getHeaders() {
        return new String[0];
    }

    @Override
    public String[] getValues() {
        return new String[0];
    }
}
