package com.xebia.services.Bookstore;

import com.xebia.models.Book;
import com.xebia.models.User;
import java.util.*;

public class Bookstore {

  private List<Book> bookList;
  private List<Book> unavailableBooks;
  private List<User> userList;
  private Map<String, String> loans;
  private Map<String, String> loanDate;

  public Bookstore() {
    this.bookList = new ArrayList<>();
    this.unavailableBooks = new ArrayList<>();
    this.userList = new ArrayList<>();
    this.loans = new LinkedHashMap<>();
    this.loanDate = new LinkedHashMap<>();
  }

  public List<Book> getBookList() {
    return bookList;
  }

  public List<Book> getUnavailableBooks() {
    return unavailableBooks;
  }

  public List<User> getUserList() {
    return userList;
  }

  public Map<String, String> getLoans() {
    return loans;
  }

  public Map<String, String> getLoanDate() {
    return loanDate;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Bookstore bookstore = (Bookstore) o;
    return Objects.equals(bookList, bookstore.bookList)
        && Objects.equals(unavailableBooks, bookstore.unavailableBooks)
        && Objects.equals(userList, bookstore.userList)
        && Objects.equals(loans, bookstore.loans)
        && Objects.equals(loanDate, bookstore.loanDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(bookList, unavailableBooks, userList, loans, loanDate);
  }
}
