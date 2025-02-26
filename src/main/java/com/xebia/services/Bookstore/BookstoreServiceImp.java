package com.xebia.services.Bookstore;

import com.xebia.models.Book;
import com.xebia.models.Register;
import com.xebia.models.User;
import com.xebia.services.Book.BookService;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.User.UserService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;

public class BookstoreServiceImp implements BookstoreService {

  private Logger logger = Logger.getLogger(BookstoreServiceImp.class.getName());
  private BookService bookSer;
  private UserService userSer;
  private RegisterService regSer;

  public BookstoreServiceImp(BookService bookImp, UserService userImp, RegisterService regImp) {
    this.bookSer = bookImp;
    this.userSer = userImp;
    this.regSer = regImp;
  }

  @Override
  public boolean requestBook(Book book, User user) {
    Optional<User> searchedUser = userSer.searchUserByID(user.getId());
    if (searchedUser.isEmpty()) {
      logger.warning("The user doesn't exists in the bookstore");
      return false;
    } else {
      Optional<Book> searchedBook = bookSer.searchBookById(book.getId());
      if (searchedBook.isEmpty()) {
        logger.warning("The Book doesn't exists in the bookstore");
        return false;
      } else {
        if (searchedBook.get().isAvailable()) {
          Register register = new Register(user.getId(), book.getId());
          if (regSer.addRegister(user.getId(), book.getId())) {
            bookSer.updateAvailability(book.getId(), false);
            return true;
          } else {
            logger.warning("The user already borrowed the book");
            return false;
          }
        } else {
          logger.warning("The Book is not available in the bookstore");
          return false;
        }
      }
    }
  }

  @Override
  public boolean returnBook(Book book, User user) {
    Optional<User> searchedUser = userSer.searchUserByID(user.getId());
    if (searchedUser.isEmpty()) {
      logger.warning("The user doesn't exists in the bookstore");
      return false;
    } else {
      Optional<Book> searchedBook = bookSer.searchBookById(book.getId());
      if (searchedBook.isEmpty()) {
        logger.warning("The Book doesn't exists in the bookstore");
        return false;
      } else {
        List<Register> registers = regSer.searchRegisterByBook(book.getId());

        for (Register r : registers) {
          if (r.getUserId().equals(user.getId()) && r.isActive()) {
            regSer.closeRegister(user.getId(), book.getId());
            bookSer.updateAvailability(book.getId(), true);
            return true;
          }
        }
      }
    }
    logger.warning("The register doesn't exists");
    return false;
  }

  @Override
  public List<Book> listAvailable() {
    return bookSer.listBooks();
  }

  @Override
  public List<Book> listBorrowed() {
    List<Book> borrowed = new ArrayList<>();
    for (Register r : regSer.listRegistered()) {
      if (r.isActive()) {
        Book b = bookSer.searchBookById(r.getBookId()).get();
        borrowed.add(b);
      }
    }
    return borrowed;
  }
}
