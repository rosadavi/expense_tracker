package com.expense_tracker.service;

import com.expense_tracker.model.Expense;
import com.expense_tracker.model.User;
import com.expense_tracker.repository.ExpenseRepository;
import org.springframework.cglib.core.Local;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
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

    public List<Expense> getLastWeek(UUID userId) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusWeeks(1);

        return repository.findByUserIdAndDateBetween(userId, start, end);
    }

    public List<Expense> getLastMonth(UUID userId) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(1);

        return repository.findByUserIdAndDateBetween(userId, start, end);
    }

    public List<Expense> getLast3Months(UUID userId) {
        LocalDate end = LocalDate.now();
        LocalDate start = end.minusMonths(3);

        return repository.findByUserIdAndDateBetween(userId, start, end);
    }

    public List<Expense> getByCustomRange(UUID userId, LocalDate start, LocalDate end) {
        return repository.findByUserIdAndDateBetween(userId, start, end);
    }
}
