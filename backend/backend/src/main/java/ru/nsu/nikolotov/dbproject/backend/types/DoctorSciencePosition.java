package ru.nsu.nikolotov.dbproject.backend.types;

public enum DoctorSciencePosition {
    PROFESSOR("professor"),
    DOCENT("docent"),
    NONE(null);

    private final String positionString;

    private DoctorSciencePosition(String positionString) {this.positionString = positionString;}

    public  String toString() {
        return this.positionString;
    }

    public static DoctorSciencePosition fromString(String fromString) {
        if (fromString == null) {
            return NONE;
        }
        if (fromString.toLowerCase().equals(PROFESSOR.positionString)) {
            return PROFESSOR;
        } else if (fromString.toLowerCase().equals(DOCENT.positionString)) {
            return DOCENT;
        }

        return NONE;
    }

}
