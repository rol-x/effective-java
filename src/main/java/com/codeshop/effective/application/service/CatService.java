package com.codeshop.effective.application.service;

import com.codeshop.effective.application.dto.CatDto;
import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.repository.CatRepository;
import com.codeshop.effective.domain.service.NamingPolicy;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class CatService {
    // 5. Wstrzykiwanie zależności zamiast odwoływania się do zasobów na sztywno
    private final CatRepository catRepository;
    private final NamingPolicy namingPolicy;
    private final OwnerService ownerService;

    public Cat addCat(CatDto catDto) {
        namingPolicy.validateCatName(catDto.name());

        var primaryOwner = ownerService.getOwner(catDto.primaryOwnerId());

        // 2. Budowniczy do obsługi wielu parametrów konstruktora
        var cat = Cat.builder()
            .id(UUID.randomUUID())
            .name(catDto.name())
            .birthDate(catDto.birthDate())
            .owners(new ArrayList<>(Collections.singletonList(primaryOwner)))
            .build();

        primaryOwner.addCat(cat);
        return save(cat);
    }

    public Cat getCat(UUID catId) {
        return catRepository.findById(catId).orElseThrow();
    }

    public List<Cat> getAllCats() {
        return catRepository.findAll();
    }

    public Cat save(Cat cat) {
        return catRepository.save(cat);
    }
}
