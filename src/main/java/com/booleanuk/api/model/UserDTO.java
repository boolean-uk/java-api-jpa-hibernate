package com.booleanuk.api.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public record UserDTO(
    @JsonProperty(required = true) String email,
    @JsonProperty(required = true) String firstName,
    @JsonProperty(required = true) String lastName,
    @JsonProperty(required = true) String username,
    @JsonProperty(required = true) String phone) {
}
