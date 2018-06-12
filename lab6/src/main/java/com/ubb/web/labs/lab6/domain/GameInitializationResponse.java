package com.ubb.web.labs.lab6.domain;

import lombok.Data;

@Data
public class GameInitializationResponse {
    private Double easyAverage;
    private Double mediumAverage;
    private Double hardAverage;
    private Double hardestBestResult;
}
