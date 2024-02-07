package com.booleanuk.api.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //@Column(unique = true)
    @Column
    private String email;

    @Column
    private String firstName;

    @Column
    private String lastName;

    //@Column(unique = true)
    @Column
    private String userName;

    //@Column(unique = true)
    @Column
    private String phone;

    public User (String email, String firstName, String lastName, String userName, String phone){
        setEmail(email);
        setFirstName(firstName);
        setLastName(lastName);
        setUserName(userName);
        setPhone(phone);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, email, firstName);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
