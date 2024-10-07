package com.codeshop.effective.ui.controller;

import com.codeshop.effective.infrastructure.repository.InMemoryCatRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persistence")
public class PersistenceController {
    private final InMemoryCatRepository catRepository;

    @PostMapping("/save")
    public ResponseEntity<Void> save(@RequestParam String fileName) {
        catRepository.saveToFile(fileName);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
    public ResponseEntity<Void> load(@RequestParam String fileName) {
        catRepository.loadFromFile(fileName);
        return ResponseEntity.ok().build();
    }
}
