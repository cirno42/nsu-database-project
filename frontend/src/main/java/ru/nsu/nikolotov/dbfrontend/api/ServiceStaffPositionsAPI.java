package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.HospitalEntity;
import ru.nsu.nikolotov.dbfrontend.entities.ServiceStaffPositionEntity;

import java.util.List;

public class ServiceStaffPositionsAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/servicestaffpositions";

    public static List<ServiceStaffPositionEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall").asObject(new GenericType<List<ServiceStaffPositionEntity>>(){}).getBody();
    }
}
