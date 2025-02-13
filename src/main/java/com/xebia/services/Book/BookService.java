package com.xebia.services.Book;

import com.xebia.models.Book;
import java.util.*;

public interface BookService {

  /**
   * This method adds a Book to a List
   *
   * @param book
   * @return boolean
   */
  boolean addBook(Book book);

  /**
   * This method removes a Book from a List
   *
   * @param book
   * @return boolean
   */
  boolean removeBook(Book book);

  /**
   * This method searches a Book in a List by its title and author
   *
   * @param title
   * @param author
   * @return Optional<Book>
   */
  Optional<Book> searchBook(String title, String author);

  /**
   * This method searches a Book in a List by its title
   *
   * @param title
   * @return Optional<Book>
   */
  Optional<Book> searchBookByTitle(String title);

  /**
   * This method searchs a Book in a List by its author
   *
   * @param author
   * @return Optional<Book>
   */
  Optional<Book> searchBookByAuthor(String author);

  /**
   * This method return the List of available Books
   *
   * @return List<Book>
   */
  List<Book> listBooks();
}
