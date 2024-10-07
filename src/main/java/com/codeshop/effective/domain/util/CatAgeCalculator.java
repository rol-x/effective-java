package com.codeshop.effective.domain.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

// 4. Blokowanie tworzenia instancji za pomocą konstruktora prywatnego
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CatAgeCalculator {
    public static int toHumanYears(int catYears) {
        if (catYears <= 0) {
            return 0;
        } else if (catYears == 1) {
            return 15;
        } else if (catYears == 2) {
            return 24;
        } else {
            return 24 + (catYears - 2) * 4;
        }
    }

    public static int toCatYears(int humanYears) {
        if (humanYears <= 0) {
            return 0;
        } else if (humanYears <= 15) {
            return 1;
        } else if (humanYears <= 24) {
            return 2;
        } else {
            return 2 + (humanYears - 24) / 4;
        }
    }
}
