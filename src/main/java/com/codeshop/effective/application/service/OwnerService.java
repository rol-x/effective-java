package com.codeshop.effective.application.service;

import com.codeshop.effective.application.dto.OwnerDto;
import com.codeshop.effective.domain.model.Owner;
import com.codeshop.effective.domain.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OwnerService {
    private final OwnerRepository ownerRepository;

    public Owner addOwner(OwnerDto ownerDto) {
        var owner = Owner.builder()
            .id(UUID.randomUUID())
            .name(ownerDto.name())
            .email(ownerDto.email())
            .cats(new ArrayList<>())
            .build();

        return save(owner);
    }

    public Owner getOwner(UUID ownerId) {
        return ownerRepository.findById(ownerId).orElseThrow();
    }

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Owner save(Owner owner) {
        return ownerRepository.save(owner);
    }
}
