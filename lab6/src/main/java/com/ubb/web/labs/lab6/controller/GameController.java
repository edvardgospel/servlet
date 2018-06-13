package com.ubb.web.labs.lab6.controller;

import javax.servlet.http.HttpSession;

import com.ubb.web.labs.lab6.domain.GameJSON;
import com.ubb.web.labs.lab6.domain.GameStatisticsResponse;
import com.ubb.web.labs.lab6.domain.GeneratedNumberResponse;
import com.ubb.web.labs.lab6.service.NumberGeneratorService;
import com.ubb.web.labs.lab6.service.StatisticsManagerService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

@RestController
public class GameController {

    @Autowired
    public StatisticsManagerService statisticsCalculatorService;
    @Autowired
    public NumberGeneratorService numberGeneratorService;

    @ResponseBody
    @RequestMapping(value = "/difficulty", method = RequestMethod.GET)
    public GeneratedNumberResponse game(@RequestParam("difficulty") String difficulty) {
        return numberGeneratorService.generate(difficulty);
    }

    @ResponseBody
    @RequestMapping(value = "/statistics", method = RequestMethod.POST)
    public GameStatisticsResponse manageStatistics(@RequestBody GameJSON gameJSON, HttpSession httpSession) {
        gameJSON.setUserName(httpSession.getAttribute("userName").toString());
        return statisticsCalculatorService.manageStatistics(gameJSON);
    }
}
