package com.finance.dashboard.system.zorvyn.controller;

import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.model.TransactionType;
import com.finance.dashboard.system.zorvyn.service.FinancialRecordService;

import jakarta.validation.Valid;

import io.swagger.v3.oas.annotations.*;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Financial Records APIs", description = "Manage income and expense records")
@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    private final FinancialRecordService service;

    public FinancialRecordController(FinancialRecordService service) {
        this.service = service;
    }

    // ➕ Create
    @Operation(summary = "Create a financial record",
            description = "Adds a new income or expense record")
    @PostMapping
    public FinancialRecord create(@Valid @RequestBody FinancialRecord record) {
        return service.createRecord(record);
    }

    // ➕ Bulk Create
    @Operation(summary = "Create multiple records",
            description = "Adds multiple financial records at once")
    @PostMapping("/bulk")
    public List<FinancialRecord> createBulk(
            @Valid @RequestBody List<FinancialRecord> records) {
        return service.saveAll(records);
    }

    // 📄 Get all
    @Operation(summary = "Get all records",
            description = "Fetch all financial records")
    @GetMapping
    @PreAuthorize("hasAnyRole('VIEWER','ANALYST','ADMIN')")
    public List<FinancialRecord> getAll() {
        return service.getAllRecords();
    }

    // ✏ Update
    @Operation(summary = "Update a record",
            description = "Update financial record by ID")
    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public FinancialRecord update(
            @PathVariable Long id,
            @RequestBody FinancialRecord record) {
        return service.updateRecord(id, record);
    }

    // ❌ Delete
    @Operation(summary = "Delete a record",
            description = "Delete financial record by ID")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String delete(@PathVariable Long id) {
        service.deleteRecord(id);
        return "Record deleted";
    }

    // 🔍 Filter by type
    @Operation(summary = "Filter by type",
            description = "Get records by transaction type (INCOME / EXPENSE)")
    @GetMapping("/type")
    public List<FinancialRecord> getByType(
            @Parameter(description = "Transaction type")
            @RequestParam TransactionType type) {
        return service.filterByType(type);
    }

    // 🔍 Filter by category
    @Operation(summary = "Filter by category",
            description = "Get records by category")
    @GetMapping("/category")
    public List<FinancialRecord> getByCategory(
            @Parameter(description = "Category name")
            @RequestParam String category) {
        return service.filterByCategory(category);
    }

    // 🔍 Filter by date range
    @Operation(summary = "Filter by date range",
            description = "Get records between start and end date (yyyy-MM-dd)")
    @GetMapping("/date")
    public List<FinancialRecord> getByDateRange(
            @Parameter(description = "Start date (yyyy-MM-dd)")
            @RequestParam String start,

            @Parameter(description = "End date (yyyy-MM-dd)")
            @RequestParam String end) {

        return service.filterByDateRange(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}