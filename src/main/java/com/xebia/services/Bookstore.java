package com.xebia.services;

import com.xebia.models.Book;
import com.xebia.models.User;
import java.time.LocalDate;
import java.util.*;

public class Bookstore {
  private final Scanner sc = new Scanner(System.in);
private static final Logger logger = Logger.getLogger(BookManager.class.getName());
  private List<Book> books;
  private List<User> users;
  private Map<String, String> loans;
  private Map<String, LocalDate> loanDate;

  public Bookstore() {
    this.books = new ArrayList<>();
    this.users = new ArrayList<>();
    this.loans = new LinkedHashMap<>();
    this.loanDate = new LinkedHashMap<>();
  }

  public Bookstore(
      List<Book> books,
      List<User> users,
      Map<String, String> loans,
      Map<String, LocalDate> loanDate) {
    this.books = books;
    this.users = users;
    this.loans = loans;
    this.loanDate = loanDate;
  }

  public List<Book> getBooks() {
    return books;
  }

  public void setBooks(List<Book> books) {
    this.books = books;
  }

  public List<User> getUsers() {
    return users;
  }

  public void setUsers(List<User> users) {
    this.users = users;
  }

  public Map<String, String> getLoans() {
    return loans;
  }

  public void setLoans(Map<String, String> loans) {
    this.loans = loans;
  }

  public Map<String, LocalDate> getLoanDate() {
    return loanDate;
  }

  public void setLoanDate(Map<String, LocalDate> loanDate) {
    this.loanDate = loanDate;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Bookstore bookstore = (Bookstore) o;
    return Objects.equals(books, bookstore.books)
        && Objects.equals(users, bookstore.users)
        && Objects.equals(loans, bookstore.loans)
        && Objects.equals(loanDate, bookstore.loanDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(books, users, loans, loanDate);
  }

  public boolean addBook() {
    logger.info("Please enter the book's title");
    String title = sc.nextLine();
    logger.info("Please enter the book's author");
    String author = sc.nextLine();
    Book newBook = new Book(title, author);
    for (Book b : books) {
      if (newBook.equals(b)) {
        logger.warning("There's already a book registered under the same parameters ");
        return false;
      }
    }
    books.add(newBook);
    logger.info("The book has been registered successfully");
    return true;
  }

  public boolean removeBook() {
        logger.info("Please enter the book's title");
        String title = sc.nextLine();
        logger.info("Please enter the book's author");
        String author = sc.nextLine();
        Book newBook = new Book(title, author);
        for (Book b : books) {
            if (newBook.equals(b)) {
                books.remove(newBook);
                logger.info("The book has been removed successfully");
                return true;
            }
        }
        logger.warning("There's no existing book related to those parameters");
        return false;
    }

    public boolean searchBookTitle() {
        logger.info("Please enter the book's title");
        String title = sc.nextLine();
        for (Book b : books) {
            if (b.getTitle().equalsIgnoreCase(title)) {
                logger.info("Book found: " + b.getTitle());
                return true;
            }
        }
        logger.warning("Book with title '" + title + "' not found.");
        return false;
    }

    public boolean searchBookAuthor() {
        logger.info("Please enter the book's author");
        String author = sc.nextLine();
        for (Book b : books) {
            if (b.getAuthor().equalsIgnoreCase(author)) {
                logger.info("Book by author '" + author + "' found: " + b.getTitle());
                return true;
            }
        }
        logger.warning("No books found by author '" + author + "'.");
        return false;
    }

    public void listAvailable() {
        boolean foundAvailable = false;
        for (Book b : books) {
            if (b.isAvailable()) {
                logger.info("Available book: " + b.toString());
                foundAvailable = true;
            }
        }
        if (!foundAvailable) {
            logger.warning("No books are available.");
        }
    }
}
