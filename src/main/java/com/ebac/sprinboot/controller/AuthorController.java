package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Author;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
public class AuthorController {

    @GetMapping("/autores")
    public List<Author> getAuthor() {
        return List.of(new Author());
    }

    @GetMapping("/autores/{id}")
    public Author getAuthor(@PathVariable int id) {
        return new Author();
    }

    @PostMapping("/autores")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) {
        return ResponseEntity.status(HttpStatus.CREATED).body(author);
    }

    @PutMapping("/autores/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) {
        return ResponseEntity.ok(author);
    }

    @DeleteMapping("/autores/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        return ResponseEntity.notFound().build();
    }
}
