package io.umang.project.expense_service.client;

import io.umang.project.expense_service.external.Budget;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient("BUDGET-SERVICE")
public interface BudgetClient {

    @GetMapping("/api/budgets/{category}")
    Budget getBudget(@PathVariable("category") String category);
}
