package com.expense_tracker.controller;

import com.expense_tracker.model.Expense;
import com.expense_tracker.service.ExpenseService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/expense/analytics")
public class ExpenseAnalyticsController {
    private final ExpenseService service;

    public ExpenseAnalyticsController(ExpenseService service) {
        this.service = service;
    }

    @GetMapping("/week/{userId}")
    public List<Expense> lastWeek(@PathVariable UUID userId) {
        return service.getLastWeek(userId);
    }

    @GetMapping("/month/{userId}")
    public List<Expense> lastMonth(@PathVariable UUID userId) {
        return service.getLastMonth(userId);
    }

    @GetMapping("/3months/{userId}")
    public List<Expense> last3Montsh(@PathVariable UUID userId) {
        return service.getLast3Months(userId);
    }

    @GetMapping("/filter/{userId}")
    public List<Expense> customRage(
            @PathVariable UUID userId,
            @RequestParam LocalDate start,
            @RequestParam LocalDate end
    ) {
        return service.getByCustomRange(userId, start, end);
    }
}
