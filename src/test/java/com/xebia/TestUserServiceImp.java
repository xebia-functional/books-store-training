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
    User newUser = new User("Manolo");

    // When
    boolean result = userService.addUser(newUser);

    // Expected
    assertTrue(result);
  }

  @Test
  public void addUserShouldWorkWhenDuplicate() {
    // Given
    User newUser = new User("Manolo");
    userService.addUser(newUser);
    User newUser2 = new User("Manolo");

    // When
    boolean result = userService.addUser(newUser2);

    // Expected
    assertFalse(result);
  }

  @Test
  public void removeUserShouldWork() {
    // Given
    User newUser = new User("Manolo");
    userService.addUser(newUser);

    // When
    boolean result = userService.removeUser(newUser);

    // Expected
    assertTrue(result);
  }

  @Test
  public void removeBookShouldWorkWhenNotFound() {
    // Given
    User newUser = new User("Manolo");
    // When
    boolean result = userService.removeUser(newUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void searchUserByNameShouldWork() {
    // Given
    User newUser = new User("Manolo");
    userService.addUser(newUser);

    // When
    Optional<User> userSearch = userService.searchUserByName("Manolo");

    // Expected
    assertEquals(newUser, userSearch.get());
  }

  @Test
  public void searchUserByNameShouldWorkWhenNotFound() {
    // Given
    // When
    Optional<User> userSearch = userService.searchUserByName("Manolo");

    // Expected
    assertTrue(userSearch.isEmpty());
  }

  @Test
  public void searchUserByIDShouldWork() {
    // Given
    User newUser = new User("Manolo");
    userService.addUser(newUser);

    // When
    Optional<User> userSearch = userService.searchUserByID(newUser.getId());

    // Expected
    assertEquals(newUser, userSearch.get());
  }

  @Test
  public void searchUserByIDShouldWorkWhenNotFound() {
    // Given
    User newUser = new User("Manolo");
    // When
    Optional<User> userSearch = userService.searchUserByID(newUser.getId());
    // Expected
    assertTrue(userSearch.isEmpty());
  }

  @Test
  public void listUserShouldWork() {
    // Given
    User user1 = new User("Manolo");
    User user2 = new User("Pepe");
    User user3 = new User("Juan");

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
