package com.xebia.services;

import com.xebia.models.User;
import java.util.*;
public interface UserService {

  boolean addUser(User newUser);

  boolean removeUser(User remUser);

  Optional<User> searchUser(String Username);

  List<User> listUsers();
}
