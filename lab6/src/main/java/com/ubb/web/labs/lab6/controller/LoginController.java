package com.ubb.web.labs.lab6.controller;

import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.ubb.web.labs.lab6.domain.GameInitializationResponse;
import com.ubb.web.labs.lab6.service.StatisticsManagerService;
import com.ubb.web.labs.lab6.service.ValidatorService;

@Controller
public class LoginController {

    @Autowired
    private ValidatorService validatorService;

    @Autowired
    private StatisticsManagerService statisticsManagerService;

    @RequestMapping(value = "/login.do", method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public String validate(@RequestParam("username") String userName, @RequestParam("password") String password, Model model) {
        if (Objects.nonNull(validatorService.validateData(userName, password))) {
            GameInitializationResponse gameInitializationResponse = statisticsManagerService.initializeStatistics(userName);
            model.addAttribute("userName", userName);
            model
            return "game";
        } else {
            return "redirect:/login.do";
        }
    }

}
