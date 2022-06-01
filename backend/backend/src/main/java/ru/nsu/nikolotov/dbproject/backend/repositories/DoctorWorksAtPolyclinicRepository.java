package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtPolyclinicDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtPolyclinicFullInfo;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
public class DoctorWorksAtPolyclinicRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<DoctorWorksAtPolyclinicFullInfo> getAllWorkers() {
        String statementString = "select w.doctorid as doctorid, doctors.name as doctorname, h.id as polyclinicid, h.name as polyclinicname, w.salary as salary,\n" +
                "w.contractStartDate as contractStartDate, w.contractendDate as contractEndDate, doctors.vacationstart, doctors.vacationend, doctors.salarycoefficient\n" +
                "from doctors inner join doctorworksatpolyclinic w on doctors.id = w.doctorid inner join polyclinics h on w.polyclinicid = h.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorWorksAtPolyclinicFullInfo.class));
    }

    public List<DoctorWorksAtPolyclinicFullInfo> getHistoryOfWorkers() {
        String statementString = "select w.doctorid as doctorid, doctors.name as doctorname, h.id as polyclinicid, h.name as polyclinicname, w.salary as salary,\n" +
                "w.employmentdate as contractStartDate, w.dismissaldate as contractEndDate, doctors.vacationstart, doctors.vacationend, doctors.salarycoefficient\n" +
                "from doctors inner join doctorworkedatpolyclinic w on doctors.id = w.doctorid inner join polyclinics h on w.polyclinicid = h.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorWorksAtPolyclinicFullInfo.class));
    }

    public DoctorWorksAtPolyclinicFullInfo addDoctorToPolyclinic(CreateDoctorWorksAtPolyclinicDTO dto) {
        String statementString = "insert into doctorworksatpolyclinic(doctorid, polyclinicid, salary, contractstartdate, contractenddate) values (?,?,?,?,?)";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate conStart = LocalDate.parse(dto.getContractStartDate(), formatter);
        LocalDate conEnd = LocalDate.parse(dto.getContractEndDate(), formatter);


        jdbcTemplate.update(statementString, dto.getDoctorId(), dto.getPolyclinicId(), dto.getSalary(), Date.valueOf(conStart),
                Date.valueOf(conEnd));

        var workers = getAllWorkers();
        return workers.stream().filter(el -> el.getDoctorId().equals( dto.getDoctorId())).collect(Collectors.toList()).get(0);
    }

    public void fireDoctor(Integer id) {
        String statementString = "Select * from firedoctorfrompolyclinic("+ id +")";
        jdbcTemplate.update(statementString, id);
    }

}
