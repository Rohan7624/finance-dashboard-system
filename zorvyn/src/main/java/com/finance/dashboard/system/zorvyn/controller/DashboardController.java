package com.finance.dashboard.system.zorvyn.controller;

import com.finance.dashboard.system.zorvyn.dto.CategorySummary;
import com.finance.dashboard.system.zorvyn.dto.MonthlySummary;
import com.finance.dashboard.system.zorvyn.dto.SummaryResponse;
import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.service.DashboardService;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Dashboard APIs", description = "APIs for financial dashboard analytics")
@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private final DashboardService service;

    public DashboardController(DashboardService service) {
        this.service = service;
    }

    // 📊 Total Income, Expense, Balance
    @Operation(summary = "Get overall financial summary",
            description = "Returns total income, total expenses, and net balance")
    @GetMapping("/summary")
    @PreAuthorize("hasAnyRole('ANALYST','ADMIN')")
    public SummaryResponse getSummary() {
        return service.getSummary();
    }

    // 📂 Category Wise Totals
    @Operation(summary = "Get category-wise summary",
            description = "Returns total amount grouped by category")
    @GetMapping("/category")
    @PreAuthorize("hasAnyRole('ANALYST','ADMIN')")
    public List<CategorySummary> getCategorySummary() {
        return service.getCategorySummary();
    }

    // 🕒 Recent Activity
    @Operation(summary = "Get recent transactions",
            description = "Fetches latest financial records")
    @GetMapping("/recent")
    public List<FinancialRecord> getRecentActivity() {
        return service.getRecentActivity();
    }

    // 📈 Monthly Trends
    @Operation(summary = "Get monthly trends",
            description = "Returns month-wise income and expense trends")
    @GetMapping("/monthly")
    public List<MonthlySummary> getMonthlyTrends() {
        return service.getMonthlyTrends();
    }
}