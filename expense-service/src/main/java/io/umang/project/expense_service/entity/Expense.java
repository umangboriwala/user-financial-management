package io.umang.project.expense_service.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "expense")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Expense {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private BigDecimal amount;
    private String category;

    public Expense(String description, BigDecimal amount, String category) {
        this.description = description;
        this.amount = amount;
        this.category = category;
    }
}
