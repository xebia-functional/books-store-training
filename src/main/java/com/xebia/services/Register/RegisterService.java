package com.xebia.services.Register;

import com.xebia.models.Register;
import java.time.LocalDate;
import java.util.*;

public interface RegisterService {

  /**
   * Method that adds a new register
   *
   * @param userID
   * @param bookID
   * @param rentDate
   * @return
   */
  boolean addRegister(UUID userID, UUID bookID, LocalDate rentDate);

  /**
   * This method adds a new register setting the date as now
   *
   * @param userID
   * @param bookID
   * @return
   */
  boolean addRegister(UUID userID, UUID bookID);

  /**
   * This method will set the return date to the register, introducing manually the date
   *
   * @param userID
   * @param bookID
   * @param returnDate
   * @return
   */
  boolean closeRegister(UUID userID, UUID bookID, LocalDate returnDate);

  /**
   * This method will set the return date to the register, meaning that the book was returned at
   * `returnDate`
   *
   * @param userID
   * @param bookID
   * @return
   */
  boolean closeRegister(UUID userID, UUID bookID);

  /**
   * Method that removes a register
   *
   * @param userID
   * @param bookID
   * @param rentDate
   * @return
   */
  boolean removeRegister(UUID userID, UUID bookID, LocalDate rentDate);

  /**
   * Method ta searches a register by userID
   *
   * @param userID
   * @return
   */
  List<Register> searchRegisterByUser(UUID userID);

  /**
   * Method ta searches a register by bookID
   *
   * @param bookID
   * @return
   */
  List<Register> searchRegisterByBook(UUID bookID);

  /**
   * Method that lists all registered elements
   *
   * @return
   */
  List<Register> listRegistered();

  /**
   * Method that checks if a Register exists in the database
   *
   * @param register
   * @return
   */
  public boolean containsRegister(Register register);

  /**
   * Method that checks if a Register exists in the database and is not closed
   *
   * @param register
   * @return
   */
  public boolean containsOpenRegister(Register register);
}
