package com.xebia.services.Book;

import com.xebia.database.DatabaseManager;
import com.xebia.models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class BookServiceDBImpl implements BookService {

  private final Connection connection;
  private Logger logger;

  public BookServiceDBImpl(Logger logger) throws SQLException {
    this.connection = DatabaseManager.getConnection();
    this.logger = logger;
  }

  private boolean containsBook(Book book) {
    try {
      String selectQuery = "SELECT * FROM Book WHERE id = ?";
      PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
      selectStmt.setObject(1, book.getId());
      return selectStmt.executeQuery().next();
    } catch (SQLException e) {
      logger.severe("An error occurred while checking if the book exists in the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean addBook(Book book) {
    try (Connection connection = DatabaseManager.getConnection()) {
      if (containsBook(book)) {
        logger.warning("The book already exists in the list.");
        return false;
      } else {
        String insertQuery =
            "INSERT INTO Book (id, title, author, date, available) VALUES (?, ?, ?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setObject(1, book.getId());
        stmt.setString(2, book.getTitle());
        stmt.setString(3, book.getAuthor());
        stmt.setObject(4, book.getDate());
        stmt.setBoolean(5, book.isAvailable());
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while adding the book to the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean removeBook(Book book) {
    return false;
  }

  @Override
  public Optional<Book> searchBookByTitle(String title) {
    return Optional.empty();
  }

  @Override
  public List<Book> searchBookByAuthor(String author) {
    return List.of();
  }

  @Override
  public Optional<Book> searchBookById(UUID id) {
    return Optional.empty();
  }

  @Override
  public List<Book> listBooks() {
    return List.of();
  }

  @Override
  public void updateAvailability(UUID bookId, boolean availability) {}
}
