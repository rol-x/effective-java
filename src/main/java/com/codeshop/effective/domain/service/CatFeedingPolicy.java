package com.codeshop.effective.domain.service;

import com.codeshop.effective.domain.model.Cat;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

// 3. Singleton za pomocą prywatnego konstruktora
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CatFeedingPolicy {
    private static final CatFeedingPolicy INSTANCE = new CatFeedingPolicy();
    public static final int MIN_FEEDING_INTERVAL_HOURS = 4;
    public static final int MAX_FEEDING_PORTION_GRAMS = 100;

    public static CatFeedingPolicy getInstance() {
        return INSTANCE;
    }

    public void validateFeeding(Cat cat, int foodAmount) {
        if (cat.getLastFedTime() != null
            && cat.getLastFedTime().plusHours(MIN_FEEDING_INTERVAL_HOURS).isAfter(LocalDateTime.now())) {
            throw new IllegalStateException("Cat has been fed too recently.");
        }
        if (foodAmount <= 0) {
            throw new IllegalStateException("Food amount must be greater than 0.");
        }
        if (foodAmount > MAX_FEEDING_PORTION_GRAMS) {
            throw new IllegalStateException("Food amount cannot be greater than %d.".formatted(MAX_FEEDING_PORTION_GRAMS));
        }
    }
}
