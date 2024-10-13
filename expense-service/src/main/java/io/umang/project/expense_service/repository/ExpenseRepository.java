package io.umang.project.expense_service.repository;

import io.umang.project.expense_service.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    Optional<Expense> findByCategory(String category);
}
