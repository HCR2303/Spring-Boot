package com.ebac.sprinboot.repository;

import com.ebac.sprinboot.dto.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {
}
