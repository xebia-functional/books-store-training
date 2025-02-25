package com.xebia.database;

import java.sql.*;

public class DatabaseManager {
  // TODO: Move to a configuration file
  public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_db";
  public static final String USER = "xebia_user";
  public static final String PASSWORD = "xebia_pw";

  public static Connection getConnection() throws SQLException {
    return DriverManager.getConnection(URL, USER, PASSWORD);
  }

  public static Connection getConnection(String url, String user, String password)
      throws SQLException {
    return DriverManager.getConnection(url, user, password);
  }
}
