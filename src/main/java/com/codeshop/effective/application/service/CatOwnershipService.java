package com.codeshop.effective.application.service;

import com.codeshop.effective.application.dto.OwnerDto;
import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatOwnershipService {
    private final CatService catService;
    private final OwnerService ownerService;

    public List<Owner> getCatOwners(UUID catId) {
        return catService.getCat(catId).getOwners();
    }

    public Cat addCatOwner(UUID catId, OwnerDto ownerDto) {
        var owner = ownerService.addOwner(ownerDto);
        return catService.save(
            catService
                .getCat(catId)
                .addOwner(owner)
        );
    }

    public Cat addCatOwner(UUID catId, UUID ownerId) {
        var owner = ownerService.getOwner(ownerId);
        return catService.save(
            catService
                .getCat(catId)
                .addOwner(owner)
        );
    }

    public List<Cat> getOwnedCats(UUID ownerId) {
        return ownerService.getOwner(ownerId).getCats();
    }
}
