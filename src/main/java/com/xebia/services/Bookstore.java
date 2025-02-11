package com.xebia.services;

import com.xebia.models.Book;
import com.xebia.models.User;
import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

public class Bookstore {
  private final Scanner sc = new Scanner(System.in);
  private static final Logger logger = Logger.getLogger(Bookstore.class.getName());
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

  // Book methods

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

  public boolean searchBookTitle(String title) {
    for (Book b : books) {
      if (b.getTitle().equalsIgnoreCase(title)) {
        return true;
      }
    }
    logger.warning("Book with title '" + title + "' not found.");
    return false;
  }

  public boolean searchBookAuthor(String author) {
    for (Book b : books) {
      if (b.getAuthor().equalsIgnoreCase(author)) {
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

  // User methods

  public boolean addUser() {
    logger.info("Please, enter the user's name: ");
    String name = sc.nextLine();
    logger.info("Please, enter the user's id: ");
    int id = Integer.parseInt(sc.nextLine());
    User newUser = new User(name, id);

    for (User u : users) {
      if (u.equals(newUser)) {
        logger.warning("The user already exists.");
        return false;
      }
    }
    this.users.add(newUser);
    return true;
  }

  public boolean removeUser() {
    logger.info("Please, enter the user's name: ");
    String name = sc.nextLine();
    logger.info("Please, enter the user's id: ");
    int id = Integer.parseInt(sc.nextLine());
    User newUser = new User(name, id);

    for (User u : users) {
      if (u.equals(newUser)) {
        users.remove(newUser);
        logger.info("The user has been removed successfully.");
        return true;
      }
    }
    logger.warning("User doesn't exists.");
    return false;
  }

  public boolean searchUser(String username) {
    for (User u : users) {
      if (u.getName().equalsIgnoreCase(username)) {
        return true;
      }
    }
    logger.warning("No user register under the name " + username);
    return false;
  }

  public void listUsers() {
    logger.info("-----User's list-----");
    for (User u : users) {
      logger.info(u.toString());
    }
  }

  // Bookstore methods

  public void requestBook() {
    logger.info("Please, enter user's name: ");
    String userName = sc.nextLine();
    logger.info("Please, enter book's title: ");
    String bookTitle = sc.nextLine();
    if (searchBookTitle(bookTitle) && searchUser(userName)) {
      loans.put(bookTitle, userName);
      recordDate(bookTitle, LocalDate.now());
      for (Book b : books) {
        if (b.getTitle().equalsIgnoreCase(bookTitle)) {
          b.setAvailable(false);
          b.setUser(userName);
        }
      }
    }
  }

  public void recordDate(String bookTitle, LocalDate date) {
    for (Book b : books) {
      if (b.getTitle().equalsIgnoreCase(bookTitle)) {
        b.setDate(date);
      }
    }
    loanDate.put(bookTitle, date);
  }

  public void returnBook() {
    logger.info("Please, enter book's title: ");
    String bookTitle = sc.nextLine();
    loans.remove(bookTitle);
    loanDate.remove(bookTitle);
    for (Book b : books) {
      if (b.getTitle().equalsIgnoreCase(bookTitle)) {
        b.setAvailable(true);
        b.setUser("");
        b.setDate(null);
      }
    }
  }

  public void listBorrowed() {
    for (Book b : books) {
      logger.info("-----Borrowed books-----");
      if (!b.isAvailable()) {
        logger.info(b.toString());
      }
    }
  }
}
