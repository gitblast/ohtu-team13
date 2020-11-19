package Dao;

import Domain.Book;
import java.util.ArrayList;

public interface BookDao {
    void createBook();
    ArrayList<Book> getAllBooks();

}
