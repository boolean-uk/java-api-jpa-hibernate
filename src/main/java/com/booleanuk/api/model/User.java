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
@AllArgsConstructor
@Table(name="User")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String email;

    @Column
    private String firstName;


    private Boolean isActive;

    @Column
    private String username;

    @Column
    private String phone;

    public User(Integer id, String email, String firstName, Boolean isActive) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.isActive = isActive;
    }

    public User(String email, String firstName, String username, String phone) {
        this.email = email;
        this.firstName = firstName;
        this.username = username;
        this.phone = phone;
        this.isActive = false;
    }


    public User(String email, String firstName) {
        this.email = email;
        this.firstName = firstName;
        this.isActive = false;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(isActive, user.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, isActive);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", isActive=" + isActive +
                '}';
    }
}
