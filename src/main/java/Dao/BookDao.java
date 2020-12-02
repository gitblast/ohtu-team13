package Dao;

import Domain.Book;
import java.util.ArrayList;

public interface BookDao {

    boolean createBook(Book book);

    boolean modifyBook(Book book);
    
    boolean deleteBook(int id);
    
    ArrayList<Book> getAllBooks();

    Book findByISBN(String ISBN);

    ArrayList<Book> findByKirjoittaja(String kirjoittaja);

    ArrayList<Book> findByNimeke(String nimeke);
    
    Book findWithAuthorAndTitle(String author, String title);
}
