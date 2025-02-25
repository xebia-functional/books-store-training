package com.xebia.models;

import java.time.LocalDate;
import java.util.*;

public class Register {

  private UUID userId;
  private UUID bookId;
  private LocalDate rentDate;
  private Optional<LocalDate> returnDate;

  public Register(UUID userId, UUID bookId) {
    this.userId = userId;
    this.bookId = bookId;
    this.rentDate = LocalDate.now();
    this.returnDate = Optional.empty();
  }

  public Register(UUID userId, UUID bookId, LocalDate rentDate) {
    this.userId = userId;
    this.bookId = bookId;
    this.rentDate = rentDate;
    this.returnDate = Optional.empty();
  }

  public Register(UUID userId, UUID bookId, LocalDate rentDate, Optional<LocalDate> returnDate) {
    this.userId = userId;
    this.bookId = bookId;
    this.rentDate = rentDate;
    this.returnDate = returnDate;
  }

  public UUID getUserId() {
    return userId;
  }

  public UUID getBookId() {
    return bookId;
  }

  public LocalDate getRentDate() {
    return rentDate;
  }

  public Optional<LocalDate> getReturnDate() {
    return returnDate;
  }

  public void setReturnDate(Optional<LocalDate> returnDate) {
    this.returnDate = returnDate;
  }

  public boolean isActive() {
    return returnDate.isEmpty();
  }

  @Override
  public String toString() {
    return "Register{"
        + "user id="
        + userId
        + ", book id="
        + bookId
        + ", rent date="
        + rentDate
        + ", return date="
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
