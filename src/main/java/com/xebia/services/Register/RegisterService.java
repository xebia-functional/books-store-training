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
}
