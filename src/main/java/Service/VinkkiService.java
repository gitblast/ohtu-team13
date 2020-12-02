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

    public boolean addBook(Book book) {
        return this.bookDao.createBook(book);
    }

    public boolean addURL(Url url) {
        boolean palautus;
        palautus = this.urlDao.createURL(url);
        System.out.println(palautus);
        return palautus;
    }

    public ArrayList<Book> listBooks() {
        return this.bookDao.getAllBooks();
    }

    public Book searchBookByISBN(String ISBN) {
        return this.bookDao.findByISBN(ISBN);
    }

    public ArrayList<Book> searchBookByAuthor(String author) {
        return this.bookDao.findByKirjoittaja(author);
    }

    public ArrayList<Book> searchBookByName(String name) {
        return this.bookDao.findByNimeke(name);
    }

    public ArrayList<Url> listURLs() {
        return this.urlDao.getAllURLs();
    }

    public ArrayList<Url> searchUrlByName(String name) {
        return this.urlDao.findByOtsikko(name);
    }
    
    public Book findBookByAuthorAndTitle(String author, String title) {
        return this.bookDao.findWithAuthorAndTitle(author, title);
    }
}
