package io.umang.project.budget_service.repository;

import io.umang.project.budget_service.entity.Budget;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;

@DataJpaTest
public class BudgetRepositoryTest {
    @Autowired
    private BudgetRepository budgetRepository;

    @Test
    public void testFindByCategory() {
        Budget budget = new Budget("Education", new BigDecimal("1000"));
        budgetRepository.save(budget);
        Assertions.assertThat(budgetRepository.findByCategory("Education")).isNotEmpty();
    }

    @Test
    public void testFindById() {
        Budget budget = new Budget("Health", new BigDecimal("200"));
        Budget savedBudget = budgetRepository.save(budget);
        Assertions.assertThat(budgetRepository.findById(savedBudget.getId())).isNotEmpty();
    }
}