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
    
    public void addBook(Book book) {
        this.bookDao.createBook(book.getKirjoittaja(), book.getNimeke(),
                        book.getJulkaisuvuosi(), book.getSivumaara(), book.getISBN());
    }
    
    public void addBook(String kirjoittaja, String nimeke,
                    Integer julkaisuvuosi, Integer sivumaara) {
        this.addBook(kirjoittaja, nimeke, julkaisuvuosi, sivumaara, null);
    }
    
    public void addBook(String kirjoittaja, String nimeke,
                    Integer julkaisuvuosi, Integer sivumaara, String ISBN) {
        this.bookDao.createBook(kirjoittaja, nimeke, julkaisuvuosi, sivumaara, ISBN);
    }
    
    public void addURL(Url url) {
        this.urlDao.createURL(url.getOtsikko(), url.getUrl());
    }
    
    public void addURL(String otsikko, String url) {
        this.urlDao.createURL(otsikko, url); 
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
        return  this.bookDao.findByNimeke(name);
    }
        
    public ArrayList<Url> listURLs() {
        return this.urlDao.getAllURLs();
    }
    
    public ArrayList<Url> searchUrlByName(String name) {
        return this.urlDao.findByOtsikko(name);
    }
}
