package io.umang.project.expense_service.service;

import io.umang.project.expense_service.client.BudgetClient;
import io.umang.project.expense_service.external.Budget;
import io.umang.project.expense_service.entity.Expense;
import io.umang.project.expense_service.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class ExpenseService {
    private final ExpenseRepository expenseRepository;
    private final BudgetClient budgetClient;
    private final NotificationService notificationService;

    public List<Expense> findAll() {
        return expenseRepository.findAll();
    }

    public Optional<Expense> findByCategory(String category) {
        log.info("Fetching expense by category");
        return expenseRepository.findByCategory(category);
    }

    public void recordExpense(Expense expense) {
        Optional<Expense> optionalExpense = expenseRepository.findByCategory(expense.getCategory());

        Expense addOrUpdateExpense = optionalExpense.map(existingExpense -> {
            existingExpense.setAmount(existingExpense.getAmount().add(expense.getAmount()));
            log.info("Expense updated successfully.");
            return existingExpense;
        }).orElseGet(() -> {
            log.info("Expense created successfully.");
            return new Expense(expense.getDescription(), expense.getAmount(), expense.getCategory());
        });

        Optional<Budget> optionalBudget = Optional.ofNullable(budgetClient.getBudget(expense.getCategory()));
        optionalBudget.ifPresent(budget -> {
            if (addOrUpdateExpense.getAmount().compareTo(budget.amount()) > 0) {
                notificationService.notifyBudgetExceedNotification(budget, addOrUpdateExpense);
            }
        });
        expenseRepository.save(addOrUpdateExpense);
    }

    public void deleteExpense(Expense expense) {
        Optional<Expense> existingExpense = expenseRepository.findByCategory(expense.getCategory());
        existingExpense.ifPresent(expenseRepository::delete);
    }

}
