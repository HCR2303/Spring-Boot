package com.ebac.sprinboot.sevice;

import com.ebac.sprinboot.dto.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    @Autowired
    AuthorRepository authorRepository;

    public Author CreateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public Optional<Author> getById(int id) {
        return authorRepository.findById(id);
    }

    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }

    public Author UpdateAuthor(Author author) {
        return authorRepository.save(author);
    }

    public void DeleteAuthor(int id) {
        authorRepository.deleteById(id);
    }
}
