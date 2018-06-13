package com.ubb.web.labs.lab6.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "game")
public class GameEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserEntity userEntity;

    private Double percentage;

    private String difficulty;
}
