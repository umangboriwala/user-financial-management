package io.umang.project.budget_service.service;

import static org.mockito.Mockito.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.umang.project.budget_service.entity.Budget;
import io.umang.project.budget_service.repository.BudgetRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

@SpringBootTest
public class BudgetServiceTest {
    @Mock
    private BudgetRepository budgetRepository;

    @InjectMocks
    private BudgetService budgetService;

    @Test
    public void testServiceReturningSavedBudget() {
        Budget budget = new Budget("Travel", new BigDecimal("500"));
        when(budgetRepository.save(Mockito.any(Budget.class))).thenReturn(budget);
        Budget created = budgetService.saveOrUpdateBudget(budget);
        assertThat(created.getCategory(), is(budget.getCategory()));
    }
}