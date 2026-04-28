package com.expense_tracker.controller;

import com.expense_tracker.model.Expense;
import com.expense_tracker.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense")
public class ExpenseController {
    private final ExpenseService service;

    public ExpenseController(ExpenseService service) {
        this.service = service;
    }

    @PostMapping("/{userId}")
    public ResponseEntity<Expense> create(
            @PathVariable UUID userId,
            @RequestBody Expense expense
    ) {
        return ResponseEntity.ok(service.createExpense(userId, expense));
    }

    @GetMapping("/{userId}")
    public ResponseEntity<List<Expense>> list(@PathVariable UUID userId) {
        return ResponseEntity.ok(service.getUserExpenses(userId));
    }

    @DeleteMapping("/{expenseId}")
    public ResponseEntity<Void> delete(@PathVariable UUID expenseId) {
        service.deleteExpense(expenseId);
        return ResponseEntity.noContent().build();
    }


}
