package com.expense_tracker.service;

import com.expense_tracker.model.Expense;
import com.expense_tracker.model.User;
import com.expense_tracker.repository.ExpenseRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ExpenseService {
    private final ExpenseRepository repository;
    private final UserService user;

    public ExpenseService(ExpenseRepository repository, UserService userService) {
        this.repository = repository;
        this.user = userService;
    }

    public Expense createExpense(UUID userId, Expense expense) {
        User user = this.user.findById(userId).orElseThrow(() -> new RuntimeException("User not Found"));

        expense.setUser(user);

        return repository.save(expense);
    }

    public List<Expense> getUserExpenses(UUID userId) {
        return repository.findByUserId(userId);
    }

    public void deleteExpense(UUID expenseId) {
        repository.deleteById(expenseId);
    }
}
