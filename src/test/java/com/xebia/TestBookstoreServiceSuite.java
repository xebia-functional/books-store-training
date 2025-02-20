package com.xebia;

import static org.junit.jupiter.api.Assertions.*;

import com.xebia.models.Book;
import com.xebia.models.User;
import com.xebia.services.Book.BookService;
import com.xebia.services.Book.BookServiceImpl;
import com.xebia.services.Bookstore.BookstoreServiceImp;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.Register.RegisterServiceImp;
import com.xebia.services.User.UserService;
import com.xebia.services.User.UserServiceImp;
import java.util.logging.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class TestBookstoreServiceSuite {

  private Logger logger = Logger.getLogger(BookstoreServiceImp.class.getName());
  private BookService bookSer;
  private UserService userSer;
  private RegisterService regSer;
  private BookstoreServiceImp bsSer;

  @BeforeEach
  void setUp() {
    logger = Logger.getLogger(TestUserServiceSuite.class.getName());
    bookSer = new BookServiceImpl(logger);
    userSer = new UserServiceImp(logger);
    regSer = new RegisterServiceImp(logger);
    bsSer = new BookstoreServiceImp(bookSer, userSer, regSer);
  }

  @Test
  public void requestBookShouldWork() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    bookSer.addBook(sampleBook);
    userSer.addUser(sampleUser);

    // When
    boolean result = bsSer.requestBook(sampleBook, sampleUser);

    // Expected
    assertTrue(result);
  }

  @Test
  public void requestBookShouldWorkWhenUserEmpty() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    bookSer.addBook(sampleBook);

    // When
    boolean result = bsSer.requestBook(sampleBook, sampleUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void requestBookShouldWorkWhenBookEmpty() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    userSer.addUser(sampleUser);

    // When
    boolean result = bsSer.requestBook(sampleBook, sampleUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void requestBookShouldWorkWhenNotAvailable() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    userSer.addUser(sampleUser);
    bookSer.addBook(sampleBook);
    bookSer.updateAvailability(sampleBook.getId(), false);

    // When
    boolean result = bsSer.requestBook(sampleBook, sampleUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void returnBookShouldWork() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    bookSer.addBook(sampleBook);
    userSer.addUser(sampleUser);
    regSer.addRegister(sampleUser.getId(), sampleBook.getId());
    // When
    boolean result = bsSer.returnBook(sampleBook, sampleUser);

    // Expected
    assertTrue(result);
  }

  @Test
  public void returnBookShouldWorkWhenUserEmpty() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    bookSer.addBook(sampleBook);
    regSer.addRegister(sampleUser.getId(), sampleBook.getId());
    // When
    boolean result = bsSer.returnBook(sampleBook, sampleUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void returnBookShouldWorkWhenBookEmpty() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    userSer.addUser(sampleUser);
    regSer.addRegister(sampleUser.getId(), sampleBook.getId());
    // When
    boolean result = bsSer.returnBook(sampleBook, sampleUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void returnBookShouldWorkWhenRegisterEmpty() {
    // Given
    Book sampleBook = new Book("TittleSample", "AuthorSample");
    User sampleUser = new User("NameSample");
    bookSer.addBook(sampleBook);
    userSer.addUser(sampleUser);
    // When
    boolean result = bsSer.returnBook(sampleBook, sampleUser);

    // Expected
    assertFalse(result);
  }

  @Test
  public void listAvailableShouldWork() {
    // Given
    Book sampleBook1 = new Book("TittleSample1", "AuthorSample1");
    Book sampleBook2 = new Book("TittleSample2", "AuthorSample2");
    Book sampleBook3 = new Book("TittleSample3", "AuthorSample3");
    bookSer.addBook(sampleBook1);
    bookSer.addBook(sampleBook2);
    bookSer.addBook(sampleBook3);
    // When
    bsSer.listAvailable();

    // Expected
    assertEquals(3, bsSer.listAvailable().size());
  }

  @Test
  public void listAvailableShouldWorkWhenEmpty() {
    // Given
    // When
    bsSer.listAvailable();

    // Expected
    assertEquals(0, bsSer.listAvailable().size());
  }

  @Test
  public void listBorrowedShouldWork() {
    // Given
    User sampleUser = new User(("Manolo"));
    Book sampleBook1 = new Book("TittleSample1", "AuthorSample1");
    Book sampleBook2 = new Book("TittleSample2", "AuthorSample2");
    Book sampleBook3 = new Book("TittleSample3", "AuthorSample3");
    bookSer.addBook(sampleBook1);
    bookSer.addBook(sampleBook2);
    bookSer.addBook(sampleBook3);

    regSer.addRegister(sampleUser.getId(), sampleBook1.getId());
    regSer.addRegister(sampleUser.getId(), sampleBook2.getId());
    regSer.addRegister(sampleUser.getId(), sampleBook3.getId());

    // When
    bsSer.listBorrowed();

    // Expected
    assertEquals(3, bsSer.listBorrowed().size());
  }

  @Test
  public void listBorrowedShouldWorkWhenEmpty() {
    // Given
    // When
    bsSer.listAvailable();

    // Expected
    assertEquals(0, bsSer.listAvailable().size());
  }
}
