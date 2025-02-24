package com.xebia.models;

import java.util.Objects;
import java.util.UUID;

public class User {

  private String name;
  private UUID id;

  public User() {}

  public User(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  public User(String name) {
    this.name = name;
    this.id = UUID.randomUUID();
  }

  public String getName() {
    return name;
  }

  public UUID getId() {
    return id;
  }

  @Override
  public String toString() {
    return "User{" + "name='" + name + '\'' + ", id=" + id + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return Objects.equals(getName(), user.getName()) && Objects.equals(getId(), user.getId());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getName(), getId());
  }
}
