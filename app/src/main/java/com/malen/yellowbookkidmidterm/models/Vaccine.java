package com.malen.yellowbookkidmidterm.models;

public class Vaccine {
    private int id;
    private String name;
    private String description;
    private int monthsDue;

    public Vaccine(int id, String name, String description, int monthsDue) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.monthsDue = monthsDue;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getMonthsDue() {
        return monthsDue;
    }

    public void setMonthsDue(int monthsDue) {
        this.monthsDue = monthsDue;
    }

    @Override
    public String toString() {
        return name;
    }
}