package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.Expense;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
}
