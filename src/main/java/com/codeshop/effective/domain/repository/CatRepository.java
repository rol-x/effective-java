package com.codeshop.effective.domain.repository;

import com.codeshop.effective.domain.model.Cat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatRepository {
    List<Cat> findAll();

    Optional<Cat> findById(UUID id);

    Cat save(Cat cat);
}
