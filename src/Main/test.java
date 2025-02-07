package Main;

import java.time.LocalDate;
import java.util.Scanner;

public class test {
    public static void main(String[] args) {
        Book b1 = new Book("El Hobbit","J.R.R. Tolkien");
        User u1 = new User("Flavio",1234);
        Bookstore bs1 = new Bookstore();

        bs1.getBooks().add(b1);
        bs1.getUsers().add(u1);
        bs1.getLoans().put(b1.getTitle(), u1.getName());
        bs1.getLoanDate().put(b1.getTitle(), LocalDate.now());

        System.out.println("Esta registrado el libro: "+b1.toString()+" a nombre de: "+u1.toString());
    }
}
