package com.xebia.models;

import java.time.LocalDate;
import java.util.Objects;

public class Book {

  private String title;
  private String author;
  private LocalDate date;

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public LocalDate getDate() {
    return date;
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
        + ", date="
        + date
        + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(title, book.title)
        && Objects.equals(author, book.author)
        && Objects.equals(date, book.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, date);
  }
}
