package com.xebia.book;

import static org.junit.jupiter.api.Assertions.*;

import com.xebia.database.DatabaseManager;
import com.xebia.migrations.FlywayMigration;
import com.xebia.models.Book;
import com.xebia.services.Book.BookService;
import com.xebia.services.Book.BookServiceDBImpl;
import com.xebia.user.TestUserServiceDBSuite;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.PostgreSQLContainer;

public class TestBookServiceDBSuite {

  private Logger logger;
  private BookService bookService;

  static PostgreSQLContainer<?> postgres =
      new PostgreSQLContainer<>("postgres:15")
          .withDatabaseName("bookstore_db")
          .withUsername("xebia_user")
          .withPassword("xebia_pw");

  @BeforeAll
  static void beforeAll() {
    postgres.start();
    FlywayMigration.migrate(postgres);
    System.out.println("Postgres URL: " + postgres.getJdbcUrl());
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
    bookService = new BookServiceDBImpl(logger, connection);
    connection.prepareStatement("TRUNCATE TABLE Users CASCADE").execute();
  }

  @Test
  public void addBookShouldWork() {
    Book newBook =
        new Book(UUID.randomUUID(), "sampleTitle", "sampleAuthor", LocalDate.now(), true);
    assertTrue(bookService.addBook(newBook));
    assertEquals(true, bookService.containsBook(newBook));
  }

  @Test
  void removeBookShouldWork() {
    Book newBook =
        new Book(UUID.randomUUID(), "sampleTitle", "sampleAuthor", LocalDate.now(), true);
    bookService.addBook(newBook);
    assertTrue(bookService.removeBook(newBook));
    assertFalse(bookService.containsBook(newBook));
  }

  @Test
  void searchBookByTitleShouldWork() {
    Book newBook =
        new Book(UUID.randomUUID(), "sampleTitle", "sampleAuthor", LocalDate.now(), true);
    bookService.addBook(newBook);
    List<Book> sampleList = bookService.searchBookByTitle(newBook.getTitle());
    assertEquals(sampleList, bookService.searchBookByTitle(newBook.getTitle()));
  }

  @Test
  void searchBookByAuthorShouldWork() {
    Book newBook =
        new Book(UUID.randomUUID(), "sampleTitle", "sampleAuthor", LocalDate.now(), true);
    bookService.addBook(newBook);
    List<Book> sampleList = bookService.searchBookByAuthor(newBook.getAuthor());
    assertEquals(sampleList, bookService.searchBookByAuthor(newBook.getAuthor()));
  }

  @Test
  void searchUserByIdShouldWork() {

    UUID id = UUID.randomUUID();
    Book newBook = new Book(id, "sampleTitle", "sampleAuthor", LocalDate.now(), true);
    bookService.addBook(newBook);
    assertEquals(newBook, bookService.searchBookById(id).get());
  }

  @Test
  public void listBooksShouldWork() {
    bookService.addBook(
        new Book(UUID.randomUUID(), "sampleTitle1", "sampleAuthor1", LocalDate.now(), true));
    bookService.addBook(
        new Book(UUID.randomUUID(), "sampleTitle2", "sampleAuthor2", LocalDate.now(), true));
    bookService.addBook(
        new Book(UUID.randomUUID(), "sampleTitle3", "sampleAuthor3", LocalDate.now(), true));
    List<Book> sampleList = bookService.listBooks();

    bookService.listBooks();
    assertEquals(3, sampleList.size());
  }

  @Test
  public void updateAvailability() {
    UUID id = UUID.randomUUID();
    Book newBook = new Book(id, "sampleTitle", "sampleAuthor", LocalDate.now(), true);
    bookService.addBook(newBook);
    bookService.updateAvailability(id, false);

    Optional<Book> sampleBook = bookService.searchBookById(id);
    assertEquals(false, sampleBook.get().isAvailable());
  }
}
