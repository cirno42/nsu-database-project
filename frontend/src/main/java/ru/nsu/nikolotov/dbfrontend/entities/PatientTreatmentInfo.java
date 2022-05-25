package ru.nsu.nikolotov.dbfrontend.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class PatientTreatmentInfo implements EntityForInsertIntoJTable{
    private Integer id;
    private String name;
    private String diseaseHistory;
    private String operationsHistory;
    private String medsHistory;
    private Date admissionDate;
    private Date dateOfRecovery;

    @Override
    public String[] getHeaders() {
        return new String[] {"id", "name", "Disease history", "Operations history", "Meds history", "Admission date", "Date Of Recovery"};
    }

    @Override
    public String[] getValues() {
        String disH = "-";
        if (diseaseHistory != null) {
            disH = diseaseHistory;
        }
        String operH = "-";
        if (operationsHistory != null) {
            operH = operationsHistory;
        }
        String medsH = "-";
        if (medsHistory != null) {
            medsH = medsHistory;
        }
        return new String[] {id.toString(), name, disH, operH, medsH, admissionDate.toString(), dateOfRecovery.toString()};
    }
}
