package com.expensesplitter.expense_splitter.controller;

import com.expensesplitter.expense_splitter.dto.BalanceDTO;
import com.expensesplitter.expense_splitter.entity.Group;
import com.expensesplitter.expense_splitter.service.BalanceService;
import com.expensesplitter.expense_splitter.repository.GroupRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/balances")
public class BalanceController {

    private final BalanceService balanceService;
    private final GroupRepository groupRepository;

    public BalanceController(BalanceService balanceService, GroupRepository groupRepository) {
        this.balanceService = balanceService;
        this.groupRepository = groupRepository;
    }

    @GetMapping("/{groupId}")
    public ResponseEntity<List<BalanceDTO>> getBalances(@PathVariable Long groupId) {
        Group group = groupRepository.findById(groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        List<BalanceDTO> balances = balanceService.calculateBalances(group);
        return ResponseEntity.ok(balances);
    }
}
