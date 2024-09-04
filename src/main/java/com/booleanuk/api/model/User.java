package com.booleanuk.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "users")
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

  public User(Integer id, String email, String firstName, String lastName, String username, String phone) {
    this(email, firstName, lastName, username, phone);
    this.id = id;
  }

  public User(String email, String firstName, String lastName, String username, String phone) {
    this.email = email;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.phone = phone;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(this.id, user.id) && Objects.equals(this.email, user.email)
        && Objects.equals(this.firstName, user.firstName)
        && Objects.equals(this.lastName, user.lastName)
        && Objects.equals(this.username, user.username)
        && Objects.equals(this.phone, user.phone);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.email, this.firstName, this.lastName, this.username, this.phone);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + this.id +
        ", email='" + this.email + '\'' +
        ", firstName='" + this.firstName + '\'' +
        ", lastName='" + this.lastName + '\'' +
        ", username='" + this.username + '\'' +
        ", phone='" + this.phone + '\'' +
        '}';
  }
}
