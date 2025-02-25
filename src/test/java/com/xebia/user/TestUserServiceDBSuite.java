package com.xebia.user;

import static org.junit.jupiter.api.Assertions.*;

import com.xebia.database.DatabaseManager;
import com.xebia.migrations.FlywayMigration;
import com.xebia.models.User;
import com.xebia.services.User.UserService;
import com.xebia.services.User.UserServiceDBImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestUserServiceDBSuite {

  private Logger logger;
  private UserService userService;

  static PostgreSQLContainer<?> postgres =
      new PostgreSQLContainer<>("postgres:15")
          .withDatabaseName("bookstore_db")
          .withUsername("xebia_user")
          .withPassword("xebia_pw");

  @BeforeAll
  static void beforeAll() {
    postgres.start();
    FlywayMigration.migrate(postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
  }

  @AfterAll
  static void afterAll() {
    postgres.stop();
  }

  @BeforeEach
  void setUp() throws SQLException {
    Connection connection =
        DatabaseManager.getConnection(
            postgres.getJdbcUrl(), postgres.getUsername(), postgres.getPassword());
    logger = Logger.getLogger(TestUserServiceDBSuite.class.getName());
    userService = new UserServiceDBImpl(logger, connection);
    connection.prepareStatement("TRUNCATE TABLE Users CASCADE").execute();
  }

  @Test
  void addUserShouldWork() {
    User newUser = new User("Manolo");
    assertTrue(userService.addUser(newUser));
    assertEquals(true, userService.containsUser(newUser));
  }

  @Test
  void removeUserShouldWork() {
    User newUser = new User("Manolo");
    userService.addUser(newUser);
    assertTrue(userService.removeUser(newUser));
    assertFalse(userService.containsUser(newUser));
  }

  @Test
  void searchUserByNameShouldWork() {
    User newUser = new User("Manolo");
    String name = newUser.getName();
    userService.addUser(newUser);
    assertEquals(newUser, userService.searchUserByName(name).get());
  }

  @Test
  void searchUserByIdShouldWork() {
    User newUser = new User("Manolo");
    UUID idSearch = newUser.getId();
    userService.addUser(newUser);
    assertEquals(newUser, userService.searchUserByID(idSearch).get());
  }

  @Test
  void listUsersShouldWork() {
    userService.addUser(new User("Manolo"));
    userService.addUser(new User("Juan"));
    assertEquals(2, userService.listUsers().size());
  }
}
