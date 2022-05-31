package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalDepartmentFullInfo;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;

import java.util.List;

public class HospitalDepartmentsAPI {

    private static final String ENDPOINT = "http://localhost:8080/api/v1/hospitaldepartments";

    public static List<HospitalDepartmentEntity> getDepartmentsInHospital(Integer hospitalID) {
        System.out.println("Hospital: " + hospitalID);
        return Unirest.get(ENDPOINT + "/byhospital")
                .queryString("hospitalId", hospitalID)
                .asObject(new GenericType<List<HospitalDepartmentEntity>>(){})
                .getBody();
    }

    public static List<HospitalDepartmentFullInfo> getAll() {
        return Unirest.get(ENDPOINT + "/getall")
                .asObject(new GenericType<List<HospitalDepartmentFullInfo>>(){})
                .getBody();
    }

}
