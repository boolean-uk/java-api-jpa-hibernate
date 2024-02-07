package com.booleanuk.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {

    @Test
    void testGameConstructor() {
        Game game = new Game("title", "genre", "publisher", "developer", 2020, true);
        Assertions.assertEquals("title", game.getTitle());
        Assertions.assertEquals("genre", game.getGenre());
        Assertions.assertEquals("publisher", game.getPublisher());
        Assertions.assertEquals("developer", game.getDeveloper());
        Assertions.assertEquals(2020, game.getReleaseYear());
        Assertions.assertTrue(game.isEarlyAccess());
    }
}