package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Book;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class BookController {
    @GetMapping("/libros")
    public List<Book> getBooks() {
        return List.of(new Book());
    }

    @GetMapping("/libros/{id}")
    public Book getBook(@PathVariable int id) {
        return new Book();
    }

    @PostMapping("/libros")
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(book);
    }

    @PutMapping("/lobros/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) {
        return ResponseEntity.ok(book);
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        return ResponseEntity.notFound().build();
    }
}
