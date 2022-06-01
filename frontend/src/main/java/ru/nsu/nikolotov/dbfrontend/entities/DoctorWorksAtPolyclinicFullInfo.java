package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorWorksAtPolyclinicFullInfo implements EntityForInsertIntoJTable{
    private Integer doctorId;
    private String doctorName;
    private Integer polyclinicId;
    private String polyclinicName;
    private Integer salary;
    private Date contractStartDate;
    private Date contractEndDate;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;

    @Override
    public String[] getHeaders() {
        return new String[]{"Doctor ID, Doctor name", "Polyclinic ID", "Polyclinic name", "Salary", "Contract start date", "Contract end date",
                "vacation start date", "vacation end date", "Salary coeff."};
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
        return new String[] {doctorId.toString(), doctorName, polyclinicId.toString(), polyclinicName, salary.toString(), contractStartDate.toString(),
                contractEndDate.toString(), vacStart, vacEnd, salaryCoefficient.toString()};
    }
}
