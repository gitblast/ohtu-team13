package Service;

import Domain.Book;
import Dao.BookDao;
import Domain.Url;
import Dao.UrlDao;
import java.util.ArrayList;

public class VinkkiService {
    private BookDao bookDao;
    private UrlDao urlDao;

    public VinkkiService(BookDao bookDao, UrlDao urlDao) {
        this.bookDao = bookDao;
        this.urlDao = urlDao;
    }
    
    public void addBook() {
        this.bookDao.createBook();
    }
    
    public void addURL() {
        this.urlDao.createURL(); 
    }
    
    public ArrayList<Book> listBooks() {
        return this.bookDao.getAllBooks();
    }
        
    public ArrayList<Url> listURLs() {
        return this.urlDao.getAllURLs();
    }
}
