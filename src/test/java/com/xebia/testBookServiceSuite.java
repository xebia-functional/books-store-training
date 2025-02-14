package com.xebia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.xebia.models.Book;
import com.xebia.services.Book.BookService;
import com.xebia.services.Book.BookServiceImpl;
import java.util.*;
import java.util.logging.Logger;
import org.junit.jupiter.api.*;

public class testBookServiceSuite {

  // private List<Book> bookList;
  private Logger log = Logger.getLogger(testBookServiceSuite.class.getName());
  private BookService bs = new BookServiceImpl(log);

  @BeforeEach
  public void setUp() {}

  @Test
  public void addBookShouldWork() {
    // Given
    Book b1 = new Book("Title1", "Author1");
    Book b2 = new Book("Title2", "Author2");
    Book b3 = new Book("Title3", "Author3");
    // When
    bs.addBook(b1);
    bs.addBook(b1);
    bs.addBook(b2);
    // Expected
    assertEquals(2, bs.listBooks().size());
  }

  @Test
  public void removeBookShouldWork() {
    // Given
    Book b1 = new Book("Title1", "Author1");
    Book b2 = new Book("Title2", "Author2");
    Book b3 = new Book("Title3", "Author3");
    bs.addBook(b1);
    bs.addBook(b2);
    bs.addBook(b2);
    // When
    bs.removeBook(b2);
    // Expected
    assertEquals(1, bs.listBooks().size());
  }

  @Test
  public void searchBookShouldWork() {
    // Given
    Book b1 = new Book("Title1", "Author1");
    Book b2 = new Book("Title2", "Author2");
    Book b3 = new Book("Title3", "Author3");
    bs.addBook(b1);
    bs.addBook(b2);
    bs.addBook(b3);
    // When
    Optional<Book> booksearch = bs.searchBook("Title3", "Author3");
    // Expected
    assertEquals(b3, booksearch.get());
  }

  @Test
  public void searchBookByTitleShouldWork() {
    // Given
    Book b1 = new Book("Title1", "Author1");
    Book b2 = new Book("Title2", "Author2");
    Book b3 = new Book("Title3", "Author3");
    bs.addBook(b1);
    bs.addBook(b2);
    bs.addBook(b3);
    // When
    Optional<Book> booksearch = bs.searchBookByTitle("title2");
    // Expected
    assertEquals(b2, booksearch.get());
  }

  @Test
  public void searchBookByAuthorShouldWork() {
    // Given
    Book b1 = new Book("Title1", "Author1");
    Book b2 = new Book("Title2", "Author2");
    Book b3 = new Book("Title3", "Author3");
    bs.addBook(b1);
    bs.addBook(b2);
    bs.addBook(b3);
    // When
    Optional<Book> booksearch = bs.searchBookByAuthor("author1");
    // Expected
    assertEquals(b1, booksearch.get());
  }

  @Test
  public void listBooksShouldWork() {
    // Given
    Book b1 = new Book("Title1", "Author1");
    Book b2 = new Book("Title2", "Author2");
    Book b3 = new Book("Title3", "Author3");

    // When
    bs.addBook(b1);
    bs.addBook(b2);
    bs.addBook(b3);
    // Expected
    assertEquals(3, bs.listBooks().size());
  }
}
