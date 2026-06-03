package com.finance.dashboard.system.zorvyn.service;


import com.finance.dashboard.system.zorvyn.dto.CategorySummary;
import com.finance.dashboard.system.zorvyn.dto.MonthlySummary;
import com.finance.dashboard.system.zorvyn.dto.SummaryResponse;
import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DashboardService {

    private final FinancialRecordRepository repository;

    public DashboardService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    // 📊 Overall Summary
    public SummaryResponse getSummary() {
        Double income = repository.getTotalIncome();
        Double expense = repository.getTotalExpense();

        System.out.println("DashboardService: Total Income = " + income + ", Total Expense = " + expense);
        System.out.println("DashboardService: Calculated Net Balance = " + (income != null && expense != null ? income - expense : "N/A"));
        return new SummaryResponse(
                income != null ? income : 0,
                expense != null ? expense : 0
        );
    }

    // 📂 Category Summary
    public List<CategorySummary> getCategorySummary() {
        return repository.getCategorySummary();
    }

    // 🕒 Recent Activity
    public List<FinancialRecord> getRecentActivity() {
        return repository.findTop5ByOrderByDateDesc();
    }

    // 📈 Monthly Trends
    public List<MonthlySummary> getMonthlyTrends() {
        return repository.getMonthlySummary();
    }
}