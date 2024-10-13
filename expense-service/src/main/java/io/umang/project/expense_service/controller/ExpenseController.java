package io.umang.project.expense_service.controller;

import io.umang.project.expense_service.entity.Expense;
import io.umang.project.expense_service.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {

    private final ExpenseService expenseService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Expense> getAllExpenses() {
        return expenseService.findAll();
    }

    @GetMapping("/{category}")
    public ResponseEntity<Expense> getExpenseByCategory(@PathVariable String category) {
        Optional<Expense> expense = expenseService.findByCategory(category);
        return expense.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String addExpense(@RequestBody Expense expense) {
        expenseService.recordExpense(expense);
        return "Expense category '"+expense.getCategory()+"' added successfully.";
    }

    @DeleteMapping
    public ResponseEntity<String> deleteExpense(@RequestParam String category) {
        Optional<Expense> existingExpense = expenseService.findByCategory(category);
        if(existingExpense.isPresent()) {
            expenseService.deleteExpense(existingExpense.get());
            return ResponseEntity.ok("Expense category '" + category + "' deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}
