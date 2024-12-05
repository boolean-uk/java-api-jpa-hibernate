package com.booleanuk.api.model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name= "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "username")
    private String username;

    @Column(name = "phone")
    private String phone;

//    private Boolean isActive;

    public User(String email, String firstName, String lastName, String username, String phone) {
//        this.id = id;
        super();
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.phone = phone;
//        this.isActive = isActive;
    }

    public User(String email, String firstName) {
        this.email = email;
        this.firstName = firstName;
//        this.isActive = false;
    }

    public User() {

    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhone() {
        return this.phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

//    public Boolean getActive() {
//        return this.isActive;
//    }

//    public void setActive(Boolean active) {
//        isActive = active;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
//        User user = (User) o;
//        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName) && Objects.equals(isActive, user.isActive);
//    }

//    @Override
//    public int hashCode() {
//        return Objects.hash(id, email, firstName, isActive);
//    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", username='" + username + '\'' +
                ", phone='" + phone + '\'' +
//                ", isActive=" + isActive +
                '}';
    }
}
