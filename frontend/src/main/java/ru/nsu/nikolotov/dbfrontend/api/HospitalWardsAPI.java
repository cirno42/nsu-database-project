package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalDepartmentEntity;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalWardEntity;

import java.util.List;

public class HospitalWardsAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/hospitalwards";

    public static List<HospitalWardEntity> getWardsInDepartment(Integer departmentId) {
        return Unirest.get(ENDPOINT + "/indepartment")
                .queryString("departmentId", departmentId)
                .asObject(new GenericType<List<HospitalWardEntity>>(){})
                .getBody();
    }
}
