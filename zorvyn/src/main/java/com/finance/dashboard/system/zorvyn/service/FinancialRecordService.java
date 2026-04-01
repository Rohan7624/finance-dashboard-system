package com.finance.dashboard.system.zorvyn.service;


import com.finance.dashboard.system.zorvyn.entity.FinancialRecord;
import com.finance.dashboard.system.zorvyn.model.TransactionType;
import com.finance.dashboard.system.zorvyn.repository.FinancialRecordRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class FinancialRecordService {

    private final FinancialRecordRepository repository;

    public FinancialRecordService(FinancialRecordRepository repository) {
        this.repository = repository;
    }

    public FinancialRecord createRecord(FinancialRecord record) {
        return repository.save(record);
    }

    public List<FinancialRecord> getAllRecords() {
        return repository.findAll();
    }

    public FinancialRecord updateRecord(Long id, FinancialRecord updated) {
        FinancialRecord record = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Record not found"));

        record.setAmount(updated.getAmount());
        record.setType(updated.getType());
        record.setCategory(updated.getCategory());
        record.setDate(updated.getDate());
        record.setNotes(updated.getNotes());

        return repository.save(record);
    }

    public void deleteRecord(Long id) {
        repository.deleteById(id);
    }

    // 🔍 Filtering
    public List<FinancialRecord> filterByType(TransactionType type) {
        return repository.findByType(type);
    }

    public List<FinancialRecord> filterByCategory(String category) {
        return repository.findByCategory(category);
    }

    public List<FinancialRecord> filterByDateRange(LocalDate start, LocalDate end) {
        return repository.findByDateBetween(start, end);
    }
    public List<FinancialRecord> saveAll(List<FinancialRecord> records) {
        return repository.saveAll(records);
    }
}