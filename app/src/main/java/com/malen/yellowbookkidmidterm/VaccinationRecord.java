package com.malen.yellowbookkidmidterm;

public class VaccinationRecord {
    private int id;
    private int kidId;
    private int vaccineId;
    private String injectionDate;
    private String followUpDate;

    public VaccinationRecord(int id, int kidId, int vaccineId, String injectionDate, String followUpDate) {
        this.id = id;
        this.kidId = kidId;
        this.vaccineId = vaccineId;
        this.injectionDate = injectionDate;
        this.followUpDate = followUpDate;
    }

    // Getters and setters
}
