package com.booleanuk.api.repository;

import com.booleanuk.api.model.Games;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GamesRepository extends JpaRepository<Games, Integer> {

}
