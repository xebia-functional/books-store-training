package com.xebia.services.Bookstore;

import com.xebia.models.Book;
import com.xebia.models.User;
import com.xebia.services.Book.BookService;
import com.xebia.services.Book.BookServiceImpl;
import com.xebia.services.Register.RegisterService;
import com.xebia.services.Register.RegisterServiceImp;
import com.xebia.services.User.UserService;
import com.xebia.services.User.UserServiceImp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class BookstoreServiceImp implements BookstoreService {

  private Logger logger = Logger.getLogger(BookstoreServiceImp.class.getName());
  private BookService bookSer;
  private UserService userSer;
  private RegisterService regSer;
  private List<Book> borrowedList;

  public BookstoreServiceImp(
      BookServiceImpl bookImp, UserServiceImp userImp, RegisterServiceImp regImp) {
    this.bookSer = bookImp;
    this.userSer = userImp;
    this.regSer = regImp;
    borrowedList = new ArrayList<>();
  }

  @Override
  public boolean requestBook(Book book, User user) {
    if (bookSer.listBooks().contains(book)) {
      regSer.addRegister(user.getId(), book.getId());
      bookSer.removeBook(book);
      borrowedList.add(book);
      return true;
    }
    logger.warning("The Book doesn't exists in the bookstore");
    return false;
  }

  @Override
  public boolean returnBook(Book book, User user) {
    if (borrowedList.contains(book)) {
      borrowedList.remove(book);
      bookSer.addBook(book);
      regSer.closeRegister(user.getId(), book.getId());
      logger.info("Book returned with success");
      return true;
    }
    logger.warning("The Book doesn't exists in the bookstore");

    return false;
  }

  @Override
  public List<Book> listAvailable() {
    return bookSer.listBooks();
  }

  @Override
  public List<Book> listBorrowed() {
    return borrowedList;
  }
}
