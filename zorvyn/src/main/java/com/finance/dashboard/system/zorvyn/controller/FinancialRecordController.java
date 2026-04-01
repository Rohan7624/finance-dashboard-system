package com.finance.dashboard.system.zorvyn.controller;


import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.model.TransactionType;
import com.finance.dashboard.system.zorvyn.service.FinancialRecordService;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/records")
public class FinancialRecordController {

    private final FinancialRecordService service;

    public FinancialRecordController(FinancialRecordService service) {
        this.service = service;
    }

    // ➕ Create
    @PostMapping
    public FinancialRecord create(@RequestBody FinancialRecord record) {
        return service.createRecord(record);
    }

    @PostMapping("/bulk")
    public List<FinancialRecord> createBulk(@RequestBody List<FinancialRecord> records) {
        return service.saveAll(records);
    }
    // 📄 Get all
    @GetMapping
    public List<FinancialRecord> getAll() {
        return service.getAllRecords();
    }

    // ✏ Update
    @PutMapping("/{id}")
    public FinancialRecord update(@PathVariable Long id,
                                  @RequestBody FinancialRecord record) {
        return service.updateRecord(id, record);
    }

    // ❌ Delete
    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id) {
        service.deleteRecord(id);
        return "Record deleted";
    }

    // 🔍 Filter by type
    @GetMapping("/type")
    public List<FinancialRecord> getByType(@RequestParam TransactionType type) {
        return service.filterByType(type);
    }

    // 🔍 Filter by category
    @GetMapping("/category")
    public List<FinancialRecord> getByCategory(@RequestParam String category) {
        return service.filterByCategory(category);
    }

    // 🔍 Filter by date range
    @GetMapping("/date")
    public List<FinancialRecord> getByDateRange(
            @RequestParam String start,
            @RequestParam String end) {

        return service.filterByDateRange(
                LocalDate.parse(start),
                LocalDate.parse(end)
        );
    }
}