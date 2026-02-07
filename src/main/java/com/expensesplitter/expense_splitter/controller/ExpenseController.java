package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.dto.CreateExpenseRequest;
import com.expensesplitter.expense_splitter.entity.Expense;
import com.expensesplitter.expense_splitter.service.ExpenseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    @PostMapping
    public ResponseEntity<Expense> createExpense(
            @RequestBody CreateExpenseRequest request) {

        Expense expense = expenseService.createExpense(request);
        return ResponseEntity.ok(expense);
    }
}

