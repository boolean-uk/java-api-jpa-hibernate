package com.booleanuk.api.repository;

import com.booleanuk.api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends JpaRepository<Game, Integer> {
}