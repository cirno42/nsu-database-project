package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.*;

import java.util.List;

public class ServiceStaffAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/servicestaff";

    public static List<ServiceStaffEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall").asObject(new GenericType<List<ServiceStaffEntity>>(){}).getBody();
    }

    public static List<ServiceStaffInfoEntity> getStaffWhoWorksInSuchInst(Integer institutionId, String institutionType, String workerType) {
        return Unirest.get(ENDPOINT + "/servicestaffinfo")
                .queryString("institutionId",institutionId)
                .queryString("institutionType", institutionType)
                .queryString("workerType", workerType)
                .asObject(new GenericType<List<ServiceStaffInfoEntity>>(){})
                .getBody();
    }

    public static ServiceStaffTypeStatistic getStaffStatistic(Integer institutionId, String institutionType, String workerType) {
        return Unirest.get(ENDPOINT + "/servicestaffinfo/count")
                .queryString("institutionId",institutionId)
                .queryString("institutionType", institutionType)
                .queryString("workerType", workerType)
                .asObject(ServiceStaffTypeStatistic.class)
                .getBody();
    }
}
