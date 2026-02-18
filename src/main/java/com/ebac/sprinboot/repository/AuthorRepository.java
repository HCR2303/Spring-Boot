package com.ebac.sprinboot.repository;

import com.ebac.sprinboot.dto.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author,Integer> {
}
