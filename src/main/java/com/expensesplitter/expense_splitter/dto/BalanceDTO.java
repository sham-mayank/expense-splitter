package com.expensesplitter.expense_splitter.dto;

import java.math.BigDecimal;

public class BalanceDTO {
    private String userName;
    private BigDecimal balance;

    public BalanceDTO(String userName, BigDecimal balance) {
        this.userName = userName;
        this.balance = balance;
    }

    public String getUserName() { return userName; }
    public BigDecimal getBalance() { return balance; }

    public void setUserName(String userName) { this.userName = userName; }
    public void setBalance(BigDecimal balance) { this.balance = balance; }
}
