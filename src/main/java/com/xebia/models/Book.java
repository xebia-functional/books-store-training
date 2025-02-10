package com.xebia.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book {
  private String title;
  private String author;
  private String user;
  private boolean available;
  private LocalDate date;

  public Book() {}

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.user = "";
    this.available = true;
  }

  @Override
  public String toString() {
    return "Book{"
        + "title='"
        + title
        + '\''
        + ", author='"
        + author
        + '\''
        + ", user='"
        + user
        + '\''
        + ", available="
        + available
        + ", date="
        + date
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return isAvailable() == book.isAvailable()
        && getDate() == book.getDate()
        && Objects.equals(getTitle(), book.getTitle())
        && Objects.equals(getAuthor(), book.getAuthor())
        && Objects.equals(getUser(), book.getUser());
  }

  @Override
  public int hashCode() {
    return Objects.hash(getTitle(), getAuthor(), getUser(), isAvailable(), getDate());
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getAuthor() {
    return author;
  }

  public void setAuthor(String author) {
    this.author = author;
  }

  public String getUser() {
    return user;
  }

  public void setUser(String user) {
    this.user = user;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }
}
