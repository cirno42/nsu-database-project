package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DoctorExperienceEntity implements EntityForInsertIntoJTable{
    Integer id;
    String name;
    Integer workDays;

    @Override
    public String[] getHeaders() {
        return new String[]{"id", "name", "work days"};
    }

    @Override
    public String[] getValues() {
        return new String[]{id.toString(), name, workDays.toString()};
    }
}
