package com.oxygen.oxygenspring.db.repository;

import com.oxygen.oxygenspring.db.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsersRepository extends JpaRepository<Users, Long> {
    Optional<Users> findByUserId(String userId);
}