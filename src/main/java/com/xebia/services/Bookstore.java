package com.xebia.services;

import com.xebia.models.Book;
import com.xebia.models.User;
import java.time.LocalDate;
import java.util.*;

public class Bookstore {
  private final Scanner sc = new Scanner(System.in);

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
    System.out.println("Please enter the book's title");
    String title = sc.nextLine();
    System.out.println("Please enter the book's author");
    String author = sc.nextLine();
    Book newBook = new Book(title, author);
    for (Book b : books) {
      if (newBook.equals(b)) {
        System.out.println("There's already a book registered under the same parameters ");
        return false;
      }
    }
    books.add(newBook);
    System.out.println("The book has been registered successfully");
    return true;
  }

  public boolean removeBook() {
    System.out.println("Please enter the book's title");
    String title = sc.nextLine();
    System.out.println("Please enter the book's author");
    String author = sc.nextLine();
    Book newBook = new Book(title, author);
    for (Book b : books) {
      if (newBook.equals(b)) {

        books.remove(newBook);
        System.out.println("The book has been removed successfully");
        return true;
      }
    }
    System.out.println("There's no existing book related to those parameters ");
    return false;
  }

  public boolean searchBookTitle() {
    System.out.println("Please enter the book's title");
    String title = sc.nextLine();
    for (Book b : books) {
      if (b.getTitle().equalsIgnoreCase(title)) {
        return true;
      }
    }
    return false;
  }

  public boolean searchBookAuthor() {
    System.out.println("Please enter the book's author");
    String author = sc.nextLine();
    for (Book b : books) {
      if (b.getAuthor().equalsIgnoreCase(author)) {
        return true;
      }
    }
    return false;
  }

  public void listAvailable() {
    for (Book b : books) {
      if (b.isAvailable()) {
        System.out.println(b.toString());
      }
    }
  }
}
