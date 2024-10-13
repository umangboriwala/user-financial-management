package io.umang.project.expense_service.service;

import io.umang.project.expense_service.external.Budget;
import io.umang.project.expense_service.entity.Expense;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
@AllArgsConstructor
public class NotificationService {
    private final JavaMailSender javaMailSender;

    public void notifyBudgetExceedNotification(Budget budget, Expense expense) {
        log.info("Budget for " + budget.category() + " exceeded. Expense: " + expense.getDescription() + "of Category: " + expense.getCategory());
        String budgetCategory = budget.category();
        BigDecimal budgetAmount = budget.amount();
        String expenseDescription = expense.getDescription();
        BigDecimal expenseAmount = expense.getAmount();

        String messageBody = "Hello User,\n\n" +
                "Budget for " + budgetCategory + " is exceeded. Please find below details:" +
                "\n" +
                "Budget Category : " + budgetCategory + "\n" +
                "Budget Amount : " + budgetAmount + "\n" +
                "Expense Description : " + expenseDescription + "\n" +
                "Expense Amount : " + expenseAmount + "\n" +
                "\n\n" +
                "Thank you.";
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("uboriwala@gmail.com");
        message.setSubject("Alert - Budget for " + budgetCategory + " exceeded.");
        message.setText(messageBody);
        message.setFrom("no-reply@demomailtrap.com");

        javaMailSender.send(message);
    }
}
