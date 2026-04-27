package com.expense_tracker.repository;

import com.expense_tracker.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

// A interface *JpaRepository* te retorna funcoes de banco como: save, findAll e delete;
// A interface do JPA precisa de dois parametros ( entidade, tipo do ID )
// Aplica um Optional pra retorar algo especifico;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
