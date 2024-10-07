package com.codeshop.effective.application.service;

import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.repository.CatRepository;
import com.codeshop.effective.domain.service.CatFeedingService;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class FeedingService {
    private final CatRepository catRepository;
    private final CatFeedingService catFeedingService;

    public Cat feedCat(UUID catId, int amount) {
        return catRepository.findById(catId)
            .map(cat -> {
                catFeedingService.recordFeeding(cat, amount);
                return cat;
            })
            .map(catRepository::save)
            .orElseThrow();
    }
}
