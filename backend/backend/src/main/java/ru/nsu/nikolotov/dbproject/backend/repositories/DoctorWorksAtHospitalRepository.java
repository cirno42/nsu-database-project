package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.dtos.CreateDoctorWorksAtHospitalDTO;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtHospitalFullInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtInstitutionEntity;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Repository
@Transactional
public class DoctorWorksAtHospitalRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<DoctorWorksAtHospitalFullInfoEntity> getAllWorkers() {
        String statementString = "select w.doctorid as doctorid, doctors.name as doctorname, h.id as hospitalid, h.name as hospitalname, w.salary as salary,\n" +
                "w.contractStartDate as contractStartDate, w.contractendDate as contractEndDate, doctors.vacationstart, doctors.vacationend, doctors.salarycoefficient\n" +
                "from doctors inner join doctorworksathospital w on doctors.id = w.doctorid inner join hospitals h on w.hospitalid = h.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorWorksAtHospitalFullInfoEntity.class));
    }

    public List<DoctorWorksAtHospitalFullInfoEntity> getHistoryOfWorkers() {
        String statementString = "select w.doctorid as doctorid, doctors.name as doctorname, h.id as hospitalid, h.name as hospitalname, w.salary as salary,\n" +
                "w.employmentdate as contractStartDate, w.dismissaldate as contractEndDate, doctors.vacationstart, doctors.vacationend, doctors.salarycoefficient\n" +
                "from doctors inner join doctorworkedathospital w on doctors.id = w.doctorid inner join hospitals h on w.hospitalid = h.id;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorWorksAtHospitalFullInfoEntity.class));
    }

    public DoctorWorksAtHospitalFullInfoEntity addDoctorToHospital(CreateDoctorWorksAtHospitalDTO dto) {
        String statementString = "insert into doctorworksathospital(doctorid, hospitalid, salary, contractstartdate, contractenddate) values (?,?,?,?,?)";

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy", Locale.ENGLISH);
        LocalDate conStart = LocalDate.parse(dto.getContractStartDate(), formatter);
        LocalDate conEnd = LocalDate.parse(dto.getContractEndDate(), formatter);


        jdbcTemplate.update(statementString, dto.getDoctorId(), dto.getHospitalId(), dto.getSalary(), Date.valueOf(conStart),
                Date.valueOf(conEnd));

        var workers = getAllWorkers();
        return workers.stream().filter(el -> el.getDoctorId().equals( dto.getDoctorId())).collect(Collectors.toList()).get(0);
    }

    public void fireDoctor(Integer id) {
        String statementString = "Select * from firedoctorfromhospital("+ id + ")";
        jdbcTemplate.update(statementString, id);
    }
}
