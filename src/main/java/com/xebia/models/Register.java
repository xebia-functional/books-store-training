package com.xebia.models;

import java.time.LocalDate;
import java.util.*;

public class Register {

  private UUID userId;
  private UUID bookId;
  private LocalDate rentDate;
  private LocalDate returnDate;

  public Register(UUID userId, UUID bookId, LocalDate rentDate) {
    this.userId = userId;
    this.bookId = bookId;
    this.rentDate = rentDate;
  }

  public UUID getUser_id() {
    return userId;
  }

  public UUID getBook_id() {
    return bookId;
  }

  public LocalDate getRent_date() {
    return rentDate;
  }

  public LocalDate getReturn_date() {
    return returnDate;
  }

  @Override
  public String toString() {
    return "Register{"
        + "user_id="
        + userId
        + ", book_id="
        + bookId
        + ", rent_date="
        + rentDate
        + ", return_date="
        + returnDate
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Register register = (Register) o;
    return Objects.equals(userId, register.userId)
        && Objects.equals(bookId, register.bookId)
        && Objects.equals(rentDate, register.rentDate)
        && Objects.equals(returnDate, register.returnDate);
  }

  @Override
  public int hashCode() {
    return Objects.hash(userId, bookId, rentDate, returnDate);
  }
}
