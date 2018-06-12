package com.ubb.web.labs.lab6.controller;

import com.ubb.web.labs.lab6.domain.GameJSON;
import com.ubb.web.labs.lab6.domain.GameStatisticsResponse;
import com.ubb.web.labs.lab6.service.NumberGeneratorService;
import com.ubb.web.labs.lab6.service.StatisticsManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";

    @Value("${init.parameter.easy}")
    private int[] initEasy;
    @Value("${init.parameter.medium}")
    private int[] initMedium;
    @Value("${init.parameter.hard}")
    private int[] initHard;

    @Autowired
    public StatisticsManagerService statisticsCalculatorService;

    @ResponseBody
    @RequestMapping(value = "/difficulty", method = RequestMethod.GET)
    public int[] game(@RequestParam("difficulty") String difficulty) {
        int[] ret = new int[2];
        if (difficulty.equals(EASY)) {
            ret[0] = NumberGeneratorService.generateNDigitNumber(initEasy[0]);
            ret[1] = initEasy[1];
        } else if (difficulty.equals(MEDIUM)) {
            ret[0] = NumberGeneratorService.generateNDigitNumber(initMedium[0]);
            ret[1] = initMedium[1];
        } else if (difficulty.equals(HARD)) {
            ret[0] = NumberGeneratorService.generateNDigitNumber(initHard[0]);
            ret[1] = initHard[1];
        }
        return ret;
    }

    @ResponseBody
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public GameStatisticsResponse manageStatistics(@RequestBody GameJSON gameJSON) {
        GameStatisticsResponse gsr = statisticsCalculatorService.manageStatistics(gameJSON);
        System.out.println("BACKEND:" + gameJSON);
        System.out.println("BACKEND:" + gsr);
        return gsr;
    }
}
