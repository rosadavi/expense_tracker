package com.expense_tracker.controller;

import com.expense_tracker.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// Injeta UserService
// Cria metodo POST para cadastrar usuarios com ResponseEntity que retorna algo do tipo User
// Valida o tipo do body onde deve condizir com o tipo User e retorna um .ok do tipo responseEntity ao utilizar o service create passando o user do body

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService service;

    public UserController(UserService service) {
        this.service = service;
    }
}
