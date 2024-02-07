package com.booleanuk.api.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    @Test
    void testUserConstructor() {
        User user = new User("email", "firstName", "lastName", "userName", "phone");
        Assertions.assertEquals("email", user.getEmail());
        Assertions.assertEquals("firstName", user.getFirstName());
        Assertions.assertEquals("lastName", user.getLastName());
        Assertions.assertEquals("userName", user.getUserName());
        Assertions.assertEquals("phone", user.getPhone());
    }
}