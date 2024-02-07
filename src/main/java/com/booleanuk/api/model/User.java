package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name="Users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userId;

    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String username;

    @Column
    private String phone;


    public User(String email, String firstName, String username, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.username = username;
        this.phone = phone;
    }
}
