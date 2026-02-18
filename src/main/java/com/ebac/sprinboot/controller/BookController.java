package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Book;
import com.ebac.sprinboot.sevice.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;
@Slf4j
@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/libros")
    public ResponseWrapper<List<Book>> getBooks() {
        ResponseEntity<List<Book>> RE= ResponseEntity.ok(bookService.getAllBooks());
        if (bookService.getAllBooks().size()>0){
            return new ResponseWrapper<>(true,"Se obtuvo lista de books",RE);
        }else{
            return new ResponseWrapper<>(false,"No existen libros",RE);
        }
    }

    @GetMapping("/libros/{id}")
    public ResponseWrapper<Book> getBook(@PathVariable int id) {
        Optional<Book> bookFound = bookService.getById(id);
        if (bookFound.isPresent()){
            log.info("Obteniendo libro con ID:{}",id);
            ResponseEntity<Book> RE= ResponseEntity.ok(bookFound.get());
            return new ResponseWrapper<>(true,"Se encontro libro", RE);
        }else {
            log.warn("Buscando ID:{}",id);
            ResponseEntity<Book> RE= ResponseEntity.notFound().build();
            return new ResponseWrapper<>(false,"No existen coincidencias", RE);
        }
    }

    @PostMapping("/libros")
    public ResponseWrapper<Book> createBook(@RequestBody Book book) throws URISyntaxException {
        bookService.CreateBook(book);
        String title=book.getTitle();
        log.info("Creando libro:{}",title);
        ResponseEntity<Book> RE= ResponseEntity.created(new URI("/libros")).build();
        return new ResponseWrapper<>(true,"Se creo un libro", RE);
    }

    @PutMapping("/libros/{id}")
    public ResponseWrapper<Book> updateBook(@PathVariable int id, @RequestBody Book book) throws URISyntaxException {
        Optional<Book> bookFound = bookService.getById(id);
        if (bookFound.isPresent()) {
            book.setIdBook(bookFound.get().getIdBook());
            bookService.UpdateBook(book);
            ResponseEntity<Book> RE= ResponseEntity.ok(book);
            return  new ResponseWrapper<>(true,"Se actualizó libro", RE);
        } else {
            log.warn("No se pudo actualizar libro con ID:{}",id);
            ResponseEntity<Book> RE= ResponseEntity.notFound().build();
            return new ResponseWrapper<>(false,"No existen coincidencias", RE);
        }
    }

    @DeleteMapping("/libros/{id}")
    public ResponseWrapper<Void> deleteBook(@PathVariable int id) {
        bookService.DeleteBook(id);
        ResponseEntity<Void> RE= ResponseEntity.noContent().build();
        return new ResponseWrapper<>(true,"Libro eliminado", RE);
    }
}
