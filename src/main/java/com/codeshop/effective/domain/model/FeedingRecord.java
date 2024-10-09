package com.codeshop.effective.domain.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@ToString                                                       // 12. Przedefiniowanie toString za pomocą Lomboka
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class FeedingRecord {
    private final UUID catId;
    private int amount;
    private LocalDateTime date;

    public static FeedingRecord create(UUID catId, int amount) {
        return new FeedingRecord(catId, amount, LocalDateTime.now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FeedingRecord that = (FeedingRecord) o;
        return Objects.equals(this.date, that.date)    // 10. Przedefiniowanie equals - semantyka wartości (równość pól)
            && Objects.equals(this.catId, that.catId)
            && this.amount == that.amount;
    }

    @Override
    public int hashCode() {                             // 11. Przedefiniowanie hashCode - równomierny rozkład obiektów
        int result = Objects.hashCode(catId);
        result = 31 * result + Objects.hashCode(amount);
        result = 31 * result + Objects.hashCode(date);
        return result;
    }
}
