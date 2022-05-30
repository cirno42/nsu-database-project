package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorTreatsPatientEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicCabinetEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicCabinetVisitsCountEntity;

import java.util.List;

public class PolyclinicCabinetAPI {
    private static final String ENDPOINT = "http://localhost:8080/api/v1/polycliniccabinets";

    public static List<PolyclinicCabinetEntity> getAll() {
        return Unirest.get(ENDPOINT + "/getall")
                .asObject(new GenericType<List<PolyclinicCabinetEntity>>(){})
                .getBody();
    }

    public static List<PolyclinicCabinetVisitsCountEntity> getCabinetVisitStats(Integer polyclinicId, String beginDate, String endDate) {
        return Unirest.get(ENDPOINT + "/visitscount")
                .queryString("polyclinicId", polyclinicId)
                .queryString("beginDate", beginDate)
                .queryString("endDate", endDate)
                .asObject(new GenericType<List<PolyclinicCabinetVisitsCountEntity>>(){})
                .getBody();
    }

}
