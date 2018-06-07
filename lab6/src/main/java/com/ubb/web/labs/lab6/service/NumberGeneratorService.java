package com.ubb.web.labs.lab6.service;

import java.util.Random;

public class NumberGeneratorService {

    public static final Random RANDOM = new Random();

    public static int generateNDigitNumber(int digit) {
        int min = 1;
        int max = 9;
        for (int i = 1; i < digit; i++) {
            min *= 10;
            max *= 10;
        }
        return generateNumber(min, max);

    }

    private static int generateNumber(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

}
