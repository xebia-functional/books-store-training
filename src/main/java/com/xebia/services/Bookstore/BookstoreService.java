package com.xebia.services.Bookstore;

import com.xebia.models.Book;
import com.xebia.models.User;
import java.time.LocalDate;
import java.util.*;

public interface BookstoreService {

  /**
   * This method allows a user to request a book
   *
   * @param user
   * @param book
   * @return boolean
   */
  boolean requestBook(User user, Book book);

  /**
   * This method registers the date when a book is borrowed
   *
   * @param book
   * @param date
   * @return boolean
   */
  boolean recordDate(Book book, LocalDate date);

  /**
   * This method allows to return a book to the bookstore
   *
   * @param book
   * @return boolean
   */
  boolean returnBook(Book book);

  /** This method show a list of all the borrowed books */
  List<Book> listBorrowed();
}
