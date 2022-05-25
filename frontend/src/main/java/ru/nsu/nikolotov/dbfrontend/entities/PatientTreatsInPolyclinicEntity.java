package ru.nsu.nikolotov.dbfrontend.entities;


import lombok.Getter;
import lombok.Setter;

import java.util.Locale;

@Getter
@Setter
public class PatientTreatsInPolyclinicEntity implements EntityForInsertIntoJTable{
    private Integer patientId;
    private String patientName;
    private Integer doctorId;
    private String doctorName;
    private String currentDisease;
    private String currentMeds;
    private String currentOperation;
    private String admissionDate;
    private String dateOfRecovery;

    @Override
    public String[] getHeaders() {
        return new String[] {"Patient ID", "Patient Name", "Doctor ID", "Doctor Name", "Current Disease",
                "Current Meds", "Current Operation", "Admission date", "Date of Recovery"};
    }

    @Override
    public String[] getValues() {
        String curD = "-";
        if (currentDisease != null) {
            curD = currentDisease;
        }
        String curM = "-";
        if (currentMeds != null) {
            curM = currentMeds;
        }
        String curO = "-";
        if (currentOperation != null) {
            curO = currentOperation;
        }
        String dateOfRec = "-";
        if (dateOfRecovery != null) {
            dateOfRec = dateOfRecovery;
        }
        return new String[]{patientId.toString(), patientName, doctorId.toString(), doctorName, curD, curM, curO, admissionDate, dateOfRec};
    }
}
