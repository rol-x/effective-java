package com.codeshop.effective.domain.repository;

import com.codeshop.effective.domain.model.Owner;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface OwnerRepository {
    Owner save(Owner owner);

    Optional<Owner> findById(UUID id);

    List<Owner> findAll();
}
