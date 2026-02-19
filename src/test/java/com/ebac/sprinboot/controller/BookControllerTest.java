package com.ebac.sprinboot.controller;

//import com.ebac.sprinboot.dto.Author;
import com.ebac.sprinboot.dto.Book;
//import com.ebac.sprinboot.dto.User;
import com.ebac.sprinboot.dto.User;
import com.ebac.sprinboot.sevice.BookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookControllerTest {

    @Mock
    BookService service;
    @InjectMocks
    BookController controller;

    @Test
    void getBooks() {
        int numBooks = 4;
        List<Book> expected = crearBooks(numBooks);

        when(service.getAllBooks()).thenReturn(expected);

        List<Book> actual = controller.getBooks().getResponse().getBody();

        assertEquals (numBooks, actual.size());
        assertEquals (expected, actual);
    }

    @Test
    void getAuthorsEmpty() {

        when(service.getAllBooks()).thenReturn(List.of());

        List<Book> actual = controller.getBooks().getResponse().getBody();

        assertTrue(actual.isEmpty());
        verify(service, times(1)).getAllBooks();
    }

    @Test
    void getBook() {
        int idBook = 1;
        Optional<Book> expected = Optional.of(crearBooks(idBook).get(0));
        when(service.getById(1)).thenReturn(expected);
        ResponseEntity<Book> bookRE = controller.getBook(idBook).getResponse();
        Book actualBook = bookRE.getBody();

        assertEquals (200,bookRE.getStatusCode().value());
        assertEquals ("Título_1",actualBook.getTitle());
    }

    @Test
    void getBookNotFound() {
        Optional<Book> expected = Optional.empty();
        when(service.getById(1)).thenReturn(expected);
        ResponseEntity<Book> bookRE = controller.getBook(1).getResponse();
        Book actualBook = bookRE.getBody();

        assertEquals(404,bookRE.getStatusCode().value());
        assertTrue (Objects.isNull(actualBook));

    }

    @Test
    void createBook() throws URISyntaxException {
        Book expected = crearBooks(1).get(0);

        when(service.CreateBook(expected)).thenReturn(expected);

        ResponseEntity<Book> bookRE = controller.createBook(expected).getResponse();
        Book actualBook = bookRE.getBody();

        assertEquals (201,bookRE.getStatusCode().value());
        assertTrue (Objects.isNull(actualBook));
    }

    @Test
    void updateBook() throws URISyntaxException {
        int idBook = 1;

        Book bookDefault = crearBooks(idBook).get(0);

        Book bookActualizado = new Book();
        bookActualizado.setTitle("actualizado");

        when(service.getById(idBook)).thenReturn(Optional.of(bookDefault));
        doNothing().when(service).UpdateBook(bookActualizado);

        ResponseEntity<Book> bookRE = controller.updateBook((int) idBook, bookActualizado).getResponse();
        Book actualBook = bookRE.getBody();

        assertEquals (200,bookRE.getStatusCode().value());
        assertTrue(Objects.nonNull(actualBook));
        assertEquals ("actualizado",actualBook.getTitle());


    }
    @Test
    void updateBookNotFound() throws URISyntaxException {
        Integer idBook = 1;
        //Se crea usuario default
        Book bookDefault = crearBooks(idBook).get(0);

        //Se crea usuario actualizado
        Book bookActualizado = new Book();
        bookActualizado.setTitle("actualizado");

        when(service.getById(idBook)).thenReturn(Optional.empty());

        ResponseEntity<Book> bookRE = controller.updateBook((int) idBook, bookActualizado).getResponse();
        Book actualBook = bookRE.getBody();

        assertEquals (404,bookRE.getStatusCode().value());
        assertNull(actualBook);
        verify(service, never()).UpdateBook(bookActualizado);

    }

    @Test
    void deleteBook() {
        int idBook = 1;

        doNothing().when(service).DeleteBook(idBook);

        ResponseEntity<Void> userRE = controller.deleteBook(idBook).getResponse();

        assertEquals (204,userRE.getStatusCode().value());
        verify(service, times(1)).DeleteBook(idBook);

    }

    public static List<Book> crearBooks(int numBooks) {

        return IntStream.range(1, numBooks + 1).mapToObj(i -> {
            Book book = new Book();
            book.setIdBook(i);
            book.setTitle("Título_" + i);
            book.setPublicationYear(2000+i);
            book.setIsbn("ISBN_" + i);
            return book;
        }).collect(Collectors.toList());

    };
}