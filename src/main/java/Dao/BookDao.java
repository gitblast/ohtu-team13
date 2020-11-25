package Dao;

import Domain.Book;
import java.util.ArrayList;

public interface BookDao {

    boolean createBook(String kirjoittaja, String nimeke,
            Integer julkaisuvuosi, Integer sivumaara, String ISBN);

    boolean createBook(String kirjoittaja, String nimeke,
            Integer julkaisuvuosi, Integer sivumaara);

    ArrayList<Book> getAllBooks();

    Book findByISBN(String ISBN);

    ArrayList<Book> findByKirjoittaja(String kirjoittaja);

    ArrayList<Book> findByNimeke(String nimeke);
}
