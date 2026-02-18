package com.ebac.sprinboot.repository;

import com.ebac.sprinboot.dto.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepository extends JpaRepository<Book,Integer> {
}
