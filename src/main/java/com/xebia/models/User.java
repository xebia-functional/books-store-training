package com.xebia.models;

import java.util.Objects;

public class User {

  private String name;
  private int id;

  public User() {}

  public User(String name, int id) {
    this.name = name;
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public int getId() {
    return id;
  }

  @Override
  public String toString() {
    return "user{" + "name='" + name + '\'' + ", id=" + id + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    User user = (User) o;
    return id == user.id && Objects.equals(name, user.name);
  }
}
