package com.codeshop.effective.ui.controller;

import com.codeshop.effective.infrastructure.repository.InMemoryCatRepository;
import com.codeshop.effective.infrastructure.repository.InMemoryOwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/persistence")
public class PersistenceController {
    private final InMemoryCatRepository catRepository;
    private final InMemoryOwnerRepository ownerRepository;

    @PostMapping("/save")
    public ResponseEntity<Void> save() {
        catRepository.saveToFile("cats.txt");
        ownerRepository.saveToFile("owners.txt");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/load")
    public ResponseEntity<Void> load() {
        catRepository.loadFromFile("cats.txt");
        ownerRepository.loadFromFile("owners.txt");
        return ResponseEntity.ok().build();
    }
}
