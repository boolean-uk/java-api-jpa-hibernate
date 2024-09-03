package com.booleanuk.api.model;

public record GameDTO(
    String title,
    String genre,
    String publisher,
    String developer,
    Integer releaseYear,
    Boolean isEarlyAccess) {
}
