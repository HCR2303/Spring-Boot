package com.ebac.sprinboot.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
@Data
@Entity
@Table(name = "usuarios")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUsuario;

    private String name;
    private String email;
    private String password;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "booksByUser", // Nombre de la tabla intermedia que se creará sola
            joinColumns = @JoinColumn(name = "user_id"), // Columna para el ID del usuario
            inverseJoinColumns = @JoinColumn(name = "book_id") // Columna para el ID del libro
    )
    private List<Book> borrowedBooks=new ArrayList<>();


}
