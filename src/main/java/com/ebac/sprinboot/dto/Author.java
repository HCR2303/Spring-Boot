package com.ebac.sprinboot.dto;

public class Author {
    private String name;
    private String lastName;
    private String biography;
    private String mostFamousTitle;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getBiography() {
        return biography;
    }

    public void setBiography(String biography) {
        this.biography = biography;
    }

    public String getMostFamousTitle() {
        return mostFamousTitle;
    }

    public void setMostFamousTitle(String mostFamousTitle) {
        this.mostFamousTitle = mostFamousTitle;
    }
}
