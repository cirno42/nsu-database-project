package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PatientEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PatientTreatmentInfo;
import ru.nsu.nikolotov.dbfrontend.entities.PatientTreatsInPolyclinicEntity;

import java.util.List;

public class PatientAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/patients";

    public static List<PatientEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall").asObject(new GenericType<List<PatientEntity>>(){}).getBody();
    }

    public static List<PatientTreatmentInfo> getPatientsWhoTreatsInSuchPlace(Integer hospitalId, Integer departmentId, Integer wardId) {
        String depId = "";
        if (departmentId != null) {
            depId = departmentId.toString();
        }

        String wId = "";
        if (wardId != null) {
            wId = wardId.toString();
        }

        return Unirest.get(ENDPOINT + "/patientswhotreatsinsuchplace")
                .queryString("hospitalId", hospitalId)
                .queryString("departmentId", depId)
                .queryString("wardId", wId)
                .asObject(new GenericType<List<PatientTreatmentInfo>>(){})
                .getBody();
    }

    public static List<PatientTreatmentInfo> getPatientsWhoTreatedByDoctor (Integer doctorId, String admission, String recovery) {
        return Unirest.get(ENDPOINT + "/patientstreatedbydoctor")
                .queryString("doctorId", doctorId)
                .queryString("admission", admission)
                .queryString("recovery", recovery)
                .asObject(new GenericType<List<PatientTreatmentInfo>>(){})
                .getBody();
    }

    public static List<PatientTreatmentInfo> getPatientsWhoTreatedInHospital (Integer hospitalId, String admission, String recovery) {
        return Unirest.get(ENDPOINT + "/patientstreatedinhospital")
                .queryString("hospitalId", hospitalId)
                .queryString("admission", admission)
                .queryString("recovery", recovery)
                .asObject(new GenericType<List<PatientTreatmentInfo>>(){})
                .getBody();
    }

    public static List<PatientTreatsInPolyclinicEntity> getPatientsWhoTreatedInSuchPolyclinic(String doctorType, Integer polyclinicId) {
        return Unirest.get(ENDPOINT + "/patientstreatsinpolyclinic")
                .queryString("polyclinicId", polyclinicId)
                .queryString("doctorType", doctorType)
                .asObject(new GenericType<List<PatientTreatsInPolyclinicEntity>>(){})
                .getBody();
    }


}
