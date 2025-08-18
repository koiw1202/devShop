package com.devsShop.common;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CalculationUtils {

    public static int getCurrentOffset(int currentPage, int pageUnit) {
        return (currentPage - 1) * pageUnit;
    }



}
