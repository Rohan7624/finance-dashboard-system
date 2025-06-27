package net.engineeringdigest.journalApp.controller;

import net.engineeringdigest.journalApp.Entity.JournalEntry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/journal")
public class JournalEntryController {

    private Map<Long , JournalEntry> JournalEntry = new HashMap<>();

    @GetMapping("/abc")
    public List<JournalEntry> GetAll(){
        return new ArrayList<>();
    }
}
