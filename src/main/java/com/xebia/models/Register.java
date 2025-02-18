package com.xebia.models;

import java.time.LocalDate;
import java.util.*;

public class Register {

  private UUID user_id;
  private UUID book_id;
  private LocalDate rent_date;
  private LocalDate return_date;

  public Register(User user, Book book, LocalDate rent_date) {
    this.user_id = user.getId();
    this.book_id = book.getId();
    this.rent_date = rent_date;
  }

  public UUID getUser_id() {
    return user_id;
  }

  public void setUser_id(UUID user_id) {
    this.user_id = user_id;
  }

  public UUID getBook_id() {
    return book_id;
  }

  public void setBook_id(UUID book_id) {
    this.book_id = book_id;
  }

  public LocalDate getRent_date() {
    return rent_date;
  }

  public void setRent_date(LocalDate rent_date) {
    this.rent_date = rent_date;
  }

  public LocalDate getReturn_date() {
    return return_date;
  }

  public void setReturn_date(LocalDate return_date) {
    this.return_date = return_date;
  }

  @Override
  public String toString() {
    return "Register{"
        + "user_id="
        + user_id
        + ", book_id="
        + book_id
        + ", rent_date="
        + rent_date
        + ", return_date="
        + return_date
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Register register = (Register) o;
    return Objects.equals(user_id, register.user_id)
        && Objects.equals(book_id, register.book_id)
        && Objects.equals(rent_date, register.rent_date)
        && Objects.equals(return_date, register.return_date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(user_id, book_id, rent_date, return_date);
  }
}
