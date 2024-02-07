package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email", nullable = false)
    public String email;
    @Column(name = "first_name")
    public String firstName;
    @Column(name = "last_name")
    public String lastName;
    @Column(name = "username", nullable = false)
    public String username;
    @Column(name = "phone")
    public String phone;
    @Column(name = "is_active")
    public Boolean isActive;

    public User() {}

    public User(final Integer id, final String email, final String firstName, final String lastName, final String username, final String phone, final Boolean isActive) {
        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
        this.isActive = isActive;
    }

    public User(final String email, final String firstName, final String lastName, final String username, final String phone) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;

        isActive = false;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User _user = (User) o;

        return Objects.equals(id, _user.id) &&
                Objects.equals(email, _user.email) &&
                Objects.equals(firstName, _user.firstName) &&
                Objects.equals(lastName, _user.lastName) &&
                Objects.equals(username, _user.username) &&
                Objects.equals(phone, _user.phone) &&
                Objects.equals(isActive, _user.isActive);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName, lastName, username, phone, isActive);
    }

    @Override
    public String toString() {
        return "User {" +
                "id=" + id +
                ", email='" + email + "'" +
                ", firstName='" + firstName + "'" +
                ", lastName='" + lastName + "'" +
                ", username='" + username + "'" +
                ", phone='" + phone + "'" +
                ", isActive=" + isActive +
                '}';
    }
}
