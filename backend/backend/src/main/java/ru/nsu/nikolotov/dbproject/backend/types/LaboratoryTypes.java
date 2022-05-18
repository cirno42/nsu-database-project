package ru.nsu.nikolotov.dbproject.backend.types;

public enum LaboratoryTypes {
    BIOLOGICAL("biological"),
    PHYSIOLOGICAL("physiological"),
    BIOCHEMICAL("biochemical"),
    NONE(null);

    private final String labType;

    private LaboratoryTypes(String labType) {
        this.labType = labType;
    }

    public String toString() {
        return labType;
    }

    public static LaboratoryTypes fromString(String typeString) {
        if (typeString == null) {
            return NONE;
        }
        if (typeString.toLowerCase().equals(BIOLOGICAL.labType)) {
            return BIOLOGICAL;
        } else if (typeString.toLowerCase().equals(PHYSIOLOGICAL.labType)) {
            return  PHYSIOLOGICAL;
        } else if (typeString.toLowerCase().equals(BIOCHEMICAL.labType)) {
            return BIOCHEMICAL;
        }
        return NONE;
    }

    public static String labTypeToTableName(LaboratoryTypes type, MedicineInstitutionType institutionType) {
        String subString = "";
        if (institutionType == MedicineInstitutionType.POLYCLINIC) {
            subString = "polyclinic";
        } else if (institutionType == MedicineInstitutionType.HOSPITAL) {
            subString = "hospital";
        }
        switch (type) {
            case BIOLOGICAL:
                return "biologicallaboratoryservices" + subString;
            case PHYSIOLOGICAL:
                return "physiologicallaboratoryservices" + subString;
            case BIOCHEMICAL:
                return "biochemicallaboratoryservices" + subString;
        }
        return null;
    }
}
