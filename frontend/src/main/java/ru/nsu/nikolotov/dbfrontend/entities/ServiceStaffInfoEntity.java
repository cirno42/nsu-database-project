package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Setter
@Getter
public class ServiceStaffInfoEntity implements EntityForInsertIntoJTable {
    private Integer institutionId;
    private String institutionName;
    private String workerName;
    private String position;
    private Double salaryCoefficient;
    private Integer salary;
    private Date employmentDate;
    private Date dismissalDate;

    @Override
    public String[] getHeaders() {
        return new String[] {"institutionId", "Institution Name", "Worker Name", "position", "Salary Coefficient",
        "Salary", "Employment Date", "Dismissal Date"};
    }

    @Override
    public String[] getValues() {
        String disDate = "-";
        if (dismissalDate != null) {
            disDate = dismissalDate.toString();
        }
        return new String[] {institutionId.toString(), institutionName, workerName, position, salaryCoefficient.toString(), salary.toString(), employmentDate.toString(), disDate};
    }
}
