package com.xebia.services;

import com.xebia.models.User;

public interface UserService {

  boolean addUser(User newUser);

  boolean removeUser(User remUser);

  Optional<User> searchUser(String Username);

  void listUsers();
}
