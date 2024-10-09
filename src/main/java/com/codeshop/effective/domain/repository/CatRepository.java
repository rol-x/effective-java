package com.codeshop.effective.domain.repository;

import com.codeshop.effective.domain.model.Cat;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface CatRepository {
    Cat save(Cat cat);

    Optional<Cat> findById(UUID id);

    List<Cat> findAll();
}
