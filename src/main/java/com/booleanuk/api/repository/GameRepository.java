package com.booleanuk.api.repository;

import com.booleanuk.api.model.Game;
import com.booleanuk.api.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

 public interface GameRepository extends JpaRepository<Game, Integer> {

}
