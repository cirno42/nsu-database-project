package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ServiceStaffTypeStatistic implements EntityForInsertIntoJTable{
    String type;
    Integer count;

    @Override
    public String[] getHeaders() {
        return new String[] {"Type", "Count"};
    }

    @Override
    public String[] getValues() {
        return new String[] {type, count.toString()};
    }
}
