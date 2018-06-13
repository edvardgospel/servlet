package com.ubb.web.labs.lab6.service;

import java.util.Random;

import org.springframework.beans.factory.annotation.Value;

import com.ubb.web.labs.lab6.domain.GeneratedNumberResponse;

public class NumberGeneratorService {

    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";
    public static final Random RANDOM = new Random();

    @Value("${init.parameter.easy}")
    private int[] initEasy;
    @Value("${init.parameter.medium}")
    private int[] initMedium;
    @Value("${init.parameter.hard}")
    private int[] initHard;

    public GeneratedNumberResponse generate(String difficulty) {
        GeneratedNumberResponse generatedNumberResponse = new GeneratedNumberResponse();
        if (difficulty.equals(EASY)) {
            generatedNumberResponse.setGeneratedNumber(generateNDigitNumber(initEasy[0]));
            generatedNumberResponse.setSecond(initEasy[1]);
        } else if (difficulty.equals(MEDIUM)) {
            generatedNumberResponse.setGeneratedNumber(generateNDigitNumber(initMedium[0]));
            generatedNumberResponse.setSecond(initMedium[1]);
        } else if (difficulty.equals(HARD)) {
            generatedNumberResponse.setGeneratedNumber(generateNDigitNumber(initHard[0]));
            generatedNumberResponse.setSecond(initHard[1]);
        }
        return generatedNumberResponse;
    }


    private int generateNDigitNumber(int digit) {
        int min = 1;
        int max = 9;
        for (int i = 1; i < digit; i++) {
            min *= 10;
            max *= 10;
        }
        return generateNumber(min, max);
    }

    private int generateNumber(int min, int max) {
        return RANDOM.nextInt(max - min) + min;
    }

}
