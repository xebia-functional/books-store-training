package com.xebia.database;

import com.xebia.migrations.FlywayMigration;
import com.xebia.models.Book;

import java.sql.*;
import java.util.UUID;

public class DatabaseManager {
    // TODO: Move to a configuration file
    public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_db";
    public static final String USER = "xebia_user";
    public static final String PASSWORD = "xebia_pw";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    public static void main(String[] args) {
        try (Connection connection = DatabaseManager.getConnection()) {
            Book book = new Book("The Pragmatic Programmer", "Andrew Hunt, David Thomas");
            String selectQuery = "INSERT INTO Book (id, title, author, date, available) VALUES (?, ?, ?, ?, ?)";
            PreparedStatement stmt = connection.prepareStatement(selectQuery);
            stmt.setObject(1, book.getId());
            stmt.setString(2, book.getTitle());
            stmt.setString(3, book.getAuthor());
            stmt.setObject(4, book.getDate());
            stmt.setBoolean(5, book.isAvailable());
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println("An error occurred while adding the book to the database.");
            System.out.println(e.getMessage());
        }

        // Select a book which author is Andrew Hunt, David Thomas
        try {
            Connection connection = DatabaseManager.getConnection();
            String selectQuery = "SELECT * FROM Book WHERE author = ?";
            PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
            selectStmt.setString(1, "Andrew Hunt, David Thomas");
            ResultSet resultSet = selectStmt.executeQuery();
            while (resultSet.next()) {
                String title = resultSet.getString("title");
                String author = resultSet.getString("author");
                Date date = resultSet.getDate("date");
                boolean available = resultSet.getBoolean("available");
                UUID id = (UUID) resultSet.getObject("id");
                Book book = new Book(title, author, id, date.toLocalDate(), available);
                System.out.println(book);
            }
        } catch (SQLException e) {
            System.out.println("An error occurred while searching the book in the database.");
            System.out.println(e.getMessage());
        }
    }
}
