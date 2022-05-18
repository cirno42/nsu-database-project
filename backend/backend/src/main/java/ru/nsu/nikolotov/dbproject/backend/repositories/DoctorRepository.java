package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.nsu.nikolotov.dbproject.backend.entities.*;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorSciencePosition;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorScienceRank;
import ru.nsu.nikolotov.dbproject.backend.types.DoctorType;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.Date;
import java.util.List;

import static ru.nsu.nikolotov.dbproject.backend.types.DoctorType.NONE;
import static ru.nsu.nikolotov.dbproject.backend.types.DoctorType.doctorTypeToTableName;

@Repository
@Transactional
public class DoctorRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;



    public DoctorEntity create(DoctorEntity entity) {
        var id = repositoryUtils.getNextval("doctors_id_seq");
        String statementString = "Insert into doctors(id, name, vacationstart, vacationend, salarycoefficient) " +
                " values (?, ?, ?, ?, ?)";

        int affectedRows = jdbcTemplate.update(statementString,
                id,
                entity.getName(),
                entity.getVacationStart(),
                entity.getVacationEnd(),
                entity.getSalaryCoefficient());

        if (entity.getCanDoOperation()) {
            insertIntoDoctorsWhoCanDoOperations(id);
        }

        markDoctorRank(id, entity.getDoctorScienceRank());
        markDoctorPosition(id, entity.getDoctorSciencePosition());
        insertDoctorType(id, entity.getDoctorType());

        if (affectedRows == 0) {
            return null;
        }
        entity.setId(id);
        return entity;
    }

    public DoctorEntity getNext(int id) {
        var nextId = repositoryUtils.getNextId("doctors", id);
        return getById(nextId);
    }

    public void delete(int id)  {

    }

    public List<DoctorWorksAtInstitutionEntity> getDoctorsWorkingInHospital
            (Integer instId, DoctorType doctorType, MedicineInstitutionType institutionType) {

        String doctorTypeTableName = doctorTypeToTableName(doctorType);
        String statementString = getStatementStringOfDoctorWorksStatement(doctorType, institutionType);
        if (statementString == null) {
            return null;
        }
        List<DoctorWorksAtInstitutionEntity> res;
        if (institutionType != MedicineInstitutionType.ALL) {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorWorksAtInstitutionEntity.class), instId);
        } else {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorWorksAtInstitutionEntity.class));
        }
        return res;
    }

    public DoctorEntity getById(int id) {
        return repositoryUtils.getEntityId("doctors", id, DoctorEntity.class);
    }

    public void markDoctorPosition(int id, DoctorSciencePosition position) {
        if (position == DoctorSciencePosition.NONE) {
            return;
        }

        String tableName = DoctorSciencePosition.doctorPositionToTableName(position);
        String statementString = "insert into " + tableName + "(id)  values (?)";
        jdbcTemplate.update(statementString, id);
    }

    public void markDoctorRank(int id, DoctorScienceRank rank) {
        if (rank == DoctorScienceRank.NONE) {
            return;
        }
        String tableName = DoctorScienceRank.doctorRankToTableName(rank);
        String statementString = "Insert into " + tableName + "(id) values(?)";
        jdbcTemplate.update(statementString, id);
    }

    public void insertIntoDoctorsWhoCanDoOperations(int id) {
        String statementString = "insert into doctorswhocandooperations (id, operationstotalcount, operationswithlethalresult)" +
                "values (?, 0, 0)";
        jdbcTemplate.update(statementString, id);
    }

    public List<DoctorEntity> getDoctorsWhoDoneMoreOperations
            (Integer count, Integer institutionId, DoctorType doctorType, MedicineInstitutionType institutionType) {
        String statementString = getStatementStringOfDoctorDoneMoreOperations(doctorType, institutionType);
        if (statementString == null) {
            return null;
        }
        List<DoctorEntity> res;
        if (institutionType != MedicineInstitutionType.ALL) {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorEntity.class), institutionId, count);
        } else {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorEntity.class), count, count);
        }
        return res;
    }

    public List<DoctorExperienceEntity> getDoctorsWithMoreExperience
            (Integer experience, Integer instId, MedicineInstitutionType institutionType, DoctorType doctorType) {
        String statementString = getStatementStringForDoctorExperience(institutionType, doctorType);
        if (statementString == null) {
            return null;
        }
        List<DoctorExperienceEntity> res;
        if (institutionType != MedicineInstitutionType.ALL) {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorExperienceEntity.class), instId, experience);
        } else {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorExperienceEntity.class), experience, experience);
        }
        return res;
    }

    public List<DoctorEntity> getDoctorsWithSuchRankAndPosition
            (Integer instId,
             DoctorType doctorType,
             MedicineInstitutionType institutionType,
             DoctorScienceRank rank,
             DoctorSciencePosition position) {

        String statementString = getStatementStringForDoctorsWithSuchRankAndPosition(doctorType, institutionType, rank, position);
        if (statementString == null) {
            return null;
        }
        List<DoctorEntity> res;
        if (institutionType != MedicineInstitutionType.ALL) {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorEntity.class), instId);
        } else {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(DoctorEntity.class));
        }
        return res;
    }

    public List<DoctorPolyclinicWorkStatistic> getPolyclinicStats(Integer doctorId,
                                                                  Date beginDate,
                                                                  Date endDate,
                                                                  Integer polyclinicId,
                                                                  DoctorType doctorType) {
        String statementString = null;
        String doctorTypeInnerJoin = " ";
        if (doctorType != NONE) {
            String tablename = DoctorType.doctorTypeToTableName(doctorType);
            doctorTypeInnerJoin = " inner join " + tablename + " on (Doctors.id = " + tablename + ".id) ";
        }
        if (doctorId != null) {
            statementString = "Select Doctors.id, Doctors.name, count(*)*1.0 / ( CAST((?) as DATE) - CAST((?) as DATE) ) as averageVisits\n" +
                    "from Doctors inner join PatientsVisitPolyclinicCabinets on (Doctors.id = PatientsVisitPolyclinicCabinets.doctorId)\n" +
                    "where dateOfVisit >= ? and dateOfVisit <= ? and Doctors.id=?\n" +
                    "group by Doctors.id;";
            return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorPolyclinicWorkStatistic.class),
                    endDate, beginDate, beginDate, endDate, doctorId);
        } else {
            if (polyclinicId != null) {
                statementString = "Select Doctors.id, Doctors.name, count(*)*1.0 / ( CAST((?) as DATE) - CAST((?) as DATE) ) as averageVisits\n" +
                        "from Doctors inner join PatientsVisitPolyclinicCabinets on (Doctors.id = PatientsVisitPolyclinicCabinets.doctorId)\n" +
                         " inner join PolyclinicCabinets on (PolyclinicCabinets.id = PatientsVisitPolyclinicCabinets.cabinetId)" +
                        " inner join Polyclinics on (polyclinics.id = PolyclinicCabinets.polyclinicId)" + doctorTypeInnerJoin +
                        "where dateOfVisit >= ? and dateOfVisit <= ? and Polyclinics.id=?\n" +
                        "group by Doctors.id;";
                return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorPolyclinicWorkStatistic.class),
                        endDate, beginDate, beginDate, endDate, polyclinicId);
            } else {
                statementString = "Select Doctors.id, Doctors.name, count(*)*1.0 / ( CAST((?) as DATE) - CAST((?) as DATE) ) as averageVisits\n" +
                        "from Doctors inner join PatientsVisitPolyclinicCabinets on (Doctors.id = PatientsVisitPolyclinicCabinets.doctorId)\n" +
                        doctorTypeInnerJoin +
                        "where dateOfVisit >= ? and dateOfVisit <= ? \n" +
                        "group by Doctors.id;";
                return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorPolyclinicWorkStatistic.class),
                        endDate, beginDate, beginDate, endDate);

            }
        }
    }


    public List<DoctorStatisticEntity> getHospitalStats(Integer doctorId,
                                                        Integer hospitalId,
                                                        DoctorType doctorType) {
        String statementString = null;
        String doctorTypeInnerJoin = " ";
        if (doctorType != NONE) {
            String tablename = DoctorType.doctorTypeToTableName(doctorType);
            doctorTypeInnerJoin = " inner join " + tablename + " on (Doctors.id = " + tablename + ".id) ";
        }
        if (doctorId != null) {
            statementString = "Select Doctors.id, Doctors.name, count(PatientTreatsInHospital.patientId)\n" +
                    "from Doctors left join PatientTreatsInHospital on (Doctors.id = PatientTreatsInHospital.doctorId)\n" +
                    "where Doctors.id = ? "+
                    "group by Doctors.id;";
            return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorStatisticEntity.class), doctorId);
        } else {
            if (hospitalId != null) {
                statementString = "Select Doctors.id, Doctors.name, count(PatientTreatsInHospital.patientId)\n" +
                        "from Doctors left join PatientTreatsInHospital on (Doctors.id = PatientTreatsInHospital.doctorId)\n" +
                         doctorTypeInnerJoin +
                        "inner join hospitalwards on (hospitalwards.id = PatientTreatsInHospital.wardid) " +
                        "inner join hospitaldepartments on (hospitalwards.departmentid = hospitaldepartments.id) " +
                        "inner join hospitals h on hospitaldepartments.hospitalid = h.id " +
                        "where h.id = ? "+
                        "group by Doctors.id;";
                return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorStatisticEntity.class), hospitalId);
            } else {
                statementString = "Select Doctors.id, Doctors.name, count(PatientTreatsInHospital.patientId)\n" +
                        "from Doctors left join PatientTreatsInHospital on (Doctors.id = PatientTreatsInHospital.doctorId)\n" +
                         doctorTypeInnerJoin +
                        "group by Doctors.id;";
                return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(DoctorStatisticEntity.class));
            }
        }
    }


    private void insertDoctorType(int id, DoctorType type) {
        if (type == DoctorType.NONE) {
            return;
        }
        String tableName = doctorTypeToTableName(type);

        String statementString = "Insert into " + tableName + "(id) values (?)";
        jdbcTemplate.update(statementString, id);
    }


    private String getStatementStringOfDoctorWorksStatement(DoctorType doctorType, MedicineInstitutionType medicineInstitutionType) {
        String doctorTypeTableName = doctorTypeToTableName(doctorType);
        String statementString = null;
        switch (medicineInstitutionType) {
            case NONE:
                return null;
            case HOSPITAL:
                statementString = "Select Doctors.id as doctorId, Doctors.name as doctorName, Hospitals.id as institutionId, Hospitals.name as institutionName, DoctorWorksAtHospital.salary, DoctorWorksAtHospital.contractStartDate, DoctorWorksAtHospital.contractEndDate, Doctors.vacationStart, " +
                    "Doctors.vacationEnd, Doctors.salaryCoefficient " +
                    "from (" + doctorTypeTableName + " INNER JOIN DoctorWorksAtHospital on (" + doctorTypeTableName + ".id = DoctorWorksAtHospital.doctorId) " +
                    "INNER JOIN Doctors on (" + doctorTypeTableName + ".id = Doctors.id)) " +
                    "INNER JOIN Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id) where hospitalId = ?";
                break;
            case POLYCLINIC:
                statementString = "Select Doctors.id as doctorId, Doctors.name as doctorName, Polyclinics.id as institutionId, Polyclinics.name as institutionName, " +
                        "DoctorWorksAtPolyclinic.salary, DoctorWorksAtPolyclinic.contractStartDate, " +
                        "DoctorWorksAtPolyclinic.contractEndDate, Doctors.vacationStart, " +
                        "Doctors.vacationEnd, Doctors.salaryCoefficient " +
                        "from (Surgeons INNER JOIN DoctorWorksAtPolyclinic on (Surgeons.id = DoctorWorksAtPolyclinic.doctorId) " +
                        "INNER JOIN Doctors on (Surgeons.id = Doctors.id)) " +
                        "INNER JOIN Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id) " +
                        "Where polyclinicid = ?;";
                break;
            case ALL:
                statementString = "Select Doctors.id as doctorId, Doctors.name as doctorName, Hospitals.id as institutionId, Hospitals.name as institutionName, DoctorWorksAtHospital.salary, DoctorWorksAtHospital.contractStartDate, DoctorWorksAtHospital.contractEndDate, Doctors.vacationStart, " +
                        "Doctors.vacationEnd, Doctors.salaryCoefficient " +
                        "from (" + doctorTypeTableName + " INNER JOIN DoctorWorksAtHospital on (" + doctorTypeTableName + ".id = DoctorWorksAtHospital.doctorId) " +
                        "INNER JOIN Doctors on (" + doctorTypeTableName + ".id = Doctors.id)) " +
                        "INNER JOIN Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id) "
                        + "UNION " +
                        "Select Doctors.id as doctorId, Doctors.name as doctorName, Polyclinics.id as institutionId, Polyclinics.name as institutionName, " +
                        "DoctorWorksAtPolyclinic.salary, DoctorWorksAtPolyclinic.contractStartDate, " +
                        "DoctorWorksAtPolyclinic.contractEndDate, Doctors.vacationStart, " +
                        "Doctors.vacationEnd, Doctors.salaryCoefficient " +
                        "from (Surgeons INNER JOIN DoctorWorksAtPolyclinic on (Surgeons.id = DoctorWorksAtPolyclinic.doctorId) " +
                        "INNER JOIN Doctors on (Surgeons.id = Doctors.id)) " +
                        "INNER JOIN Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id) ";
        }
        return statementString;
    }

    private String getStatementStringOfDoctorDoneMoreOperations (DoctorType doctorType, MedicineInstitutionType institutionType) {
        String doctorTableName = doctorTypeToTableName(doctorType);
        String statementString = null;
        switch (institutionType){
            case HOSPITAL:
                statementString = "Select Doctors.id, Doctors.name, doctors.salarycoefficient, doctors.vacationstart, doctors.vacationend from DoneOperationsForHospitals\n" +
                        "inner join Doctors on (DoneOperationsForHospitals.doctorId = Doctors.id)\n" +
                        "inner join "+ doctorTableName + " on (doctors.id = " + doctorTableName + ".id)\n" +
                        "where DoneOperationsForHospitals.hospitalid = ?\n" +
                        "group by Doctors.id\n" +
                        "Having count(*) >= ?;";
                break;
            case POLYCLINIC:
                statementString = "Select Doctors.id, Doctors.name, doctors.salarycoefficient, doctors.vacationstart, doctors.vacationend from DoneOperationsForPolyclinics\n" +
                        "inner join Doctors on (DoneOperationsForPolyclinics.doctorId = Doctors.id)\n" +
                        "inner join "+ doctorTableName + " on (doctors.id = " + doctorTableName + ".id)\n" +
                        "where DoneOperationsForPolyclinics.polyclinicId = ?\n" +
                        "group by Doctors.id\n" +
                        "Having count(*) >= ?;";
                break;
            case ALL:
                statementString =
                        "Select Doctors.id, Doctors.name, doctors.salarycoefficient, doctors.vacationstart, doctors.vacationend from DoneOperationsForHospitals\n" +
                                "inner join Doctors on (DoneOperationsForHospitals.doctorId = Doctors.id)\n" +
                                "inner join "+ doctorTableName + " on (doctors.id = " + doctorTableName + ".id)\n" +
                                "group by Doctors.id\n" +
                                "Having count(*) >= ? "
                                + "UNION "
                        + "Select Doctors.id, Doctors.name, doctors.salarycoefficient, doctors.vacationstart, doctors.vacationend from DoneOperationsForPolyclinics\n" +
                                "inner join Doctors on (DoneOperationsForPolyclinics.doctorId = Doctors.id)\n" +
                                "inner join "+ doctorTableName + " on (doctors.id = " + doctorTableName + ".id)\n" +
                                "group by Doctors.id\n" +
                                "Having count(*) >= ?;";
                break;

        }
        return statementString;
    }

    public String getStatementStringForDoctorExperience(MedicineInstitutionType institutionType, DoctorType doctorType) {
        String statementString = null;
        String doctorTypeTableName = doctorTypeToTableName(doctorType);
        switch (institutionType) {
            case HOSPITAL:
                statementString = "Select Doctors.id, Doctors.name, sum(dismissaldate - employmentdate) as workDays\n" +
                        "from Doctors inner join DoctorWorkedAtHospital on (Doctors.id = DoctorWorkedAtHospital.doctorId)\n" +
                        "inner join Hospitals on (Hospitals.id = DoctorWorkedAtHospital.hospitalId)\n" +
                        "inner join " + doctorTypeTableName + " on (doctors.id = "+ doctorTypeTableName + ".id)\n" +
                        "where hospitals.id = ?\n" +
                        "group by Doctors.id\n" +
                        "having sum(dismissaldate - employmentdate)  > ?;\n";
                break;
            case POLYCLINIC:
                statementString = "Select Doctors.id, Doctors.name, sum(dismissaldate - employmentdate) as workDays\n" +
                        "from Doctors inner join DoctorWorkedAtPolyclinic on (Doctors.id = DoctorWorkedAtPolyclinic.doctorId)\n" +
                        "inner join Polyclinics on (Polyclinics.id = DoctorWorkedAtPolyclinic.polyclinicId)\n" +
                        "inner join " + doctorTypeTableName + " on (doctors.id = "+ doctorTypeTableName + ".id)\n" +
                        "where Polyclinics.id = ?\n" +
                        "group by Doctors.id\n" +
                        "having sum(dismissaldate - employmentdate)  > ?;\n";
                break;
            case ALL:
                statementString = "Select Doctors.id, Doctors.name, sum(dismissaldate - employmentdate) as workDays\n" +
                        "from Doctors inner join DoctorWorkedAtHospital on (Doctors.id = DoctorWorkedAtHospital.doctorId)\n" +
                        "inner join Hospitals on (Hospitals.id = DoctorWorkedAtHospital.hospitalId)\n" +
                        "inner join " + doctorTypeTableName + " on (doctors.id = "+ doctorTypeTableName + ".id)\n" +
                        "group by Doctors.id\n" +
                        "having sum(dismissaldate - employmentdate)  > ?\n"
                + "UNION " +
                        "Select Doctors.id, Doctors.name, sum(dismissaldate - employmentdate) as workDays\n" +
                        "from Doctors inner join DoctorWorkedAtPolyclinic on (Doctors.id = DoctorWorkedAtPolyclinic.doctorId)\n" +
                        "inner join Polyclinics on (Polyclinics.id = DoctorWorkedAtPolyclinic.polyclinicId)\n" +
                        "inner join " + doctorTypeTableName + " on (doctors.id = "+ doctorTypeTableName + ".id)\n" +
                        "group by Doctors.id\n" +
                        "having sum(dismissaldate - employmentdate)  > ?;\n";
                break;

        }
        return statementString;
    }

    private String getStatementStringForDoctorsWithSuchRankAndPosition(DoctorType doctorType,
                                                                      MedicineInstitutionType institutionType,
                                                                      DoctorScienceRank rank,
                                                                      DoctorSciencePosition position) {
        String statementString = null;
        String doctorTypeTableName = doctorTypeToTableName(doctorType);
        String doctorRankTableName = DoctorScienceRank.doctorRankToTableName(rank);
        String doctorPositionTableName = DoctorSciencePosition.doctorPositionToTableName(position);
        String innerJoinForRank = " ";
        String innerJoinForPosition = " ";
        String innerJoinForType = " inner join " + doctorTypeTableName + " on (Doctors.id = " + doctorTypeTableName + ".id) ";
        if (doctorRankTableName != null) {
            innerJoinForRank = " inner join " + doctorRankTableName + " on (Doctors.id = " + doctorRankTableName + ".id) ";
        }
        if (doctorPositionTableName != null) {
            innerJoinForPosition = " inner join " + doctorPositionTableName + " on (Doctors.id = " + doctorPositionTableName + ".id) ";
        }


        switch (institutionType) {
            case HOSPITAL:
                statementString = "Select Doctors.id, Doctors.name, Doctors.vacationStart, Doctors.vacationEnd, Doctors.salaryCoefficient from\n" +
                        "    Doctors\n"
                        + innerJoinForPosition + innerJoinForRank + innerJoinForType +
                        " inner join DoctorWorksAtHospital on (DoctorWorksAtHospital.doctorId = Doctors.id)\n" +
                        " inner join Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id)\n" +
                        "Where (Hospitals.id = ?);";
                break;
            case POLYCLINIC:
                statementString = "Select Doctors.id, Doctors.name, Doctors.vacationStart, Doctors.vacationEnd, Doctors.salaryCoefficient from\n" +
                        "    Doctors\n"
                        + innerJoinForPosition + innerJoinForRank + innerJoinForType +
                        " inner join DoctorWorksAtPolyclinic on (DoctorWorksAtPolyclinic.doctorId = Doctors.id)\n" +
                        " inner join Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id)\n" +
                        "Where (Polyclinics.id = ?);";
                break;
            case ALL:
                statementString = "Select Doctors.id, Doctors.name, Doctors.vacationStart, Doctors.vacationEnd, Doctors.salaryCoefficient from\n" +
                        "    Doctors\n"
                        + innerJoinForPosition + innerJoinForRank + innerJoinForType +
                        " inner join DoctorWorksAtHospital on (DoctorWorksAtHospital.doctorId = Doctors.id)\n" +
                        " inner join Hospitals on (DoctorWorksAtHospital.hospitalId = Hospitals.id) UNION \n" +
                        "Select Doctors.id, Doctors.name, Doctors.vacationStart, Doctors.vacationEnd, Doctors.salaryCoefficient from\n" +
                        "Doctors\n"
                        + innerJoinForPosition + innerJoinForRank + innerJoinForType +
                        " inner join DoctorWorksAtPolyclinic on (DoctorWorksAtPolyclinic.doctorId = Doctors.id)\n" +
                        " inner join Polyclinics on (DoctorWorksAtPolyclinic.polyclinicId = Polyclinics.id)\n";
                break;
        }
        return statementString;
    }
}
