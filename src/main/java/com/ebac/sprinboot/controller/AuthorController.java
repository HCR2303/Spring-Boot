package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Author;
import com.ebac.sprinboot.sevice.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/autores")
    public List<Author> getAuthors() {
        return authorService.getAllAuthors();
    }

    @GetMapping("/autores/{id}")
    public ResponseEntity<Author> getAuthor(@PathVariable int id) {
        Optional<Author> authorFound= authorService.getById(id);
        return authorFound.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/autores")
    public ResponseEntity<Author> createAuthor(@RequestBody Author author) throws URISyntaxException {
        authorService.CreateAuthor(author);
        return ResponseEntity.created(new URI("/autores")).build();
    }

    @PutMapping("/autores/{id}")
    public ResponseEntity<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) throws URISyntaxException {
        Optional<Author> authorFound= authorService.getById(id);
        if(authorFound.isPresent()){
            author.setId(authorFound.get().getId());
            authorService.UpdateAuthor(author);
            return ResponseEntity.ok(author);
        }else{
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/autores/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable int id) {
        authorService.DeleteAuthor(id);
        return ResponseEntity.noContent().build();
    }
}
