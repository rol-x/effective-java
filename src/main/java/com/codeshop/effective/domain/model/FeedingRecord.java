package com.codeshop.effective.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedingRecord {
    private int amount;
    private LocalDateTime date;

    public static FeedingRecord create(int amount, LocalDateTime date) {
        return new FeedingRecord(amount, date);
    }
}
