package Dao;

import Domain.Book;
import java.util.ArrayList;

public interface BookDao {
    void createBook(String kirjoittaja, String nimeke,
                    Integer julkaisuvuosi, Integer sivumaara);
    ArrayList<Book> getAllBooks();

}
