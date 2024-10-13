package io.umang.project.expense_service.service;

import io.umang.project.expense_service.entity.Expense;
import io.umang.project.expense_service.external.Budget;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import java.math.BigDecimal;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {
    @Mock
    private JavaMailSender javaMailSender;

    @InjectMocks
    private NotificationService notificationService;

    private Budget budget;
    private Expense expense;

    @BeforeEach
    public void setUp() {
        budget = new Budget("Groceries", new BigDecimal("500.00"));
        expense = new Expense("Grocery Shopping", new BigDecimal("600.00"), budget.category());
    }

    @Test
    void notifyBudgetExceedNotification_ShouldSendEmail() {
        String expectedSubject = "Alert - Budget for Groceries exceeded.";
        String expectedTo = "uboriwala@gmail.com";
        String from = "no-reply@demomailtrap.com";
        String messageBody = """
                Hello User,
                
                Budget for Groceries is exceeded. Please find below details:
                Budget Category : Groceries
                Budget Amount : 500.00
                Expense Description : Grocery Shopping
                Expense Amount : 600.00
                
                
                Thank you.""";

        notificationService.notifyBudgetExceedNotification(budget, expense);

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(expectedTo);
        message.setSubject(expectedSubject);
        message.setText(messageBody);

        Mockito.verify(javaMailSender).send(message);
    }
}
