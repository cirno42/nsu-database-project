package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class DoctorWorksAtHospitalFullInfoEntity implements EntityForInsertIntoJTable{
    private Integer doctorId;
    private String doctorName;
    private Integer hospitalId;
    private String hospitalName;
    private Integer salary;
    private Date contractStartDate;
    private Date contractEndDate;
    private Date vacationStart;
    private Date vacationEnd;
    private Double salaryCoefficient;

    @Override
    public String[] getHeaders() {
        return new String[] {"Doctor ID, Doctor name", "Hospital ID", "Hospital name", "Salary", "Contract start date", "Contract end date",
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
        return new String[] {doctorId.toString(), doctorName, hospitalId.toString(), hospitalName, salary.toString(), contractStartDate.toString(),
        contractEndDate.toString(), vacStart, vacEnd, salaryCoefficient.toString()};
    }
}
