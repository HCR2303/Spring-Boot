package com.ebac.sprinboot.controller;

import com.ebac.sprinboot.dto.Author;
import com.ebac.sprinboot.sevice.AuthorService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import javax.swing.undo.AbstractUndoableEdit;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AuthorControllerTest {

//    @Mock
//    AuthorService service;
//
//    @InjectMocks
//    AuthorController authorController;
//
//    @Test
//    void getAuthors() {
//        int numAuthors = 4;
//        List<Author> expected = crearAuthors(numAuthors);
//        when(service.getAllAuthors()).thenReturn(expected);
//
//        List<Author> actual = authorController.getAuthors();
//        Integer actualSize = actual.size();
//
//        assertEquals (numAuthors, actual.size());
//        assertEquals (expected, actual);
//    }
//
//    @Test
//    void getAuthorsEmpty() {
//
//        when(service.getAllAuthors()).thenReturn(List.of());
//
//        List<Author> actual = authorController.getAuthors();
//
//        assertTrue(actual.isEmpty());
//        verify(service, times(1)).getAllAuthors();
//    }
//
//    @Test
//    void getAuthor() {
//        int idAuthor = 1;
//        Optional<Author> expected = Optional.of(crearAuthors(idAuthor).get(0));
//        when(service.getById(1)).thenReturn(expected);
//        ResponseEntity<Author> authorRE = authorController.getAuthor(idAuthor);
//        Author actualAuthor = authorRE.getBody();
//
//        assertEquals (200,authorRE.getStatusCode().value());
//        assertEquals ("Nombre_1",actualAuthor.getName());
//    }
//
//    @Test
//    void getAuthorEmpty() {
//
//        Optional<Author> expected = Optional.empty();
//        when(service.getById(1)).thenReturn(expected);
//        ResponseEntity<Author> authorRE = authorController.getAuthor(1);
//        Author actualAuthor = authorRE.getBody();
//
//        assertEquals(404,authorRE.getStatusCode().value());
//        assertTrue (Objects.isNull(actualAuthor));
//
//    }
//
//    @Test
//    void createAuthor() throws URISyntaxException {
//
//        Author expected = crearAuthors(1).get(0);
//
//        when(service.CreateAuthor(expected)).thenReturn(expected);
//
//        ResponseEntity<Author> authorRE = authorController.createAuthor(expected);
//        Author actualAuthor = authorRE.getBody();
//
//        assertEquals (201,authorRE.getStatusCode().value());
//        assertTrue (Objects.isNull(actualAuthor));
//    }
//
//    @Test
//    void updateAuthor() throws URISyntaxException {
//        int idAuthor = 1;
//        Author authorDefault = crearAuthors(idAuthor).get(0);
//
//        //Se crea usuario actualizado
//        Author authorActualizado = new Author();
//        authorActualizado.setName("actualizado");
//
//        when(service.getById(idAuthor)).thenReturn(Optional.of(authorDefault));
//        doNothing().when(service).UpdateAuthor(authorActualizado);
//
//        ResponseEntity<Author> authorRE = authorController.updateAuthor((int) idAuthor, authorActualizado);
//        Author actualAuthor = authorRE.getBody();
//
//        assertEquals (200,authorRE.getStatusCode().value());
//        assertTrue(Objects.nonNull(actualAuthor));
//        assertEquals ("actualizado",actualAuthor.getName());
//
//    }
//
//    @Test
//    void updateAuthorNotFound() throws URISyntaxException {
//        Integer idAuthor = 1;
//        //Se crea usuario default
//        Author usuarioDefault = crearAuthors(idAuthor).get(0);
//
//        //Se crea usuario actualizado
//        Author authorActualizado = new Author();
//        authorActualizado.setName("actualizado");
//
//        when(service.getById(idAuthor)).thenReturn(Optional.empty());
//
//        ResponseEntity<Author> userRE = authorController.updateAuthor((int) idAuthor, authorActualizado);
//        Author actualUser = userRE.getBody();
//
//        assertEquals (404,userRE.getStatusCode().value());
//        assertNull(actualUser);
//        verify(service, never()).UpdateAuthor(authorActualizado);
//
//    }
//
//    @Test
//    void deleteAuthor() {
//        int idAuthor = 1;
//
//        doNothing().when(service).DeleteAuthor(idAuthor);
//
//        ResponseEntity<Void> userRE = authorController.deleteAuthor(idAuthor);
//
//        assertEquals (204,userRE.getStatusCode().value());
//        verify(service, times(1)).DeleteAuthor(idAuthor);
//
//    }
//
//    public static List<Author> crearAuthors(int numAuthors) {
//
//        return IntStream.range(1, numAuthors + 1).mapToObj(i -> {
//            Author author = new Author();
//            author.setId(i);
//            author.setName("Nombre_"+i);
//            author.setLastName("Apellido_"+i);
//            author.setBiography(String.format("Biografía_"+i));
//            author.setMostFamousTitle("Libro famoso_"+i);
//            return author;
//        }).collect(Collectors.toList());
//
//    };
}