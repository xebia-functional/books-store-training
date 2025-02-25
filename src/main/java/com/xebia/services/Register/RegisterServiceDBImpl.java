package com.xebia.services.Register;

import com.xebia.models.Register;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class RegisterServiceDBImpl implements RegisterService {

  private final Connection connection;
  private Logger logger;

  public RegisterServiceDBImpl(Logger logger, Connection connection) throws SQLException {
    this.connection = connection;
    this.logger = logger;
  }

  public boolean containsRegister(Register register) {
    try {
      String selectQuery =
          "SELECT * FROM Register WHERE users_id = ? AND book_id = ? AND rentDate = ?";
      PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
      selectStmt.setObject(1, register.getUserId());
      selectStmt.setObject(2, register.getBookId());
      selectStmt.setObject(3, register.getRentDate());
      return selectStmt.executeQuery().next();
    } catch (SQLException e) {
      logger.severe("An error occurred while checking if the register exists in the database.");
      e.printStackTrace();
      return false;
    }
  }

  public boolean containsOpenRegister(Register register) {
    try {
      String selectQuery =
          "SELECT * FROM Register WHERE users_id = ? AND book_id = ? AND rentDate = ? AND returnDate IS NULL";
      PreparedStatement selectStmt = connection.prepareStatement(selectQuery);
      selectStmt.setObject(1, register.getUserId());
      selectStmt.setObject(2, register.getBookId());
      selectStmt.setObject(3, register.getRentDate());
      return selectStmt.executeQuery().next();
    } catch (SQLException e) {
      logger.severe("An error occurred while checking if the register exists in the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean addRegister(UUID userID, UUID bookID, LocalDate rentDate) {
    Register newRegister = new Register(userID, bookID, rentDate);
    try {
      if (containsRegister(newRegister)) {
        logger.warning("The register already exists in the database.");
        return false;
      } else {
        String insertQuery = "INSERT INTO Register (users_id, book_id, rentDate) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setObject(1, newRegister.getUserId());
        stmt.setObject(2, newRegister.getBookId());
        stmt.setObject(3, newRegister.getRentDate());
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while adding the register to the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean addRegister(UUID userID, UUID bookID) {
    Register newRegister = new Register(userID, bookID);
    try {
      if (containsRegister(newRegister)) {
        logger.warning("The register already exists in the database.");
        return false;
      } else {
        String insertQuery = "INSERT INTO Register (users_id, book_id, rentDate) VALUES (?, ?, ?)";
        PreparedStatement stmt = connection.prepareStatement(insertQuery);
        stmt.setObject(1, newRegister.getUserId());
        stmt.setObject(2, newRegister.getBookId());
        stmt.setObject(3, LocalDate.now());
        stmt.executeUpdate();
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while adding the register to the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean closeRegister(UUID userID, UUID bookID, LocalDate returnDate) {
    Register newRegister = new Register(userID, bookID);
    try {
      if (!containsOpenRegister(newRegister)) {
        logger.warning("The register does not exist in the list.");
        return false;
      } else {
        String updateQuery =
            "UPDATE Register SET returnDate = ? WHERE users_id = ? AND book_id = ? AND returnDate IS NULL";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setObject(1, returnDate);
        stmt.setObject(2, userID);
        stmt.setObject(3, bookID);
        stmt.executeUpdate();
        logger.info("The register has been closed successfully");
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while closing the register in the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean closeRegister(UUID userID, UUID bookID) {
    Register newRegister = new Register(userID, bookID);
    try {
      if (!containsOpenRegister(newRegister)) {
        logger.warning("The register does not exist in the list.");
        return false;
      } else {
        String updateQuery =
            "UPDATE Register SET returnDate = ? WHERE users_id = ? AND book_id = ? AND returnDate IS NULL";
        PreparedStatement stmt = connection.prepareStatement(updateQuery);
        stmt.setObject(1, LocalDate.now());
        stmt.setObject(2, userID);
        stmt.setObject(3, bookID);
        stmt.executeUpdate();
        logger.info("The register has been closed successfully");
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while closing the register in the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public boolean removeRegister(UUID userID, UUID bookID, LocalDate rentDate) {
    Register newRegister = new Register(userID, bookID, rentDate);
    try {
      if (!containsRegister(newRegister)) {
        logger.warning("The register does not exist in the list.");
        return false;
      } else {
        String deleteQuery =
            "DELETE FROM Register WHERE users_id = ? AND book_id = ? AND rentDate = ?";
        PreparedStatement stmt = connection.prepareStatement(deleteQuery);
        stmt.setObject(1, userID);
        stmt.setObject(2, bookID);
        stmt.setObject(3, rentDate);
        stmt.executeUpdate();
        logger.info("The register has been removed successfully");
      }
      return true;
    } catch (SQLException e) {
      logger.severe("An error occurred while removing the register from the database.");
      e.printStackTrace();
      return false;
    }
  }

  @Override
  public List<Register> searchRegisterByUser(UUID userID) {
    List<Register> registers = new ArrayList<>();
    try {
      String selectQuery = "SELECT * FROM Register WHERE users_id = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setObject(1, userID);
      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        UUID bookID = (UUID) resultSet.getObject("book_id");
        LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
        Optional<LocalDate> returnDate = Optional.empty();
        if (resultSet.getDate("returnDate") != null) {
          returnDate = Optional.of(resultSet.getDate("returnDate").toLocalDate());
        }

        Register newRegister = new Register(userID, bookID, rentDate, returnDate);
        registers.add(newRegister);
      }
    } catch (SQLException e) {
      logger.severe("An error occurred while searching for the registers by user ID.");
      e.printStackTrace();
    }
    return registers;
  }

  @Override
  public List<Register> searchRegisterByBook(UUID bookID) {
    List<Register> registers = new ArrayList<>();
    try {
      String selectQuery = "SELECT * FROM Register WHERE book_id = ?";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      stmt.setObject(1, bookID);
      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        UUID userID = (UUID) resultSet.getObject("users_id");
        LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
        Optional<LocalDate> returnDate = Optional.empty();
        if (resultSet.getDate("returnDate") != null) {
          returnDate = Optional.of(resultSet.getDate("returnDate").toLocalDate());
        }

        Register newRegister = new Register(userID, bookID, rentDate, returnDate);
        registers.add(newRegister);
      }
    } catch (SQLException e) {
      logger.severe("An error occurred while searching for the registers by book ID.");
      e.printStackTrace();
    }
    return registers;
  }

  @Override
  public List<Register> listRegistered() {
    List<Register> registers = new ArrayList<>();
    try {
      String selectQuery = "SELECT * FROM Register";
      PreparedStatement stmt = connection.prepareStatement(selectQuery);
      ResultSet resultSet = stmt.executeQuery();

      while (resultSet.next()) {
        UUID userID = (UUID) resultSet.getObject("users_id");
        UUID bookID = (UUID) resultSet.getObject("book_id");
        LocalDate rentDate = resultSet.getDate("rentDate").toLocalDate();
        Optional<LocalDate> returnDate = Optional.empty();
        if (resultSet.getDate("returnDate") != null) {
          returnDate = Optional.of(resultSet.getDate("returnDate").toLocalDate());
        }

        Register register = new Register(userID, bookID, rentDate, returnDate);
        registers.add(register);
      }
    } catch (SQLException e) {
      logger.severe("An error occurred while listing registers from the database.");
      e.printStackTrace();
    }
    return registers;
  }
}
