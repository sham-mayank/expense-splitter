package com.expensesplitter.expense_splitter.service;

import com.expensesplitter.expense_splitter.dto.BalanceDTO;
import com.expensesplitter.expense_splitter.entity.*;
import com.expensesplitter.expense_splitter.repository.ExpenseRepository;
import com.expensesplitter.expense_splitter.repository.GroupUserRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.*;

@Service
public class BalanceService {

    private final ExpenseRepository expenseRepository;
    private final GroupUserRepository groupUserRepository;

    public BalanceService(ExpenseRepository expenseRepository,
                          GroupUserRepository groupUserRepository) {
        this.expenseRepository = expenseRepository;
        this.groupUserRepository = groupUserRepository;
    }

    public List<BalanceDTO> calculateBalances(Group group) {
        // Initialize balances for all users in the group
        Map<Long, BigDecimal> balances = new HashMap<>();
        List<GroupUser> members = groupUserRepository.findByGroup(group);
        for (GroupUser member : members) {
            balances.put(member.getUser().getId(), BigDecimal.ZERO);
        }

        // Iterate all expenses in the group
        for (Expense expense : group.getExpenses()) {
            BigDecimal totalAmount = expense.getTotalAmount();

            // Who paid
            Long paidById = expense.getPaidBy().getId();

            for (ExpenseSplit split : expense.getSplits()) {
                Long userId = split.getUser().getId();
                BigDecimal amount = split.getAmount();

                // If user owes money, subtract
                balances.put(userId, balances.get(userId).subtract(amount));

                // If user is payer, add total paid
                if (userId.equals(paidById)) {
                    balances.put(userId, balances.get(userId).add(totalAmount));
                }
            }
        }

        // Convert to BalanceDTO
        List<BalanceDTO> result = new ArrayList<>();
        for (GroupUser member : members) {
            result.add(new BalanceDTO(member.getUser().getName(), balances.get(member.getUser().getId())));
        }

        return result;
    }
}
