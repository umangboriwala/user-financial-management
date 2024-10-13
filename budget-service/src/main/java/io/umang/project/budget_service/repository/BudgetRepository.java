package io.umang.project.budget_service.repository;

import io.umang.project.budget_service.entity.Budget;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BudgetRepository extends JpaRepository<Budget, Long> {
    Optional<Budget> findByCategory(String category);
}
