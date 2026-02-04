package com.ebac.sprinboot.dto;

import jakarta.persistence.*;

@Entity
@Table(name="autores")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String lastName;
    private String biography;
    private String mostFamousTitle;

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
