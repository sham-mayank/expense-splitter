package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.dto.CreateExpenseRequest;
import com.expensesplitter.expense_splitter.entity.*;
import com.expensesplitter.expense_splitter.repository.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseSplitRepository splitRepository;
    private final GroupUserRepository groupUserRepository;
    private final GroupRepository groupRepository;
    private final UserRepository userRepository;

    public ExpenseService(
            ExpenseRepository expenseRepository,
            ExpenseSplitRepository splitRepository,
            GroupUserRepository groupUserRepository,
            GroupRepository groupRepository,
            UserRepository userRepository
    ) {
        this.expenseRepository = expenseRepository;
        this.splitRepository = splitRepository;
        this.groupUserRepository = groupUserRepository;
        this.groupRepository = groupRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public Expense createExpense(CreateExpenseRequest request) {

        // 1️⃣ Fetch Group
        Group group = groupRepository.findById(request.getGroupId())
                .orElseThrow(() -> new RuntimeException("Group not found"));

        // 2️⃣ Fetch PaidBy user
        User paidBy = userRepository.findById(request.getPaidByUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        // 3️⃣ Create Expense entity
        Expense expense = new Expense();
        expense.setDescription(request.getDescription());
        expense.setTotalAmount(request.getTotalAmount());
        expense.setGroup(group);
        expense.setPaidBy(paidBy);

        // 4️⃣ Save Expense
        Expense savedExpense = expenseRepository.save(expense);

        // 5️⃣ Fetch group members
        List<GroupUser> members = groupUserRepository.findByGroup(group);

        BigDecimal splitAmount = savedExpense.getTotalAmount()
                .divide(BigDecimal.valueOf(members.size()), 2, BigDecimal.ROUND_HALF_UP);

        List<ExpenseSplit> splits = new ArrayList<>();

        for (GroupUser member : members) {
            ExpenseSplit split = new ExpenseSplit();
            split.setExpense(savedExpense);
            split.setUser(member.getUser());
            split.setAmount(splitAmount);
            splits.add(split);
        }

        splitRepository.saveAll(splits);
        savedExpense.setSplits(splits);

        return savedExpense;
    }

}
