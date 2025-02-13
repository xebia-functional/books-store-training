package com.xebia.services;

import com.xebia.models.User;
import java.util.*;

public interface UserService {
  /**
   * This method adds a User to the User list
   *
   * @param newUser
   * @return boolean
   */
  boolean addUser(User newUser);

  /**
   * This method adds an User to the User list
   *
   * @param remUser
   * @return boolean
   */
  boolean removeUser(User remUser);

  /**
   * This method returns the user that matches with the parameter userName
   *
   * @param userName
   * @return Optional.of(user)
   */
  Optional<User> searchUser(String userName);

  /**
   * This method returns a list of the registered users
   *
   * @return List<User>
   */
  List<User> listUsers();
}
