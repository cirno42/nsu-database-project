package ru.nsu.nikolotov.dbproject.backend.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.nikolotov.dbproject.backend.entities.DoctorWorksAtInstitutionEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.ServiceStaffInfoEntity;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.List;

@Repository
public class ServiceStaffInfoRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;
    @Autowired
    RepositoryUtils repositoryUtils;

    public List<ServiceStaffInfoEntity> getServiceStaffWorkingAtSuchInstitution
            (Integer institutionId, String workerType, MedicineInstitutionType institutionType) {
        List<ServiceStaffInfoEntity> res = null;
        var statementString = getStatementStringForServiceStaffWorks(institutionType);
        if (statementString == null) {
            return null;
        }
        if (institutionType != MedicineInstitutionType.ALL) {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(ServiceStaffInfoEntity.class), workerType, institutionId);
        } else {
            res = jdbcTemplate.query(statementString,
                    BeanPropertyRowMapper.newInstance(ServiceStaffInfoEntity.class), workerType, workerType);
        }
        return res;
    }

    private String getStatementStringForServiceStaffWorks(MedicineInstitutionType institutionType) {
        String statementString = null;
        switch (institutionType) {
            case HOSPITAL:
                statementString = "Select Hospitals.id as institutionId, Hospitals.name as institutionName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient, \n" +
                        "ServiceStaffWorksAtHospital.salary, ServiceStaffWorksAtHospital.employmentDate, ServiceStaffWorksAtHospital.dismissalDate \n" +
                        "from \n" +
                        "(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions\n" +
                        "where (ServiceStaffPositions.positionName = ?)) as positions \n" +
                        "inner join ServiceStaffWorksAtHospital on (ServiceStaffWorksAtHospital.positionId = positions.positionId)\n" +
                        "inner join Hospitals on (ServiceStaffWorksAtHospital.hospitalId = Hospitals.id)\n" +
                        "inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtHospital.workerId)" +
                        "where Hospitals.id=?;\n";
                break;
            case POLYCLINIC:
                statementString = "Select Polyclinics.id as institutionId, Polyclinics.name as institutionName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient, \n" +
                        "ServiceStaffWorksAtPolyclinic.salary, ServiceStaffWorksAtPolyclinic.employmentDate, ServiceStaffWorksAtPolyclinic.dismissalDate \n" +
                        "from \n" +
                        "(Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions\n" +
                        "where (ServiceStaffPositions.positionName = ?)) as positions \n" +
                        "inner join ServiceStaffWorksAtPolyclinic on (ServiceStaffWorksAtPolyclinic.positionId = positions.positionId)\n" +
                        "inner join Polyclinics on (ServiceStaffWorksAtPolyclinic.polyclinicId = Polyclinics.id)\n" +
                        "inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtPolyclinic.workerId)" +
                        "where Polyclinics.id=?;";
                break;
            case ALL:
                statementString = "Select Polyclinics.id as institutionId, Polyclinics.name as institutionName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient,\n" +
                        "       ServiceStaffWorksAtPolyclinic.salary, ServiceStaffWorksAtPolyclinic.employmentDate, ServiceStaffWorksAtPolyclinic.dismissalDate\n" +
                        "from\n" +
                        "    (Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions\n" +
                        "     where (ServiceStaffPositions.positionName = ?)) as positions\n" +
                        "        inner join ServiceStaffWorksAtPolyclinic on (ServiceStaffWorksAtPolyclinic.positionId = positions.positionId)\n" +
                        "        inner join Polyclinics on (ServiceStaffWorksAtPolyclinic.polyclinicId = Polyclinics.id)\n" +
                        "        inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtPolyclinic.workerId)\n" +
                        "UNION\n" +
                        "Select Hospitals.id as institutionId, Hospitals.name as institutionName, ServiceStaff.name as workerName, positions.positionName as position, positions.salaryCoefficient as salaryCoefficient,\n" +
                        "       ServiceStaffWorksAtHospital.salary, ServiceStaffWorksAtHospital.employmentDate, ServiceStaffWorksAtHospital.dismissalDate\n" +
                        "from\n" +
                        "    (Select ServiceStaffPositions.id as positionId, ServiceStaffPositions.salaryCoefficient, ServiceStaffPositions.positionName from ServiceStaffPositions\n" +
                        "     where (ServiceStaffPositions.positionName = ?)) as positions\n" +
                        "        inner join ServiceStaffWorksAtHospital on (ServiceStaffWorksAtHospital.positionId = positions.positionId)\n" +
                        "        inner join Hospitals on (ServiceStaffWorksAtHospital.hospitalId = Hospitals.id)\n" +
                        "        inner join ServiceStaff on (ServiceStaff.id = ServiceStaffWorksAtHospital.workerId);";
                break;
            case NONE:
                break;
        }
        return statementString;
    }
}
