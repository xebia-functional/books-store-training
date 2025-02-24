package com.xebia.services.User;

import com.xebia.database.DatabaseManager;
import com.xebia.models.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class UserServiceDBImpl implements UserService {

  private final Connection connection;
  private Logger logger;

  public UserServiceDBImpl(Logger logger) throws SQLException {
    this.connection = DatabaseManager.getConnection();
    this.logger = logger;
  }

  private boolean containsUser(User user) {
    try {
      String selectQuery = "SELECT * FROM Users WHERE id = ?";
      PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
      selectStmt.setObject(1, user.getId());
      return selectStmt.executeQuery().next();
    } catch (SQLException e) {
      logger.severe("An error occurred while checking if the user exists in the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean addUser(User user) {
    try (Connection connection = DatabaseManager.getConnection()) {
      if (containsUser(user)) {
        logger.warning("The user already exists in the list.");
        return false;
      } else {
        String insertQuery = "INSERT INTO Users (id, name) VALUES (?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setObject(1, user.getId());
        stmt.setString(2, user.getName());
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while adding the user to the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean removeUser(User user) {
    try (Connection connection = DatabaseManager.getConnection()) {
      if (!containsUser(user)) {
        logger.warning("The user does not exist in the list.");
        return false;
      } else {
        String deleteQuery = "DELETE FROM Users WHERE id = ?";
        PreparedStatement stmt = connection.prepareStatement(deleteQuery);
        stmt.setObject(1, user.getId());
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while removing the book from the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public Optional<User> searchUserByName(String userName) {
    try {
      String selectQuery = "SELECT * FROM Users WHERE name = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setString(1, userName);
      var resultSet = stmt.executeQuery();

      if (resultSet.next()) {
        User user = new User((UUID) resultSet.getObject("id"), resultSet.getString("name"));
        return Optional.of(user);
      }
    } catch (SQLException e) {
      logger.severe("An error occurred while searching for the user by name.");
      e.printStackTrace();
    }
    return Optional.empty();
  }

  @Override
  public Optional<User> searchUserByID(UUID userID) {
    try {
      String selectQuery = "SELECT * FROM Users WHERE name = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setObject(1, userID);
      var resultSet = stmt.executeQuery();

      if (resultSet.next()) {
        User user = new User((UUID) resultSet.getObject("id"), resultSet.getString("name"));
        return Optional.of(user);
      }

    } catch (SQLException e) {
      logger.severe("An error occurred while searching for the user by ID.");
      e.printStackTrace();
    }
    logger.warning("The user does not exists in the list.");
    return Optional.empty();
  }

  @Override
  public List<User> listUsers() {
    List<User> users = new ArrayList<>();
    try {
      String selectQuery = "SELECT * FROM Users";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      var resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        User user = new User((UUID) resultSet.getObject("id"), resultSet.getString("name"));
        users.add(user);
      }
    } catch (SQLException e) {
      logger.severe("An error occurred while listing users from the database.");
      e.printStackTrace();
    }
    return users;
  }
}
