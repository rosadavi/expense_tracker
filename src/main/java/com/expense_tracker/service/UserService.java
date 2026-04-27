package com.expense_tracker.service;

import com.expense_tracker.model.User;
import com.expense_tracker.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

// Servicos de user
// Injeta o repositorio do user na criacao do objeto UserService e o torna privado

@Service
public class UserService {

    private final UserRepository repository;

    public UserService(UserRepository repository) {
        this.repository = repository;
    }

    public User createUser(User user) {
        return repository.save(user);
    }

    public Optional<User> findById(UUID userId) {
        return repository.findById(userId);
    }
}
