package com.codeshop.effective.application.service;

import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.model.Owner;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CatOwnershipServiceTest {
    @Mock private CatService catService;
    @Mock private OwnerService ownerService;
    @InjectMocks private CatOwnershipService uut;

    private static final UUID CAT_ID = UUID.randomUUID();
    private static final UUID OWNER_ID = UUID.randomUUID();

    @Test
    void addCatOwner() {
        // Given
        var exampleCat = exampleCat();
        var exampleOwner = exampleOwner();
        when(catService.getCat(CAT_ID)).thenReturn(exampleCat);
        when(catService.save(any(Cat.class))).then(invocation -> invocation.getArgument(0));
        when(ownerService.getOwner(OWNER_ID)).thenReturn(exampleOwner);

        // When
        var cat = uut.addCatOwner(CAT_ID, OWNER_ID);

        // Then
        assertThat(cat.getOwners()).contains(exampleOwner);
    }

    private Cat exampleCat() {
        return Cat.builder()
            .id(CAT_ID)
            .name("Cat")
            .birthDate(LocalDate.now())
            .owners(new ArrayList<>())
            .build();
    }

    private Owner exampleOwner() {
        return Owner.builder()
            .id(OWNER_ID)
            .name("Owner")
            .email("owner@email.com")
            .cats(new ArrayList<>())
            .build();
    }
}