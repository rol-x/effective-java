package com.codeshop.effective.application.service;

import com.codeshop.effective.application.dto.FeedingRecordDto;
import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.repository.CatRepository;
import com.codeshop.effective.domain.service.CatFeedingService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FeedingService {
    private final CatRepository catRepository;
    private final CatFeedingService catFeedingService;

    public Cat feedCat(FeedingRecordDto feedingRecordDto) {
        return catRepository.findById(feedingRecordDto.catId())
            .map(cat -> {
                catFeedingService.recordFeeding(cat, feedingRecordDto.amount());
                return cat;
            })
            .map(catRepository::save)
            .orElseThrow();
    }
}
