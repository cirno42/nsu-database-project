package ru.nsu.nikolotov.dbproject.backend.repositories;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.nsu.nikolotov.dbproject.backend.entities.DoneOperationsForPatientEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.LaboratoryEntity;
import ru.nsu.nikolotov.dbproject.backend.entities.LaboratoryServiceStatisticEntity;
import ru.nsu.nikolotov.dbproject.backend.types.LaboratoryTypes;
import ru.nsu.nikolotov.dbproject.backend.types.MedicineInstitutionType;

import java.util.Date;
import java.util.List;

@Repository
public class LaboratoryRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private RepositoryUtils repositoryUtils;

    public List<LaboratoryServiceStatisticEntity> getLabServicesStats
            (Integer labId, LaboratoryTypes labType, Date beginDate, Date endDate, Integer institutionId, MedicineInstitutionType institutionType) {
        
        if (institutionType == MedicineInstitutionType.ALL) {
            String labTableNameHosp = LaboratoryTypes.labTypeServicesToTableName(labType, MedicineInstitutionType.HOSPITAL);
            String labTableNamePoly = LaboratoryTypes.labTypeServicesToTableName(labType, MedicineInstitutionType.POLYCLINIC);
            String statementString = "select labs.id, labs.name, count(*)*1.0 / ( CAST((?) as DATE) - CAST((?) as DATE) + 1) as averageServices\n" +
                    "from (Select Laboratories.id, Laboratories.name from " + labTableNameHosp +
                    " inner join Laboratories on (" + labTableNameHosp +".laboratoryId = Laboratories.id)\n" +
                    "                                          inner join Hospitals on ("+ labTableNameHosp +".hospitalId = Hospitals.id)\n" +
                    "where " + labTableNameHosp + ".serviceDate >= ?\n" +
                    "  and  " + labTableNameHosp + ".serviceDate <= ?\n" +
                    "union Select Laboratories.id, Laboratories.name from " + labTableNamePoly +
                    " inner join Laboratories on (" + labTableNamePoly + ".laboratoryId = Laboratories.id)\n" +
                    " inner join polyclinics on ("+ labTableNamePoly + ".polyclinicid = polyclinics.id)\n" +
                    "      where "+ labTableNamePoly + ".serviceDate >= ?\n" +
                    "        and  "+ labTableNamePoly + ".serviceDate <= ?) as labs\n" +
                    "where labs.id = ? " +
                    "group by labs.id, labs.name;";
            return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(LaboratoryServiceStatisticEntity.class),
                    endDate, beginDate, beginDate, endDate, beginDate, endDate, labId);

        } else  {
            String instTable = null;
            String instRowId = null;
            if (institutionType == MedicineInstitutionType.HOSPITAL) {
                instTable = "hospitals";
                instRowId = "hospitalid";
            } else {
                instTable = "polyclinics";
                instRowId = "polyclinicid";
            }
            String labTableName = LaboratoryTypes.labTypeServicesToTableName(labType, institutionType);
            String statementString = "select Laboratories.id, Laboratories.name, count(*)*1.0 / ( CAST((?) as DATE) - CAST((?) as DATE) + 1) as averageServices  \n" +
                    "from " + labTableName + " inner join Laboratories on (" + labTableName + ".laboratoryId = Laboratories.id)\n" +
                    "inner join " +  instTable + " on (" + labTableName + "." + instRowId + " = "+ instTable+".id)\n" +
                    "where " + instTable +".id = ? and " + labTableName + ".serviceDate >= ? \n" +
                    "and  " + labTableName +".serviceDate <= ?\n" +
                    "  and Laboratories.id=?" +
                    "group by Laboratories.id, Laboratories.name;";

            return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(LaboratoryServiceStatisticEntity.class),
                    endDate, beginDate, institutionId, beginDate, endDate, labId);
        }
    }

    public List<LaboratoryEntity> getAll() {
        String statementString = "Select * from Laboratories;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(LaboratoryEntity.class));
    }

    public List<LaboratoryEntity> getLabsWithSuchType(LaboratoryTypes type) {
        String labTypeTableName = LaboratoryTypes.labTypeToTableName(type);
        String statementString = "Select laboratories.id as id, laboratories.name as name from laboratories inner join " + labTypeTableName + " t on laboratories.id = t.labid;";
        return jdbcTemplate.query(statementString, BeanPropertyRowMapper.newInstance(LaboratoryEntity.class));
    }
}
