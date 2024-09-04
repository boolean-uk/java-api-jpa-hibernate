package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record GameDTO(
        @JsonProperty(required = true) String title,
        @JsonProperty(required = true) String genre,
        @JsonProperty(required = true) String publisher,
        @JsonProperty(required = true) String developer,
        @JsonProperty(required = true) Integer releaseYear,
        @JsonProperty(required = true) Boolean isEarlyAccess) {
}
