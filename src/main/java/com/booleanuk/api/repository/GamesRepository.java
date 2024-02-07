package com.booleanuk.api.repository;

import com.booleanuk.api.model.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.PreparedStatement;

public interface GamesRepository extends JpaRepository<Game, Integer> {
}
