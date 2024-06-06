package com.malen.yellowbookkidmidterm.models;

public class VaccinationRecord {
    private int id;
    private int childId;
    private int vaccineId;
    private String injectionDate;
    private String nextFollowupDate;

    public VaccinationRecord(int id, int childId, int vaccineId, String injectionDate, String nextFollowupDate) {
        this.id = id;
        this.childId = childId;
        this.vaccineId = vaccineId;
        this.injectionDate = injectionDate;
        this.nextFollowupDate = nextFollowupDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getChildId() {
        return childId;
    }

    public void setChildId(int childId) {
        this.childId = childId;
    }

    public int getVaccineId() {
        return vaccineId;
    }

    public void setVaccineId(int vaccineId) {
        this.vaccineId = vaccineId;
    }

    public String getInjectionDate() {
        return injectionDate;
    }

    public void setInjectionDate(String injectionDate) {
        this.injectionDate = injectionDate;
    }

    public String getNextFollowupDate() {
        return nextFollowupDate;
    }

    public void setNextFollowupDate(String nextFollowupDate) {
        this.nextFollowupDate = nextFollowupDate;
    }
}