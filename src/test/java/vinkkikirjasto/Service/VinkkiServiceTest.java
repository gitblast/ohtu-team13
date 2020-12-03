package vinkkikirjasto.Service;

import Dao.BookDao;
import Dao.MovieDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbMovieDao;
import Database.SqlDbUrlDao;
import Domain.Book;
import Domain.Url;
import Domain.Movie;
import Service.VinkkiService;
import static org.junit.Assert.assertEquals;
// import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class VinkkiServiceTest {

    private BookDao bookDao;
    private UrlDao urlDao;
    private MovieDao movieDao;
    private VinkkiService vinkkiService;

    @Before
    public void setUp() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        movieDao = new SqlDbMovieDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao, movieDao);
        assertTrue(vinkkiService.listBooks().isEmpty());
        assertTrue(vinkkiService.listURLs().isEmpty());
        assertTrue(vinkkiService.listMovies().isEmpty());
        vinkkiService.addBook(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2009, 200, "978-0132350884"));
        vinkkiService.addURL(new Url("Miniprojektin speksi", "https://ohjelmistotuotanto-hy.github.io/miniprojekti/"));
        vinkkiService.addMovie(new Movie("Moomins on the Riviera", "Xavier Picard", 2014, 70));
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
    
    @Test
    public void movieCanBeAddedWithObject() {
        Movie movie = new Movie("The Godfather", "Francis Ford Coppola", 1972, 175);
        assertTrue(vinkkiService.addMovie(movie));
        assertTrue(vinkkiService.listMovies().contains(movie));
    }
    
    @Test
    public void movieCanBeEdited() {
        Movie movie = vinkkiService.listMovies().get(0);
        assertTrue(movie.equals(new Movie("Moomins on the Riviera", "Xavier Picard", 2014, 70)));
        movie.setTitle("Full Metal Jacket");
        movie.setDirector("Stanley Kubrick");
        movie.setReleaseYear(1987);
        movie.setLength(116);
        assertTrue(vinkkiService.modifyMovie(movie));
        assertEquals(new Movie("Full Metal Jacket", "Stanley Kubrick", 1987, 116), vinkkiService.listMovies().get(0));
    }
    
    @Test
    public void movieCanBeDeleted() {
        assertTrue(vinkkiService.listMovies().size()==1);
        assertTrue(vinkkiService.deleteMovie(vinkkiService.listMovies().get(0).getId()));
        assertTrue(vinkkiService.listMovies().isEmpty());
    }

}
