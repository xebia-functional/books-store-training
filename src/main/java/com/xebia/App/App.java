package com.xebia.App;

import com.xebia.database.DatabaseManager;
import com.xebia.migrations.FlywayMigration;
import com.xebia.models.Book;
import com.xebia.models.User;
import com.xebia.services.Book.BookService;
import com.xebia.services.Book.BookServiceDBImpl;
import com.xebia.services.Bookstore.BookstoreService;
import com.xebia.services.Bookstore.BookstoreServiceImp;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.Register.RegisterServiceDBImpl;
import com.xebia.services.User.UserService;
import com.xebia.services.User.UserServiceDBImpl;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class App {

  public static final String URL = "jdbc:postgresql://localhost:5432/bookstore_db";
  public static final String USER = "xebia_user";
  public static final String PASSWORD = "xebia_pw";

  private static BookService bookSer;
  private static UserService userSer;
  private static RegisterService regSer;
  private static BookstoreService bsSer;

  public static Scanner sc = new Scanner(System.in);

  public static void main(String[] args) throws SQLException {
    Logger logger = Logger.getLogger(App.class.getName());
    Connection connection = DatabaseManager.getConnection(URL, USER, PASSWORD);
    FlywayMigration.migrate(URL, USER, PASSWORD);

    bookSer = new BookServiceDBImpl(logger, connection);
    userSer = new UserServiceDBImpl(logger, connection);
    regSer = new RegisterServiceDBImpl(logger, connection);
    bsSer = new BookstoreServiceImp(bookSer, userSer, regSer);

    boolean exit = false;
    int answr = 0;
    do {
      System.out.println("*****MENU*****");
      System.out.println("1. Manage Books");
      System.out.println("2. Manage Users");
      System.out.println("3. Manage Bookstore");
      System.out.println("4. Exit");
      answr = Integer.parseInt(sc.nextLine());

      switch (answr) {
        case 1 -> manageBooks();
        case 2 -> manageUsers();
        case 3 -> manageBookstore();
        case 4 -> exit = true;
        default -> System.out.println("Please select one of the proposed options");
      }
    } while (!exit);
  }

  private static void manageBooks() {
    int opt = 0;
    boolean exit = false;
    do {
      System.out.println("*****Book Options*****");
      System.out.println("1. Add Book");
      System.out.println("2. Remove Book");
      System.out.println("3. Search by title");
      System.out.println("4. Search by author");
      System.out.println("5. Search by ID");
      System.out.println("6. List Books");
      System.out.println("7. update Availability");
      System.out.println("8. Exit");
      opt = Integer.parseInt(sc.nextLine());

      switch (opt) {
        case 1 -> {
          System.out.println("Please introduce your book's title");
          String title = sc.nextLine();
          System.out.println("Please introduce your book's author");
          String author = sc.nextLine();
          Book newBook = new Book(UUID.randomUUID(), title, author, LocalDate.now(), true);
          bookSer.addBook(newBook);
        }
        case 2 -> {
          System.out.println("Please introduce your book's title");
          String title = sc.nextLine();
          System.out.println("Please introduce your book's author");
          String author = sc.nextLine();

          List<Book> booksbyTitle = bookSer.searchBookByTitle(title);

          List<Book> booksByAuthor =
              booksbyTitle.stream()
                  .filter(b -> b.getAuthor().equals(author)) //
                  .collect(Collectors.toList());

          if (!booksByAuthor.isEmpty()) {
            Book remBook = booksByAuthor.get(0);
            boolean result = bookSer.removeBook(remBook);
            if (result) {
              System.out.println("Book removed successfully.");
            } else {
              System.out.println("An error occurred while trying to remove the book.");
            }
          } else {
            System.out.println("No books found with the given title and author.");
          }
        }
        case 3 -> {
          System.out.println("Please introduce your book's title");
          String title = sc.nextLine();
          List<Book> books = bookSer.searchBookByTitle(title);

          if (books.isEmpty()) {
            System.out.println("No books found with the given title .");
          } else {
            for (Book b : books) {
              System.out.println(b);
            }
          }
        }
        case 4 -> {
          System.out.println("Please introduce your book's author");
          String author = sc.nextLine();

          List<Book> books = bookSer.searchBookByAuthor(author);

          if (books.isEmpty()) {
            System.out.println("No books found with the given author .");
          } else {
            for (Book b : books) {
              System.out.println(b);
            }
          }
        }
        case 5 -> {
          System.out.println("Please introduce your book's ID");
          String input = sc.nextLine();
          UUID ID = UUID.fromString(input);

          Optional<Book> book = bookSer.searchBookById(ID);

          if (book.isEmpty()) {
            System.out.println("The book does not exists in the database");
          } else {
            System.out.println(book.get());
          }
        }
        case 6 -> {
          List<Book> books = bookSer.listBooks();
          for (Book b : books) {
            System.out.println(b);
          }
        }
        case 7 -> {
          System.out.println("Please introduce your book's ID");
          String input = sc.nextLine();
          UUID ID = UUID.fromString(input);
          System.out.println("Please introduce your book's updated availability");
          String input2 = sc.nextLine();
          boolean available = Boolean.parseBoolean(input);
          bookSer.updateAvailability(ID, available);
        }
        case 8 -> exit = true;
        default -> System.out.println("Please select one of the proposed options");
      }
    } while (!exit);
  }

  private static void manageUsers() {
    int opt = 0;
    boolean exit = false;
    do {
      System.out.println("*****User Options*****");
      System.out.println("1. Add User");
      System.out.println("2. Remove User");
      System.out.println("3. Search by Name");
      System.out.println("4. Search by ID");
      System.out.println("5. List Users");
      System.out.println("6. Exit");
      opt = Integer.parseInt(sc.nextLine());

      switch (opt) {
        case 1 -> {
          System.out.println("Please introduce your username");
          String name = sc.nextLine();
          User newUser = new User(UUID.randomUUID(), name);
          userSer.addUser(newUser);
        }
        case 2 -> {
          System.out.println("Please introduce your username");
          String name = sc.nextLine();
          Optional<User> userbyName = userSer.searchUserByName(name);

          if (userbyName.isPresent()) {
            User remUser = new User(userbyName.get().getId(), name);
            boolean result = userSer.removeUser(remUser);
            if (result) {
              System.out.println("User removed successfully.");
            } else {
              System.out.println("An error occurred while trying to remove the user.");
            }
          } else {
            System.out.println("No user found with the given username.");
          }
        }
        case 3 -> {
          System.out.println("Please introduce your user's name");
          String name = sc.nextLine();

          Optional<User> user = userSer.searchUserByName(name);

          if (user.isEmpty()) {
            System.out.println("The user does not exists in the database");
          } else {
            System.out.println(user.get());
          }
        }
        case 4 -> {
          System.out.println("Please introduce your user's ID");
          String input = sc.nextLine();
          UUID ID = UUID.fromString(input);

          Optional<User> user = userSer.searchUserByID(ID);

          if (user.isEmpty()) {
            System.out.println("The user does not exists in the database");
          } else {
            System.out.println(user.get());
          }
        }
        case 5 -> {
          List<User> users = userSer.listUsers();
          for (User u : users) {
            System.out.println(u);
          }
        }
        case 6 -> exit = true;
        default -> System.out.println("Please select one of the proposed options");
      }
    } while (!exit);
  }

  private static void manageBookstore() {
    boolean exit = false;
    do {
      System.out.println("*****MENU*****");
      System.out.println("1. Request book");
      System.out.println("2. Return book");
      System.out.println("3. List available books");
      System.out.println("4. List borrowed books");
      System.out.println("5. Exit");

      int answr = Integer.parseInt(sc.nextLine());

      switch (answr) {
        case 1 -> {
          System.out.println("Introduce your username:");
          String name = sc.nextLine();

          User user = userSer.searchUserByName(name).get();

          System.out.println("Introduce book title:");
          String title = sc.nextLine();
          System.out.println("Introduce book author:");
          String author = sc.nextLine();

          List<Book> booksbyTitle = bookSer.searchBookByTitle(title);
          List<Book> bookbyAuthor = List.of();
          for (Book b : booksbyTitle) {
            bookbyAuthor = bookSer.searchBookByAuthor(b.getAuthor());
          }
          Book book = bookbyAuthor.get(0);

          bsSer.requestBook(book, user);
        }
        case 2 -> {
          System.out.println("Introduce your username:");
          String name = sc.nextLine();

          Optional<User> userOptional = userSer.searchUserByName(name);
          if (userOptional.isEmpty()) {
            System.out.println("User not found.");
            break;
          }
          User user = userOptional.get();

          System.out.println("Introduce book title:");
          String title = sc.nextLine();
          System.out.println("Introduce book author:");
          String author = sc.nextLine();

          List<Book> booksbyTitle = bookSer.searchBookByTitle(title);

          List<Book> booksByAuthor =
              booksbyTitle.stream()
                  .filter(b -> b.getAuthor().equals(author))
                  .collect(Collectors.toList());

          if (booksByAuthor.isEmpty()) {
            System.out.println("No books found with the given title and author.");
            break; // Salimos si no hay libros que coincidan
          }

          Book book = booksByAuthor.get(0);

          boolean success = bsSer.returnBook(book, user);
          if (success) {
            System.out.println("Book returned successfully.");
          } else {
            System.out.println("An error occurred while returning the book.");
          }
        }
        case 3 -> {
          List<Book> books = bsSer.listAvailable();
          for (Book b : books) {
            System.out.println(b);
          }
        }
        case 4 -> {
          List<Book> books = bsSer.listBorrowed();
          for (Book b : books) {
            System.out.println(b);
          }
        }
        case 5 -> exit = true;
      }
    } while (!exit);
  }
}
