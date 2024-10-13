package io.umang.project.budget_service.controller;

import io.umang.project.budget_service.entity.Budget;
import io.umang.project.budget_service.service.BudgetService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/budgets")
@RequiredArgsConstructor
public class BudgetController {
    private final BudgetService budgetService;

    @GetMapping("/")
    @ResponseStatus(HttpStatus.OK)
    public List<Budget> getAllBudgets() {
        return budgetService.findAll();
    }

    @GetMapping("/{category}")
    public ResponseEntity<Budget> getBudgetByCategory(@PathVariable String category) {
        Optional<Budget> budget = budgetService.findByCategory(category);
        return budget.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/")
    @ResponseStatus(HttpStatus.CREATED)
    public String addOrUpdateBudget(@RequestBody Budget budget) {
        budgetService.saveOrUpdateBudget(budget);
        return "Budget category '" + budget.getCategory() + "'  added/updated successfully.";
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBudget(@RequestParam String category) {
        Optional<Budget> existingBudget = budgetService.findByCategory(category);
        if(existingBudget.isPresent()) {
            budgetService.deleteBudget(existingBudget.get());
            return ResponseEntity.ok("Budget category '" + category + "' deleted successfully.");
        }
        return ResponseEntity.notFound().build();
    }
}
