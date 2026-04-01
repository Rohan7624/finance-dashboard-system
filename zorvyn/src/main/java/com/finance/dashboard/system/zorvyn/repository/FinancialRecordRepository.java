package com.finance.dashboard.system.zorvyn.repository;


import com.finance.dashboard.system.zorvyn.dto.CategorySummary;
import com.finance.dashboard.system.zorvyn.dto.MonthlySummary;
import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.model.TransactionType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;

public interface FinancialRecordRepository extends JpaRepository<FinancialRecord, Long> {

    List<FinancialRecord> findByType(TransactionType type);

    List<FinancialRecord> findByCategory(String category);

    List<FinancialRecord> findByDateBetween(LocalDate start, LocalDate end);

    List<FinancialRecord> findByTypeAndCategory(TransactionType type, String category);
    // Total Income
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'INCOME'")
    Double getTotalIncome();

    // Total Expense
    @Query("SELECT SUM(f.amount) FROM FinancialRecord f WHERE f.type = 'EXPENSE'")
    Double getTotalExpense();

    // Category wise totals
    @Query("SELECT f.category as category, SUM(f.amount) as total " +
            "FROM FinancialRecord f GROUP BY f.category")
    List<CategorySummary> getCategorySummary();

    // Recent activity (last 5)
    List<FinancialRecord> findTop5ByOrderByDateDesc();

    // Monthly trend
    @Query("SELECT FUNCTION('DATE_FORMAT', f.date, '%Y-%m') as month, " +
            "SUM(f.amount) as total " +
            "FROM FinancialRecord f GROUP BY month ORDER BY month")
    List<MonthlySummary> getMonthlySummary();
}