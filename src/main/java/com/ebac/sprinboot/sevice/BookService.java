package com.ebac.sprinboot.sevice;

import com.ebac.sprinboot.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BooksRepository booksRepository;

    public Book CreateBook(Book book) {
        return booksRepository.save(book);
    }

    public Optional<Book> getById(int id) {
        return booksRepository.findById(id);
    }

    public List<Book> getAllBooks() {
        return booksRepository.findAll();
    }

    public void UpdateBook(Book book) {
        booksRepository.save(book);
    }

    public void DeleteBook(int id) {
        booksRepository.deleteById(id);
    }
}
