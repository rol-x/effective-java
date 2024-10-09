package com.codeshop.effective.ui.controller;

import com.codeshop.effective.application.dto.OwnerDto;
import com.codeshop.effective.domain.model.Owner;
import com.codeshop.effective.application.service.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/owners")
public class OwnersController {
    private final OwnerService ownerService;

    @GetMapping
    public List<Owner> findAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{ownerId}")
    public Owner findOwner(@PathVariable UUID ownerId) {
        return ownerService.getOwner(ownerId);
    }

    @PostMapping
    public Owner addOwner(@RequestBody OwnerDto ownerDto) {
        return ownerService.addOwner(ownerDto);
    }
}
