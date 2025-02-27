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
   * This method searches a Book in a List by its title
   *
   * @param title
   * @return Optional<Book>
   */
  List<Book> searchBookByTitle(String title);

  /**
   * This method searches a Book in a List by its author
   *
   * @param author
   * @return Optional<Book>
   */
  List<Book> searchBookByAuthor(String author);

  /**
   * This method searches a Book in a List by its id
   *
   * @param id
   * @return Optional<Book>
   */
  Optional<Book> searchBookById(UUID id);

  /**
   * This method return the List of available Books
   *
   * @return List<Book>
   */
  List<Book> listBooks();

  /**
   * This method updates the availability of a book
   *
   * @param bookId
   * @param availability
   */
  void updateAvailability(UUID bookId, boolean availability);

  boolean containsBook(Book newBook);
}
