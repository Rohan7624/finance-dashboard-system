package com.finance.dashboard.system.zorvyn.dto;

import lombok.Data;

@Data
public class SummaryResponse {

    private Double totalIncome;
    private Double totalExpense;
    private Double netBalance;

    public SummaryResponse(Double totalIncome, Double totalExpense) {
        this.totalIncome = totalIncome;
        this.totalExpense = totalExpense;
        this.netBalance = totalIncome - totalExpense;
    }

    // Getters
}