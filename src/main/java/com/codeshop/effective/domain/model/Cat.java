package com.codeshop.effective.domain.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
public class Cat implements Serializable {
    @Serial private static final long serialVersionUID = 1L;

    private final UUID id;
    private String name;
    private LocalDate birthDate;
    @JsonManagedReference
    private List<Owner> owners;
    private LocalDateTime lastFedTime;

    public FeedingRecord feed(int amount) {
        var feedingRecord = FeedingRecord.create(id, amount);   // 1. Statyczne metody fabryczne zamiast konstruktorów
        lastFedTime = feedingRecord.getDate();
        return feedingRecord;
    }

    @Override                                       // 12. Przedefiniowanie toString - zapewnienie czytelności
    public String toString() {
        return "Cat{id='%s', name='%s', birthDate=%s, owners='%s', lastFedTime='%s'}"
            .formatted(id, name, birthDate, owners.stream().map(Owner::getName).toList(), lastFedTime);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cat cat = (Cat) o;
        return Objects.equals(id, cat.id);          // 10. Przedefiniowanie equals - semantyka encji (równość po ID)
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);                    // 11. Przedefiniowanie hashCode - równomierny rozkład obiektów
    }

    public Cat addOwner(Owner owner) {
        if (!owners.contains(owner)) {
            owners.add(owner);
            owner.addCat(this);
        }
        return this;
    }
}
