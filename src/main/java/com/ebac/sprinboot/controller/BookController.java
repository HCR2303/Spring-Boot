package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Book;
import com.ebac.sprinboot.sevice.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/libros")
    public List<Book> getBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/libros/{id}")
    public ResponseEntity<Book> getBook(@PathVariable int id) {
        Optional<Book> bookFound = bookService.getById(id);
        return bookFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/libros")
    public ResponseEntity<Book> createBook(@RequestBody Book book) throws URISyntaxException {
        bookService.CreateBook(book);
        return ResponseEntity.created(new URI("/libros")).build();
    }

    @PutMapping("/libros/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book book) throws URISyntaxException {
        Optional<Book> bookFound = bookService.getById(id);
        if (bookFound.isPresent()) {
            book.setId(bookFound.get().getId());
            bookService.UpdateBook(book);
            return ResponseEntity.ok(book);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/libros/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable int id) {
        bookService.DeleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
