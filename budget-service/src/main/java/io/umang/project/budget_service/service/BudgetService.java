package io.umang.project.budget_service.service;

import io.umang.project.budget_service.entity.Budget;
import io.umang.project.budget_service.repository.BudgetRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BudgetService {
    private final BudgetRepository budgetRepository;

    public List<Budget> findAll() {
        log.info("Fetching budget list from database. ");
        return budgetRepository.findAll();
    }
    public Optional<Budget> findByCategory(String category) {
        log.info("Fetching budget by category");
        return budgetRepository.findByCategory(category);
    }
    public Budget saveOrUpdateBudget(Budget budget) {
        Optional<Budget> optionalBudget = budgetRepository.findByCategory(budget.getCategory());

        Budget addOrUpdateBudget = optionalBudget.map(existingBudget -> {
            existingBudget.setAmount(existingBudget.getAmount().add(budget.getAmount()));
            log.info("Budget updated successfully.");
            return existingBudget;
        }).orElseGet(() -> {
            log.info("Budget created successfully.");
            return new Budget(budget.getCategory(), budget.getAmount());
        });

        return budgetRepository.save(addOrUpdateBudget);
    }
    public void deleteBudget(Budget budget) {
        Optional<Budget> existingBudget = budgetRepository.findByCategory(budget.getCategory());
        existingBudget.ifPresent(budgetRepository::delete);
    }
}
