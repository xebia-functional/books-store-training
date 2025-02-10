package com.xebia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.xebia.models.Book;
import com.xebia.models.User;
import com.xebia.services.Bookstore;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class testBookstoreSuite {

  private Bookstore bookstore;

  @BeforeEach
  public void setUp() {
    bookstore = new Bookstore();
  }

  @Test
  public void getBooksShouldWork() {

    // Given
    Book book1 = new Book("The Hobbit", "Tolkien");
    Book book2 = new Book("TLOTR 1", "Tolkien");
    bookstore.setBooks(Arrays.asList(book1, book2));

    // When
    List<Book> books = bookstore.getBooks();

    // Expected
    assertEquals(2, books.size());
  }

  @Test
  public void setBooksShouldWork() {

    // Given
    Book book1 = new Book("The Hobbit", "Tolkien");

    // When
    bookstore.setBooks(Arrays.asList(book1));

    // Expected
    assertEquals(1, bookstore.getBooks().size());
  }

  @Test
  public void getUsersShouldWork() {

    // Given
    User user1 = new User("name", 1234);
    bookstore.setUsers(Arrays.asList(user1));

    // When
    List<User> users = bookstore.getUsers();

    // Expected
    assertEquals(1, users.size());
  }

  @Test
  public void setUsersShouldWork() {

    // Given
    User user1 = new User("name", 1245);
    User user2 = new User("name2", 124222225);

    // When
    bookstore.setUsers(Arrays.asList(user1, user2));

    // Expected
    assertEquals(2, bookstore.getUsers().size());
  }

  @Test
  public void setLoansShouldWork() {

    // Given
    LinkedHashMap<String, String> loan = new LinkedHashMap<>();
    loan.put("user1", "book1");
    loan.put("user2", "book2");

    // When
    bookstore.setLoans(loan);

    // Expected
    assertEquals(2, bookstore.getLoans().size());
  }

  @Test
  public void getLoansShouldWork() {

    // Given
    LinkedHashMap<String, String> loan = new LinkedHashMap<>();
    loan.put("user1", "book1");
    loan.put("user2", "book2");
    bookstore.setLoans(loan);

    // When
    Map<String, String> loans = bookstore.getLoans();

    // Expected
    assertEquals(2, loans.size());
  }

  @Test
  public void setLoanDateShouldWork() {

    // Given
    LinkedHashMap<String, LocalDate> loanDate = new LinkedHashMap<>();
    loanDate.put("book1", LocalDate.now());
    loanDate.put("book2", LocalDate.now());

    // When
    bookstore.setLoanDate(loanDate);

    // Expected
    assertEquals(2, bookstore.getLoanDate().size());
  }

  @Test
  public void getLoanDateShouldWork() {

    // Given
    LinkedHashMap<String, LocalDate> loan = new LinkedHashMap<>();
    loan.put("book1", LocalDate.now());
    loan.put("book2", LocalDate.now());
    bookstore.setLoanDate(loan);

    // When
    Map<String, LocalDate> loans = bookstore.getLoanDate();

    // Expected
    assertEquals(2, loans.size());
  }
}
