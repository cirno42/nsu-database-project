package ru.nsu.nikolotov.dbfrontend.api;

import kong.unirest.GenericType;
import kong.unirest.Unirest;
import ru.nsu.nikolotov.dbfrontend.entities.DoctorStatisticEntity;
import ru.nsu.nikolotov.dbfrontend.entities.PolyclinicEntity;

import java.util.List;

public class PolyclinicAPI {
    private static final String POLYCLINIC_ENDPOINT = "http://localhost:8080/api/v1/polyclinics";
    public static List<PolyclinicEntity> getAll() {
        return Unirest.get(POLYCLINIC_ENDPOINT + "/getall").asObject(new GenericType<List<PolyclinicEntity>>(){}).getBody();
    }

    public static DoctorStatisticEntity getCountOfCabinets(Integer polyclinicId) {
        return Unirest.get(POLYCLINIC_ENDPOINT + "/cabinetscount")
                .queryString("polyclinicId", polyclinicId)
                .asObject(DoctorStatisticEntity.class)
                .getBody();
    }
}
