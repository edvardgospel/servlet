package com.ubb.web.labs.lab6.repository;

import com.ubb.web.labs.lab6.domain.GameEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface GameRepository extends JpaRepository<GameEntity, Integer> {
    @Query("SELECT g FROM GameEntity g WHERE g.difficulty = ?1")
    List<GameEntity> findAllGamesByDifficulty(String difficulty);
}
