package com.ubb.web.labs.lab6.service;

import com.ubb.web.labs.lab6.domain.*;
import com.ubb.web.labs.lab6.repository.GameRepository;
import com.ubb.web.labs.lab6.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StatisticsManagerService {

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    public GameInitializationResponse initializeStatistics(String userName) {
        GameInitializationResponse gameInitializationResponse = new GameInitializationResponse();
        gameInitializationResponse.setEasyAverage(calculateAveragePercentage(gameRepository.findAllGamesByUserAndDifficulty(userName, "easy")));
        gameInitializationResponse.setMediumAverage(calculateAveragePercentage(gameRepository.findAllGamesByUserAndDifficulty(userName, "medium")));
        List<GameEntity> gameEntities = gameRepository.findAllGamesByUserAndDifficulty(userName, "hard");
        gameInitializationResponse.setHardAverage(calculateAveragePercentage(gameEntities));
        gameInitializationResponse.setHardestBestResult(calculateMaxPercentage(gameEntities));
        return gameInitializationResponse;
    }

    public GameStatisticsResponse manageStatistics(GameJSON game) {
        Double percentage = calculatePercentage(game.getGeneratedNumber(), game.getYourNumber());
        postStatistics(game.getUserName(), percentage, game.getDifficulty());
        List<GameEntity> gameEntities = gameRepository.findAllGamesByUserAndDifficulty(game.getUserName(), game.getDifficulty());

        GameStatisticsResponse gameStatisticsResponse = new GameStatisticsResponse();
        gameStatisticsResponse.setGeneratedNumber(game.getGeneratedNumber());
        gameStatisticsResponse.setYourNumber(game.getYourNumber());
        gameStatisticsResponse.setPercentage(percentage);
        gameStatisticsResponse.setAverage(calculateAveragePercentage(gameEntities));
        if (game.getDifficulty().equals("hard")) {
            gameStatisticsResponse.setHardestBestResult(calculateMaxPercentage(gameEntities));
        }
        return gameStatisticsResponse;
    }

    private Double calculatePercentage(String generatedNumber, String yourNumber) {
        double counter = 0.0;
        String shorter = getShorterString(generatedNumber, yourNumber);
        for (int i = 0; i < shorter.length(); i++) {
            if (yourNumber.charAt(i) == generatedNumber.charAt(i)) {
                counter++;
            }
        }
        return counter / generatedNumber.length();
    }

    private String getShorterString(String string1, String string2) {
        return string1.length() < string2.length() ? string1 : string2;
    }

    private String getLongerString(String string1, String string2) {
        return string1.length() > string2.length() ? string1 : string2;
    }


    private void postStatistics(String userName, Double score, String difficulty) {
        GameEntity game = new GameEntity();
        game.setUserEntity(getUserEntity(userName));
        game.setPercentage(score);
        game.setDifficulty(difficulty);
        gameRepository.saveAndFlush(game);
    }


    private Double calculateAveragePercentage(List<GameEntity> games) {
        if (games.size() == 0) {
            return 0.0;
        }
        Double averagePercentage = 0.0;
        for (GameEntity game : games) {
            averagePercentage += game.getPercentage();
        }
        return averagePercentage / games.size();
    }

    private Double calculateMaxPercentage(List<GameEntity> games) {
        Double maxPercentage = 0.0;
        for (GameEntity game : games) {
            if (game.getPercentage() > maxPercentage) {
                maxPercentage = game.getPercentage();
            }
        }
        return maxPercentage;
    }

    private UserEntity getUserEntity(String userName) {
        return userRepository.findByUserName(userName);
    }

}
