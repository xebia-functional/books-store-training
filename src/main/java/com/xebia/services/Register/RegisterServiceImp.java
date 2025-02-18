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
    for (Register r : registers) {
      if (registers.contains(newRegister)) {
        logger.warning("The register" + newRegister.toString() + " already exists.");
        return false;
      }
    }
    this.registers.add(newRegister);
    return true;
  }

  @Override
  public boolean removeRegister(UUID userID, UUID bookID, LocalDate rentDate) {
    Register remRegister = new Register(userID, bookID, rentDate);
    if (registers.contains(remRegister)) {
      registers.remove(remRegister);
      logger.info("The register " + remRegister.toString() + " has been removed successfully.");
      return true;
    }
    logger.warning("The user: " + remRegister.toString() + " doesn't exist.");
    return false;
  }

  @Override
  public Optional<Register> searchRegisterByUser(UUID userID) {
    for (Register r : registers) {
      if (r.getUserId().equals(userID)) {
        return Optional.of(r);
      }
    }
    logger.warning("No user register under the name " + userID);
    return Optional.empty();
  }

  @Override
  public Optional<Register> searchRegisterByBook(UUID bookID) {
    for (Register r : registers) {
      if (r.getBookId().equals(bookID)) {
        return Optional.of(r);
      }
    }
    logger.warning("No user register under the name " + bookID);
    return Optional.empty();
  }

  @Override
  public List<Register> listRegistered() {
    logger.info("----- Register's list -----");
    if (registers.isEmpty()) {
      logger.info("No users registered.");
    } else {
      for (Register r : registers) {
        logger.info(r.toString());
      }
    }
    return registers;
  }
}
