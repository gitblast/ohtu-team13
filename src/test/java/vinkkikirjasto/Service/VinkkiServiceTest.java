package vinkkikirjasto.Service;

import Dao.BookDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Domain.Book;
import Domain.Url;
import Service.VinkkiService;
// import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

public class VinkkiServiceTest {
    private BookDao bookDao;
    private UrlDao urlDao;
    private VinkkiService vinkkiService;
    
    @Before
    public void setUp() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao);
    }

    @Test
    public void dbIsEmptyAtStart() {
        assertTrue(vinkkiService.listBooks().isEmpty());
        assertTrue(vinkkiService.listBooks().isEmpty());
    }
    
    @Test
    public void bookCanBeAddedWithParams() {
        vinkkiService.addBook("Robert Martin",
            "Clean Code: A Handbook of Agile Software Craftsmanship",
            1970, 200);
        Book book = vinkkiService.listBooks().get(0);
        assertEquals("Robert Martin", book.getKirjoittaja());
        assertEquals("Clean Code: A Handbook of Agile Software Craftsmanship",
            book.getNimeke());
        assertTrue(book.getJulkaisuvuosi() == 1970);
        assertTrue(book.getSivumaara() == 200);
    }
    
    @Test
    public void urlCanBeAddedWithParams() {
        vinkkiService.addURL("Miniprojektin speksi",
            "https://ohjelmistotuotanto-hy.github.io/miniprojekti/");
        Url url = vinkkiService.listURLs().get(0);
        assertEquals("Miniprojektin speksi", url.getOtsikko());
        assertEquals("https://ohjelmistotuotanto-hy.github.io/miniprojekti/",
            url.getUrl());
    }
    
    @Test
    public void bookCanBeAddedWithObject() {
        Book book = new Book("Aleksis kivi", "Seitsemän veljestä", 1870, 427);
        vinkkiService.addBook(book);
        assertTrue(vinkkiService.listBooks().contains(book));
    }
    
    @Test
    public void urlCanBeAddedWithObject() {
        Url url = new Url("Varjocafe", "http://varjocafe.net/");
        vinkkiService.addURL(url);
        assertTrue(vinkkiService.listURLs().contains(url));
    }

}
