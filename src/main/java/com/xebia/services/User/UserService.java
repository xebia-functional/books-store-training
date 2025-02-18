package com.xebia.services.User;

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
   * This method adds a User to the User list
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
  Optional<User> searchUserByName(String userName);

  /**
   * This method returns the user that matches with the parameter ID
   *
   * @param userID
   * @return Optional.of(user)
   */
  Optional<User> searchUserByID(UUID userID);

  /**
   * This method returns a list of the registered users
   *
   * @return List<User>
   */
  List<User> listUsers();
}
