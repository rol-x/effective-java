package com.codeshop.effective.domain.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@Builder
public class Cat implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private final UUID id;
    private String name;
    private int age;
    private String favoriteFood;
    private LocalDateTime lastFedTime;

    public FeedingRecord feed(int amount) {
        lastFedTime = LocalDateTime.now();
        return FeedingRecord.create(amount, lastFedTime); // 1. Statyczne metody fabryczne zamiast konstruktorów
    }

    @Override
    public String toString() {
        return "Cat{id='%s', name='%s', age=%d, favoriteFood='%s', lastFedTime='%s'}"
            .formatted(id, name, age, favoriteFood, lastFedTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
