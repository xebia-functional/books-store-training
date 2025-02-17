package com.xebia.services.User;

import com.xebia.models.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
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
    for (User u : users) {
      if (u.getName().equals(newUser.getName())) {
        logger.warning("The user" + newUser.getName() + " already exists.");
        return false;
      }
    }
    this.users.add(newUser);
    return true;
  }

  @Override
  public boolean removeUser(User remUser) {
    if (users.contains(remUser)) {
      users.remove(remUser);
      logger.info("The user " + remUser.getName() + " has been removed successfully.");
      return true;
    }
    logger.warning("The user: " + remUser.getName() + " doesn't exist.");
    return false;
  }

  @Override
  public Optional<User> searchUserByName(String userName) {
    for (User u : users) {
      if (u.getName().equalsIgnoreCase(userName)) {
        return Optional.of(u);
      }
    }
    logger.warning("No user register under the name " + userName);
    return Optional.empty();
  }

  @Override
  public Optional<User> searchUserByID(UUID userID) {
    for (User u : users) {
      if (u.getId().equals(userID)) {
        return Optional.of(u);
      }
    }
    logger.warning("No user register under the ID " + userID);
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
