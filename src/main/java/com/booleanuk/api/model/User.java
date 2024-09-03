package com.booleanuk.api.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.util.Objects;

@Entity
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Integer id;

  private String email;
  private String firstName;
  private boolean isActive;

  public User(Integer id, String email, String firstName, boolean isActive) {
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

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public boolean getActive() {
    return isActive;
  }

  public void setActive(boolean active) {
    isActive = active;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o)
      return true;
    if (o == null || getClass() != o.getClass())
      return false;
    User user = (User) o;
    return Objects.equals(id, user.id) && Objects.equals(email, user.email) && Objects.equals(firstName, user.firstName)
        && Objects.equals(isActive, user.isActive);
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
