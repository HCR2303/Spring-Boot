package com.ebac.sprinboot.dto;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Table(name="libros")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBook;

    private String title;
    private int publicationYear;
    private String isbn;

    @ManyToMany(mappedBy = "borrowedBooks")
    @JsonIgnore
    private List<User> users;

    @ManyToOne
    @JoinColumn(name = "idAuthor")
    @JsonBackReference
    private Author author;

}
