package com.xebia.models;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Book {

  private String title;
  private String author;
  private UUID id;
  private LocalDate date;
  private boolean available;

  public Book(String title, String author, UUID id, LocalDate date, boolean available) {
    this.title = title;
    this.author = author;
    this.id = id;
    this.date = date;
    this.available = available;
  }

  public Book(String title, String author) {
    this.title = title;
    this.author = author;
    this.id = UUID.randomUUID();
    this.available = true;
    this.date = LocalDate.now();
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public UUID getId() {
    return id;
  }

  public LocalDate getDate() {
    return date;
  }

  public boolean isAvailable() {
    return available;
  }

  public void setAvailable(boolean available) {
    this.available = available;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Book book = (Book) o;
    return Objects.equals(title, book.title)
        && Objects.equals(author, book.author)
        && Objects.equals(id, book.id)
        && Objects.equals(date, book.date);
  }

  @Override
  public int hashCode() {
    return Objects.hash(title, author, id, date);
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
        + ", id="
        + id
        + ", date="
        + date
        + '}';
  }
}
