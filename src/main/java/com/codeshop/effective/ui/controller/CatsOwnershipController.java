package com.codeshop.effective.ui.controller;

import com.codeshop.effective.application.dto.OwnerDto;
import com.codeshop.effective.application.service.CatOwnershipService;
import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.model.Owner;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class CatsOwnershipController {
    private final CatOwnershipService catOwnershipService;

    @GetMapping("/cats/{catId}/owners")
    public List<Owner> findCatOwners(@PathVariable UUID catId) {
        return catOwnershipService.getCatOwners(catId);
    }

    @PostMapping("/cats/{catId}/owners")
    public Cat addCatOwnerNew(@PathVariable UUID catId, @RequestBody OwnerDto ownerDto) {
        return catOwnershipService.addCatOwner(catId, ownerDto);
    }

    @PostMapping("/cats/{catId}/owners/add")
    public Cat addCatOwnerExisting(@PathVariable UUID catId, @RequestParam UUID ownerId) {
        return catOwnershipService.addCatOwner(catId, ownerId);
    }

    @GetMapping("/owners/{ownerId}/cats")
    public List<Cat> getOwnedCats(@PathVariable UUID ownerId) {
        return catOwnershipService.getOwnedCats(ownerId);
    }
}
