package com.xebia.services.User;

import com.xebia.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class UserServiceImp implements UserService {
  private List<User> users;
  private Logger logger;

  public UserServiceImp(Logger logger) {
    this.users = new ArrayList<>();
    this.logger = logger;
  }

  @Override
  public boolean addUser(User newUser) {
      if (users.contains(newUser)) {
        logger.warning("The user already exists.");
        return false;
      }
    this.users.add(newUser);
    return true;
  }

  @Override
  public boolean removeUser(User remUser) {
    for (User u : users) {
      if (users.equals(remUser)) {
        users.remove(remUser);
        logger.info("The user has been removed successfully.");
        return true;
      }
    }
    logger.warning("The user: " + remUser.getName() + " doesn't exist.");
    return false;
  }

  @Override
  public Optional<User> searchUser(String userName) {
    for (User u : users) {
      if (u.getName().equalsIgnoreCase(userName)) {
        return Optional.of(u);
      }
    }
    logger.warning("No user register under the name " + userName);
    return Optional.empty();
  }

  @Override
  public List<User> listUsers() {
    logger.info("----- User's list -----");
    if (users.isEmpty()) {
      logger.info("No users registered.");
    } else {
      for (User u : users) {
        logger.info(u.toString());
      }
    }
    return users;
  }
}
