package io.umang.project.expense_service.external;

import java.math.BigDecimal;

public record Budget(String category, BigDecimal amount) {
}
