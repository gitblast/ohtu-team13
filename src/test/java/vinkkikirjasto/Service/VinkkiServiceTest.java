package vinkkikirjasto.Service;

import Dao.BookDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Service.VinkkiService;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class VinkkiServiceTest {
    private BookDao bookDao;
    private UrlDao urlDao;
    private VinkkiService vinkkiService;
    
    @Before
    public void setUp() throws Exception {
        String db = "jdbc:sqlite:memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao);
    }

    @Test
    public void dbIsEmptyAtStart() {
        assertTrue(vinkkiService.listBooks().isEmpty());
        assertTrue(vinkkiService.listBooks().isEmpty());
    }
}
