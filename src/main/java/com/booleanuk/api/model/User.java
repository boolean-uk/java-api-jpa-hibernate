package com.booleanuk.api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  @Column(name = "email")
  private String email;

  @Column(name = "first_name")
  private String firstName;

  @Column(name = "is_active")
  private Boolean isActive;

  public User(Integer id, String email, String firstName, Boolean isActive) {
    this.id = id;
    this.email = email;
    this.firstName = firstName;
    this.isActive = isActive;
  }

  public User(String email, String firstName) {
    this.email = email;
    this.firstName = firstName;
    this.isActive = false;
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
        && Objects.equals(this.isActive, user.isActive);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id, this.email, this.firstName, this.isActive);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + this.id +
        ", email='" + this.email + '\'' +
        ", firstName='" + this.firstName + '\'' +
        ", isActive=" + this.isActive +
        '}';
  }
}
