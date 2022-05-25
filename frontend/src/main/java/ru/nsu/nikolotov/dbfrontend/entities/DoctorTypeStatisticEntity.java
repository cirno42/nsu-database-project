package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DoctorTypeStatisticEntity implements EntityForInsertIntoJTable {
    private String type;
    private Integer count;

    @Override
    public String[] getHeaders() {
        return new String[] {"Type", "Count"};
    }

    @Override
    public String[] getValues() {
        return new String[] {type, count.toString()};
    }
}
