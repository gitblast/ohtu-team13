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
    public void bookCanBeAddedWithObject() {
        Book book = new Book("Aleksis kivi", "Seitsemän veljestä", 1870, 427);
        assertTrue(vinkkiService.addBook(book));
        assertTrue(vinkkiService.listBooks().contains(book));
    }
    
    @Test
    public void urlCanBeAddedWithObject() {
        Url url = new Url("Varjocafe", "http://varjocafe.net/");
        assertTrue(vinkkiService.addURL(url));
        assertTrue(vinkkiService.listURLs().contains(url));
    }

}
