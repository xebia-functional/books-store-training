package com.xebia.register;

import static org.junit.jupiter.api.Assertions.*;

import com.xebia.database.DatabaseManager;
import com.xebia.migrations.FlywayMigration;
import com.xebia.models.Book;
import com.xebia.models.Register;
import com.xebia.models.User;
import com.xebia.services.Book.BookService;
import com.xebia.services.Book.BookServiceDBImpl;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.Register.RegisterServiceDBImpl;
import com.xebia.services.User.UserService;
import com.xebia.services.User.UserServiceDBImpl;
import com.xebia.user.TestUserServiceDBSuite;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.UUID;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestRegisterServiceDBSuite {

  private Logger logger;
  private RegisterService regService;
  private UserService userService;
  private BookService bookService;

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
    regService = new RegisterServiceDBImpl(logger, connection);
    userService = new UserServiceDBImpl(logger, connection);
    bookService = new BookServiceDBImpl(logger, connection);
    connection.prepareStatement("TRUNCATE TABLE Register CASCADE").execute();
    connection.prepareStatement("TRUNCATE TABLE Users CASCADE").execute();
    connection.prepareStatement("TRUNCATE TABLE Book CASCADE").execute();
  }

  @Test
  void addRegisterShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId());
    assertTrue(regService.addRegister(newReg.getUserId(), newReg.getBookId()));
    assertEquals(true, regService.containsRegister(newReg));
  }

  @Test
  void addRegisterWhithDateShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    assertTrue(
        regService.addRegister(newReg.getUserId(), newReg.getBookId(), newReg.getRentDate()));
    assertEquals(true, regService.containsRegister(newReg));
  }

  @Test
  void closeRegisterShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    regService.addRegister(newReg.getUserId(), newReg.getBookId());

    assertTrue(regService.closeRegister(user.getId(), book.getId()));
    assertEquals(false, regService.containsOpenRegister(newReg));
  }

  @Test
  void closeRegisterWithDateShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    regService.addRegister(newReg.getUserId(), newReg.getBookId());

    assertTrue(regService.closeRegister(user.getId(), book.getId(), LocalDate.now()));
    assertEquals(false, regService.containsOpenRegister(newReg));
  }

  @Test
  void removeRegisterShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    regService.addRegister(newReg.getUserId(), newReg.getBookId());

    assertTrue(regService.removeRegister(user.getId(), book.getId(), newReg.getRentDate()));
    assertFalse(regService.containsRegister(newReg));
  }

  @Test
  void searchRegisterByUserShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    regService.addRegister(newReg.getUserId(), newReg.getBookId());

    assertEquals(1, regService.searchRegisterByUser(user.getId()).size());
  }

  @Test
  void searchRegisterByBookShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    regService.addRegister(newReg.getUserId(), newReg.getBookId());

    assertEquals(1, regService.searchRegisterByBook(book.getId()).size());
  }

  @Test
  void listRegisteredShouldWork() {
    User user = new User(UUID.randomUUID(), "Manolo");
    userService.addUser(user);
    Book book = new Book("SampleAuthor", "SampleTitle");
    bookService.addBook(book);
    Register newReg = new Register(user.getId(), book.getId(), LocalDate.now());
    regService.addRegister(newReg.getUserId(), newReg.getBookId());

    assertEquals(1, regService.listRegistered().size());
    assertNotEquals(0, regService.listRegistered().size());
  }
}
