package com.codeshop.effective.application.dto;

import java.time.LocalDate;
import java.util.UUID;

public record CatDto (String name, LocalDate birthDate, UUID primaryOwnerId) {}
