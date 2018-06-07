package com.ubb.web.labs.lab6.domain;

import lombok.Data;

@Data
public class GameStatisticsResponse {
    private String generatedNumber;
    private String yourNumber;
    private Double percentage;
    private Double average;
    private Double hardestBestResult;
}
