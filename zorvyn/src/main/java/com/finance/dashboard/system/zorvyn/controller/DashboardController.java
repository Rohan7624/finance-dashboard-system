package com.finance.dashboard.system.zorvyn.controller;


import com.finance.dashboard.system.zorvyn.dto.CategorySummary;
import com.finance.dashboard.system.zorvyn.dto.MonthlySummary;
import com.finance.dashboard.system.zorvyn.dto.SummaryResponse;
import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.service.DashboardService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    // 📊 Total Income, Expense, Balance
    @GetMapping("/summary")
    public SummaryResponse getSummary() {
        return service.getSummary();
    }

    // 📂 Category Wise Totals
    @GetMapping("/category")
    public List<CategorySummary> getCategorySummary() {
        return service.getCategorySummary();
    }

    // 🕒 Recent Activity
    @GetMapping("/recent")
    public List<FinancialRecord> getRecentActivity() {
        return service.getRecentActivity();
    }

    // 📈 Monthly Trends
    @GetMapping("/monthly")
    public List<MonthlySummary> getMonthlyTrends() {
        return service.getMonthlyTrends();
    }
}