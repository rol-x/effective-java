package com.codeshop.effective.ui.controller;

import com.codeshop.effective.application.dto.CatDto;
import com.codeshop.effective.application.service.CatService;
import com.codeshop.effective.domain.model.Cat;
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
@RequestMapping("/cats")
public class CatsController {
    private final CatService catService;

    @GetMapping
    public List<Cat> findAllCats() {
        return catService.getAllCats();
    }

    @GetMapping("/{catId}")
    public Cat findCat(@PathVariable UUID catId) {
        return catService.getCat(catId);
    }

    @PostMapping
    public Cat addCat(@RequestBody CatDto catDto) {
        return catService.addCat(catDto);
    }
}
