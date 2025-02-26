package com.xebia.services.Book;

import com.xebia.models.Book;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class BookServiceDBImpl implements BookService {

  private final Connection connection;
  private Logger logger;

  public BookServiceDBImpl(Logger logger, Connection connection) throws SQLException {
    this.connection = connection;
    this.logger = logger;
  }

  public boolean containsBook(Book book) {
    try {
      String selectQuery = "SELECT * FROM Book WHERE title = ? AND author = ?";
      PreparedStatement selectStmt = connection.prepareStatement(selectQuery);

      selectStmt.setString(1, book.getTitle());
      selectStmt.setString(2, book.getAuthor());

      ResultSet resultSet = selectStmt.executeQuery();

      return resultSet.next();

    } catch (SQLException e) {
      logger.severe("An error occurred while checking if the book exists in the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean addBook(Book book) {
    try {
      if (containsBook(book)) {
        logger.warning("The book already exists in the list");
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
    try {
      if (!containsBook(book)) {

        logger.warning("The book does not exist in the list.");
        return false;

      } else {

        String deleteQuery = "DELETE FROM Book WHERE author = ? AND title = ?";
        PreparedStatement stmt = connection.prepareStatement(deleteQuery);

        stmt.setString(1, book.getAuthor());
        stmt.setString(2, book.getTitle());
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while removing the book from the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Book> searchBookByTitle(String title) {
    List<Book> books = new ArrayList<>();
    try {
      String selectQuery = "SELECT id, title, author, date, available FROM Book WHERE title = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setString(1, title);

      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        UUID id = (UUID) resultSet.getObject("id");

        Book book =
            new Book(
                id,
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getBoolean("available"));

        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public List<Book> searchBookByAuthor(String author) {
    List<Book> books = new ArrayList<>();
    try {
      String selectQuery = "SELECT id, title, author, date, available FROM Book WHERE author = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setString(1, author);

      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        UUID id = (UUID) resultSet.getObject("id");

        Book book =
            new Book(
                id,
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getBoolean("available"));

        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public Optional<Book> searchBookById(UUID id) {
    try {
      String selectQuery = "SELECT id, title, author, date, available FROM Book WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setObject(1, id);

      ResultSet resultSet = stmt.executeQuery();

      if (resultSet.next()) {
        Book book =
            new Book(
                (UUID) resultSet.getObject("id"),
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getBoolean("available"));

        return Optional.of(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return Optional.empty();
  }

  @Override
  public List<Book> listBooks() {
    List<Book> books = new ArrayList<>();
    try {
      String selectQuery =
          "SELECT id, title, author, date, available FROM Book WHERE available = true";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);

      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        UUID id = (UUID) resultSet.getObject("id");

        Book book =
            new Book(
                id,
                resultSet.getString("title"),
                resultSet.getString("author"),
                resultSet.getDate("date").toLocalDate(),
                resultSet.getBoolean("available"));

        books.add(book);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }

    return books;
  }

  @Override
  public void updateAvailability(UUID bookId, boolean availability) {
    try {
      String updateQuery = "UPDATE Book SET available = ? WHERE id = ?";
      PreparedStatement stmt = connection.prepareStatement(updateQuery);
      stmt.setBoolean(1, availability);
      stmt.setObject(2, bookId);
      stmt.executeUpdate();

    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
