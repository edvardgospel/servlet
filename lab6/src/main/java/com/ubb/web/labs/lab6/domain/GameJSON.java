package com.ubb.web.labs.lab6.domain;

import java.io.Serializable;

import lombok.Data;

@Data
public class GameJSON implements Serializable {
    private String userName;
    private String yourNumber;
    private String generatedNumber;
    private String difficulty;
}
