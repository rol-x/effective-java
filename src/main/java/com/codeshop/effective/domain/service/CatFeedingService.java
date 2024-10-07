package com.codeshop.effective.domain.service;

import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.model.FeedingRecord;
import org.springframework.stereotype.Service;

@Service
public class CatFeedingService {
    public FeedingRecord recordFeeding(Cat cat, int amount) {
        var feedingPolicy = CatFeedingPolicy.getInstance();

        feedingPolicy.validateFeeding(cat, amount);

        return cat.feed(amount);
    }
}
