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
    
    public void addBook(String kirjoittaja, String nimeke, Integer julkaisuvuosi, Integer sivumaara) {
        this.bookDao.createBook(kirjoittaja, nimeke, julkaisuvuosi, sivumaara);
    }
    
    public void addURL(String otsikko, String url) {
        this.urlDao.createURL(otsikko, url); 
    }
    
    public ArrayList<Book> listBooks() {
        return this.bookDao.getAllBooks();
    }
        
    public ArrayList<Url> listURLs() {
        return this.urlDao.getAllURLs();
    }
}
