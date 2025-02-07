package Main;

import java.time.LocalDate;
import java.util.Objects;

public class book {
    private String title;
    private String author;
    private String user;
    private boolean available;
    private LocalDate date;

    public book() {
    }

    public book(String title, String author) {
        this.title = title;
        this.author = author;
        this.user = "";
        this.available = true;
    }

    @Override
    public String toString() {
        return "book{" +
                "title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", user='" + user + '\'' +
                ", available=" + available +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        book book = (book) o;
        return isAvailable() == book.isAvailable() && getDate() == book.getDate() && Objects.equals(getTitle(), book.getTitle()) && Objects.equals(getAuthor(), book.getAuthor()) && Objects.equals(getUser(), book.getUser());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getAuthor(), getUser(), isAvailable(), getDate());
    }

    public String getTitle() {
        return title;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
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

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
