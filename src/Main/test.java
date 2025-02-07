package Main;

import java.time.LocalDate;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
       Book b1 = new Book("The Hobbit","J.R.R. Tolkien");
        Book b2 = new Book("The Lord of The Rings","J.R.R. Tolkien");
        User u1 = new User("Flavio",1234);
        Bookstore bs1 = new Bookstore();

        bs1.getBooks().add(b1);
        bs1.getBooks().add(b1);
        bs1.getBooks().add(b1);

        bs1.getUsers().add(u1);
        bs1.getLoans().put(b1.getTitle(), u1.getName());
        bs1.getLoanDate().put(b1.getTitle(), LocalDate.now());
        bs1.getLoanDate().put(b2.getTitle(), LocalDate.of(2025,02,06));
        
        for (Book b: bs1.getBooks()){
            System.out.println(b);
        }

        System.out.println(bs1.getLoanDate());
    }
}
