package com.ubb.web.labs.lab6.service;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;

import com.ubb.web.labs.lab6.domain.GameEntity;
import com.ubb.web.labs.lab6.domain.GameInitializationResponse;
import com.ubb.web.labs.lab6.domain.GameJSON;
import com.ubb.web.labs.lab6.domain.GameStatisticsResponse;
import com.ubb.web.labs.lab6.domain.UserEntity;
import com.ubb.web.labs.lab6.repository.GameRepository;
import com.ubb.web.labs.lab6.repository.UserRepository;

public class StatisticsManagerService {

    public static final String EASY = "easy";
    public static final String MEDIUM = "medium";
    public static final String HARD = "hard";

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private UserRepository userRepository;

    public GameInitializationResponse initializeStatistics(String userName) {
        GameInitializationResponse gameInitializationResponse = new GameInitializationResponse();
        gameInitializationResponse.setEasyAverage(calculateAveragePercentage(gameRepository.findAllGamesByDifficulty(EASY), userName));
        gameInitializationResponse.setMediumAverage(calculateAveragePercentage(gameRepository.findAllGamesByDifficulty(MEDIUM), userName));
        List<GameEntity> hardGameEntities = gameRepository.findAllGamesByDifficulty(HARD);
        gameInitializationResponse.setHardAverage(calculateAveragePercentage(hardGameEntities, userName));
        gameInitializationResponse.setHardestBestResult(calculateMaxPercentage(hardGameEntities, userName));
        return gameInitializationResponse;
    }

    public GameStatisticsResponse manageStatistics(GameJSON game) {
        Double percentage = calculatePercentage(game.getGeneratedNumber(), game.getYourNumber());
        postStatistics(game.getUserName(), percentage, game.getDifficulty());
        List<GameEntity> gameEntities = gameRepository.findAllGamesByDifficulty(game.getDifficulty());
        GameStatisticsResponse gameStatisticsResponse = new GameStatisticsResponse();
        gameStatisticsResponse.setGeneratedNumber(game.getGeneratedNumber());
        gameStatisticsResponse.setYourNumber(game.getYourNumber());
        gameStatisticsResponse.setPercentage(percentage);
        gameStatisticsResponse.setAverage(calculateAveragePercentage(gameEntities, game.getUserName()));
        hardestBestCheck(game, gameEntities, gameStatisticsResponse);
        return gameStatisticsResponse;
    }

    private Double calculatePercentage(String generatedNumber, String yourNumber) {
        double counter = 0.0;
        String shorter = getShorterString(generatedNumber, yourNumber);
        String longer = getLongerString(generatedNumber, yourNumber);
        for (int i = 0; i < shorter.length(); i++) {
            if (yourNumber.charAt(i) == generatedNumber.charAt(i)) {
                counter++;
            }
        }
        return counter / longer.length();
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
        gameRepository.save(game);
    }

    private Double calculateAveragePercentage(List<GameEntity> games, String userName) {
        if (games.size() == 0) {
            return 0.0;
        }
        int gameCounter = 0;
        Double averagePercentage = 0.0;
        for (GameEntity game : games) {
            if (game.getUserEntity().getUserName().equals(userName)) {
                gameCounter++;
                averagePercentage += game.getPercentage();
            }
        }
        return averagePercentage / gameCounter;
    }

    private Double calculateMaxPercentage(List<GameEntity> games, String userName) {
        Double maxPercentage = 0.0;
        for (GameEntity game : games) {
            if (game.getUserEntity().getUserName().equals(userName)) {
                if (game.getPercentage() > maxPercentage) {
                    maxPercentage = game.getPercentage();
                }
            }
        }
        return maxPercentage;
    }

    private UserEntity getUserEntity(String userName) {
        return userRepository.findByUserName(userName);
    }

    private void hardestBestCheck(GameJSON game, List<GameEntity> gameEntities, GameStatisticsResponse gameStatisticsResponse) {
        if (game.getDifficulty().equals(HARD)) {
            gameStatisticsResponse.setHardestBestResult(calculateMaxPercentage(gameEntities, game.getUserName()));
        }
        if (Objects.isNull(gameStatisticsResponse.getHardestBestResult())) {
            gameStatisticsResponse.setHardestBestResult(0.0d);
        }
    }

}
