package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;
import ru.nsu.nikolotov.dbfrontend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbfrontend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbfrontend.types.DoctorType;

import java.util.Date;

@Setter
@Getter
public class DoctorEntity implements EntityForInsertIntoJTable{
    private Integer id;
    private String name;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;
    private DoctorType doctorType;
    private DoctorSciencePosition doctorSciencePosition;
    private DoctorScienceRank doctorScienceRank;
    private Boolean canDoOperation;

    @Override
    public String[] getHeaders() {
        return new String[] {"id", "name", "vacationStart", "vacationEnd", "salary coeff.", "Type", "Position", "Rank"};
    }

    @Override
    public String[] getValues() {
        String vacStart = "-";
        if (vacationStart != null) {
            vacStart = vacationStart.toString();
        }
        String vacEnd = "-";
        if (vacationEnd != null) {
            vacEnd = vacationEnd.toString();
        }
        String type = "-";
        if (doctorType != null) {
            type = doctorType.toString();
        }
        String position = "-";
        if (doctorSciencePosition != null) {
            position = doctorSciencePosition.toString();
        }
        String rank = "-";
        if (doctorScienceRank != null) {
            rank = doctorScienceRank.toString();
        }
        return new String[] {id.toString(), name, vacStart, vacEnd, salaryCoefficient.toString(), type, position, rank};
    }
}
