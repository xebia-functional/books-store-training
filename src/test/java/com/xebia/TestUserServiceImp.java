package com.xebia;

import static org.junit.jupiter.api.Assertions.*;

import com.xebia.models.User;
import com.xebia.services.User.UserService;
import com.xebia.services.User.UserServiceImp;
import java.util.*;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestUserServiceImp {

  private List<User> users;
  private Logger logger;
  private UserService userService;

  @BeforeEach
  void setUp() {
    logger = Logger.getLogger(TestUserServiceImp.class.getName());
    userService = new UserServiceImp(logger);
  }

  @Test
  public void addUserShouldWork() {
    // Given
    User newUser = new User("Manolo", 12345);

    // When
    boolean result = userService.addUser(newUser);

    // Expected
    assertTrue(result);
  }

  @Test
  public void addUserShouldWorkWhenDuplicate() {
    // Given
    User newUser = new User("Manolo", 12345);
    userService.addUser(newUser);
    User newUser2 = new User("Manolo", 12345);

    // When
    boolean result = userService.addUser(newUser2);

    // Expected
    assertFalse(result);
  }

  @Test
  public void removeBookShouldWork() {
    // Given
    User newUser = new User("Manolo", 12345);
    userService.addUser(newUser);

    // When
    boolean result = userService.removeUser(newUser);

    // Expected
    assertTrue(result);
  }

  @Test
  public void removeBookShouldWorkWhenNotFound() {
    // Given
    User newUser = new User("Manolo", 12345);
    // When
    boolean result = userService.removeUser(newUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void searchUserShouldWork() {
    // Given
    User newUser = new User("Manolo", 12345);
    userService.addUser(newUser);

    // When
    Optional<User> userSearch = userService.searchUser("Manolo");

    // Expected
    assertEquals(newUser, userSearch.get());
  }

  @Test
  public void searchUserShouldWorkWhenNotFound() {
    // Given
    // When
    Optional<User> userSearch = userService.searchUser("Manolo");

    // Expected
    assertTrue(userSearch.isEmpty());
  }

  @Test
  public void listUserShouldWork() {
    // Given
    User user1 = new User("Manolo", 12345);
    User user2 = new User("Pepe", 54321);
    User user3 = new User("Juan", 15243);

    userService.addUser(user1);
    userService.addUser(user2);
    userService.addUser(user3);

    // When
    userService.listUsers();

    // Expected
    assertEquals(3, userService.listUsers().size());
  }

  @Test
  public void listUserShouldWorkWhenEmpty() {
    // Given

    // When
    userService.listUsers();

    // Expected
    assertEquals(0, userService.listUsers().size());
  }
}
