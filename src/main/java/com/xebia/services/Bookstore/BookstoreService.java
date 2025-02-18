package com.xebia.services.Bookstore;

import com.xebia.models.*;
import java.util.*;

public interface BookstoreService {

  /**
   * This method allows an user to request a book
   *
   * @param book
   * @param user
   * @return boolean
   */
  boolean requestBook(Book book, User user);

  /**
   * This method allows an user to return a book
   *
   * @param book
   * @param user
   * @return boolean
   */
  boolean returnBook(Book book, User user);

  /**
   * This method gives back the list of available books
   *
   * @return List<Book>
   */
  List<Book> listAvailable();

  /**
   * This method gives back the list of borrowed books
   *
   * @return @return List<Book>
   */
  List<Book> listBorrowed();
}
