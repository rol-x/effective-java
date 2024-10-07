package com.codeshop.effective.domain.service;

import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class CatNamingPolicy {
    // 6. Unikanie powielania obiektów
    private static final Pattern CAT_NAME_PATTERN = Pattern.compile("^[a-zA-Z]+$");

    public void validateCatName(String name) {
        if(!CAT_NAME_PATTERN.matcher(name).matches())
            throw new IllegalArgumentException("Invalid cat name: " + name);
    }
}
