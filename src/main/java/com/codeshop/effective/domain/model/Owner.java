package com.codeshop.effective.domain.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Builder
@AllArgsConstructor
public class Owner implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    private UUID id;
    private String name;
    private String email;
    @JsonBackReference private List<Cat> cats;

    @Override
    public String toString() {
        return "Owner{id='%s', name='%s', email=%s, cats='%s'}"
            .formatted(id, name, email, cats.stream().map(Cat::getName).toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Owner owner = (Owner) o;
        return id.equals(owner.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Owner addCat(Cat cat) {
        if (!cats.contains(cat)) {
            cats.add(cat);
            cat.addOwner(this);
        }
        return this;
    }
}
