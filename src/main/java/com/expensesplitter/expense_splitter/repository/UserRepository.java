package com.expensesplitter.expense_splitter.repository;

import com.expensesplitter.expense_splitter.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
