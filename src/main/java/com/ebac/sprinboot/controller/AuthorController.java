package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Author;
import com.ebac.sprinboot.sevice.AuthorService;
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
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping("/autores")
    public ResponseWrapper<List<Author>> getAuthors() {
        ResponseEntity<List<Author>> RE= ResponseEntity.ok(authorService.getAllAuthors());
        if(RE.getBody().size()>0){
            return new ResponseWrapper<>(true,"Se obtuvo lista de autores",RE);
        }else {
            return new ResponseWrapper<>(false,"No existen libros",RE);
        }
    }

    @GetMapping("/autores/{id}")
    public ResponseWrapper<Author> getAuthor(@PathVariable int id) {
        Optional<Author> authorFound= authorService.getById(id);
        if(authorFound.isPresent()){
            log.info("Obteniendo el autor con el ID:{}",id);
            ResponseEntity<Author> RE= ResponseEntity.ok(authorFound.get());
            return new ResponseWrapper<>(true,"Se obtuvo el author",RE);
        }else {
            log.warn("No existen un autor con el ID:{}",id);
            ResponseEntity<Author> RE= ResponseEntity.notFound().build();
            return new ResponseWrapper<>(false,"No se cuenta con registro del autor",RE);
        }
    }

    @PostMapping("/autores")
    public ResponseWrapper<Author> createAuthor(@RequestBody Author author) throws URISyntaxException {
        authorService.CreateAuthor(author);
        String name=author.getName();
        log.info("Creando el autor {}",name);
        ResponseEntity<Author> RE= ResponseEntity.created(new URI("/autores")).build();
        return new ResponseWrapper<>(true,"Se crea autor",RE);
    }

    @PutMapping("/autores/{id}")
    public ResponseWrapper<Author> updateAuthor(@PathVariable int id, @RequestBody Author author) throws URISyntaxException {
        Optional<Author> authorFound= authorService.getById(id);
        if(authorFound.isPresent()){
            author.setIdAuthor(authorFound.get().getIdAuthor());
            authorService.UpdateAuthor(author);
            log.info("Actualizando el autor con el ID:{}",id);
            ResponseEntity<Author> RE = ResponseEntity.ok(author);
            return new ResponseWrapper<>(true,"Se actualiza autor",RE);
        }else{
            log.warn("No existe un autor con el ID:{}",id);
            ResponseEntity<Author> RE= ResponseEntity.notFound().build();
            return new ResponseWrapper<>(false,"No se actualizó autor",RE);
        }
    }

    @DeleteMapping("/autores/{id}")
    public ResponseWrapper<Void> deleteAuthor(@PathVariable int id) {
        authorService.DeleteAuthor(id);
        log.info("Eliminando el autor con el ID:{}",id);
        ResponseEntity<Void> RE= ResponseEntity.noContent().build();
        return new ResponseWrapper<>(true,"Se elimina el autor",RE);
    }
}
