package ru.nsu.nikolotov.dbfrontend.types;

import java.util.Locale;

public enum DoctorScienceRank {
    NONE (null),
    CANDIDATE_OF_SCIENCE("candidate of science"),
    DOCTOR_OF_SCIENCE("doctor of science");

    private final String rankString;

    private DoctorScienceRank(String rank) {this.rankString = rank;}

    public String toString() {
        return this.rankString;
    }

    public static DoctorScienceRank fromString(String fromString) {
        if (fromString == null) {
            return NONE;
        }
        if (fromString.toLowerCase().equals(CANDIDATE_OF_SCIENCE.rankString)) {
            return CANDIDATE_OF_SCIENCE;
        } else if (fromString.toLowerCase(Locale.ROOT).equals( DOCTOR_OF_SCIENCE.rankString) ) {
            return DOCTOR_OF_SCIENCE;
        }
        return NONE;
    }


    public static String doctorRankToTableName(DoctorScienceRank rank) {
        switch (rank) {
            case CANDIDATE_OF_SCIENCE:
                return "candidatsofscience";
            case DOCTOR_OF_SCIENCE:
                return "doctorsofscience";
        }
        return null;
    }

}
