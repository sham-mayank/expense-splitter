package com.expensesplitter.expense_splitter.dto;

import java.math.BigDecimal;

public class CreateExpenseRequest {

    private Long groupId;
    private Long paidByUserId;
    private String description;
    private BigDecimal totalAmount;

    // Getters & Setters
    public Long getGroupId() { return groupId; }
    public void setGroupId(Long groupId) { this.groupId = groupId; }

    public Long getPaidByUserId() { return paidByUserId; }
    public void setPaidByUserId(Long paidByUserId) { this.paidByUserId = paidByUserId; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
