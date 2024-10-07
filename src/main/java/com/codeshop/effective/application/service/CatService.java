package com.codeshop.effective.application.service;

import com.codeshop.effective.application.dto.CatDto;
import com.codeshop.effective.domain.model.Cat;
import com.codeshop.effective.domain.repository.CatRepository;
import com.codeshop.effective.domain.service.CatNamingPolicy;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CatService {
    // 5. Wstrzykiwanie zależności zamiast odwoływania się do zasobów na sztywno
    private final CatRepository catRepository;
    private final CatNamingPolicy catNamingPolicy;

    public Cat addCat(CatDto catDto) {
        catNamingPolicy.validateCatName(catDto.name());

        // 2. Budowniczy do obsługi wielu parametrów konstruktora
        var newCat = Cat.builder()
            .id(UUID.randomUUID())
            .name(catDto.name())
            .age(catDto.age())
            .favoriteFood(catDto.favoriteFood())
            .build();

        return catRepository.save(newCat);
    }

    public Cat getCat(UUID catId) {
        return catRepository.findById(catId).orElseThrow();
    }
}
