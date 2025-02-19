package com.xebia.services.Book;

import com.xebia.models.Book;
import java.util.*;
import java.util.logging.Logger;

public class BookServiceImpl implements BookService {

  private List<Book> bookList;
  private Logger logger;

  public BookServiceImpl(Logger logger) {
    this.bookList = new ArrayList<>();
    this.logger = logger;
  }

  @Override
  public boolean addBook(Book book) {
    if (bookList.contains(book)) {
      logger.warning("The book already exists in the list.");
      return false;
    }
    bookList.add(book);
    return true;
  }

  @Override
  public boolean removeBook(Book book) {
    if (bookList.contains(book)) {
      bookList.remove(book);
      return true;
    }
    logger.warning("The book doesn't exists in the list.");
    return false;
  }

  @Override
  public Optional<Book> searchBookByTitle(String title) {
    for (Book b : bookList) {
      if (b.getTitle().equalsIgnoreCase(title)) {
        return Optional.of(b);
      }
    }
    return Optional.empty();
  }

  @Override
  public Optional<Book> searchBookByAuthor(String author) {
    for (Book b : bookList) {
      if (b.getAuthor().equalsIgnoreCase(author)) {
        return Optional.of(b);
      }
    }
    return Optional.empty();
  }

  @Override
  public Optional<Book> searchBookById(UUID id) {
    for (Book b : bookList) {
      if (b.getId().equals(id)) {
        return Optional.of(b);
      }
    }
    return Optional.empty();
  }

  @Override
  public List<Book> listBooks() {
    return bookList;
  }

  @Override
  public void updateAvailability(UUID bookId, boolean availability) {
    searchBookById(bookId).get().setAvailable(availability);
  }
}
