package com.xebia.services.Register;

import com.xebia.models.Register;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Logger;

public class RegisterServiceImp implements RegisterService {

  private List<Register> registers;
  private Logger logger;

  public RegisterServiceImp(Logger logger) {
    this.registers = new ArrayList<>();
    this.logger = logger;
  }

  @Override
  public boolean addRegister(UUID userID, UUID bookID, LocalDate rentDate) {
    Register newRegister = new Register(userID, bookID, rentDate);
    if (registers.contains(newRegister)) {
      logger.warning("The register" + newRegister.toString() + " already exists.");
      return false;
    }
    this.registers.add(newRegister);
    return true;
  }

  @Override
  public boolean addRegister(UUID userID, UUID bookID) {
    return addRegister(userID, bookID, LocalDate.now());
  }

  // in progress
  @Override
  public boolean closeRegister(UUID userID, UUID bookID, LocalDate returnDate) {
    for (Register r : registers) {
      if (r.getUserId().equals(userID) && r.getBookId().equals(bookID) && r.isActive()) {
        r.setReturnDate(Optional.of(returnDate));
        logger.info("The register " + r.toString() + " has been closed successfully.");
        return true;
      }
    }
    logger.warning("The register doesn't exist.");
    return false;
  }

  @Override
  public boolean closeRegister(UUID userID, UUID bookID) {
    return closeRegister(userID, bookID, LocalDate.now());
  }

  @Override
  public boolean removeRegister(UUID userID, UUID bookID, LocalDate rentDate) {
    Register remRegister = new Register(userID, bookID, rentDate);
    if (registers.contains(remRegister)) {
      registers.remove(remRegister);
      logger.info("The register " + remRegister.toString() + " has been removed successfully.");
      return true;
    }
    logger.warning("The register: " + remRegister.toString() + " doesn't exist.");
    return false;
  }

  @Override
  public List<Register> searchRegisterByUser(UUID userID) {
    List<Register> userRegisters = new ArrayList<>();
    for (Register r : registers) {
      if (r.getUserId().equals(userID)) {
        userRegisters.add(r);
      }
    }
    if (userRegisters.isEmpty()) {
      logger.warning("No registers found under the ID " + userID);
    }
    return userRegisters;
  }

  @Override
  public List<Register> searchRegisterByBook(UUID bookID) {
    List<Register> bookRegisters = new ArrayList<>();
    for (Register r : registers) {
      if (r.getBookId().equals(bookID)) {
        bookRegisters.add(r);
      }
    }
    if (bookRegisters.isEmpty()) {
      logger.warning("No registers found under the ID " + bookID);
    }
    return bookRegisters;
  }

  @Override
  public List<Register> listRegistered() {
    logger.info("----- Register's list -----");
    if (registers.isEmpty()) {
      logger.info("No registers found");
    } else {
      for (Register r : registers) {
        logger.info(r.toString());
      }
    }
    return registers;
  }
}
