package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DoctorWorksAtInstitutionEntity implements EntityForInsertIntoJTable{
    private Integer doctorId;
    private String doctorName;
    private Integer institutionId;
    private String institutionName;
    private Integer salary;
    private Date contractStartDate;
    private Date contractEndDate;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.
                append(doctorId).
                append(' ').
                append(doctorName).
                append(' ').
                append(institutionId).
                append(' ').
                append(institutionName).
                append(' ').
                append(salary)
                .append(' ')
                .append(contractStartDate.toString())
                .append(' ')
                .append(contractEndDate.toString())
                .append(' ');
        if (vacationStart != null) {
            stringBuilder.append(vacationStart.toString()).append(' ');
        } else {
            stringBuilder.append("null").append(' ');
        }
        if (vacationEnd != null) {
            stringBuilder.append(vacationEnd.toString()).append(' ');
        } else {
            stringBuilder.append("null").append(' ');
        }
        stringBuilder.append(salaryCoefficient);
        return stringBuilder.toString();
    }

    @Override
    public String[] getHeaders() {
        return new String[] {"Doctor ID", "Doctor Name", "Inst. ID", "Inst. Name", "Salary", "Contract Start", "Contract End", "Vacation Start", "Vacation End", "Salary Coeff."};
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
        return new String[]{
                doctorId.toString(),
                doctorName,
                institutionId.toString(),
                institutionName,
                salary.toString(),
                contractStartDate.toString(),
                contractEndDate.toString(),
                vacStart,
                vacEnd,
                salaryCoefficient.toString()
        };
    }


}
