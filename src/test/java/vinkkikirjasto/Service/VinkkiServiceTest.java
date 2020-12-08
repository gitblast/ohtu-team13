package vinkkikirjasto.Service;

import Dao.BookDao;
import Dao.MovieDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbMovieDao;
import Database.SqlDbUrlDao;
import Domain.Book;
import Domain.Bookmark;
import Domain.Url;
import Domain.Movie;
import Service.VinkkiService;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class VinkkiServiceTest {

    private BookDao bookDao;
    private UrlDao urlDao;
    private MovieDao movieDao;
    private VinkkiService vinkkiService;

    private Book[] bookList = {
        new Book(
            "Robert Martin",
            "Clean Code: A Handbook of Agile Software Craftsmanship",
            2009, 200, "978-0132350884"
        ),
        new Book(
            "J. K. Rowling", "Harry Potter and the Sorcerer's Stone",
            1999, 309, "978-0590353427"
        ),
        new Book(
            "J. K. Rowling", "Harry Potter ja Azkabanin vanki",
            2020, 456, "978-9520426415"
        )
    };
    private Url[] urlList = {
        new Url(
            "Miniprojektin speksi",
            "https://ohjelmistotuotanto-hy.github.io/miniprojekti/"
        ),
        new Url(
            "Nice music video ;)",
            "https://www.youtube.com/watch?v=dQw4w9WgXcQ"
        )
    };
    private Movie[] movieList = {
        new Movie("Moomins on the Riviera", "Xavier Picard", 2014, 70),
        new Movie("Funny Games", "Michael Haneke", 1997, 108)
    };

    @Before
    public void setUp() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        movieDao = new SqlDbMovieDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao, movieDao);

        for (Book book : this.bookList) {
            vinkkiService.addBook(book);
        }
        for (Url url : this.urlList) {
            vinkkiService.addURL(url);
        }
        for (Movie movie : this.movieList) {
            vinkkiService.addMovie(movie);
        }
    }

    @Test
    public void listBooksWorks() {
        ArrayList<Book> books = vinkkiService.listBooks();
        assertEquals(3, books.size());

        for (Book book : this.bookList) {
            assertTrue(books.contains(book));
        }
    }
    
    @Test
    public void listUrlsWorks() {
        ArrayList<Url> urls = vinkkiService.listURLs();
        assertEquals(2, urls.size());

        for (Url url : this.urlList) {
            assertTrue(urls.contains(url));
        }
    }    
    
    @Test
    public void listMoviesWorks() {
        ArrayList<Movie> movies = vinkkiService.listMovies();
        assertEquals(2, movies.size());

        for (Movie movie : this.movieList) {
            assertTrue(movies.contains(movie));
        }
    }    
    
    @Test
    public void listAllBookmarksWorks() {
        ArrayList<Bookmark> bookmarks = vinkkiService.listAllBookmarks();
        assertEquals(7, bookmarks.size());

        for (Book book : this.bookList) {
            assertTrue(bookmarks.contains(book));
        }

        for (Url url : this.urlList) {
            assertTrue(bookmarks.contains(url));
        }

        for (Movie movie : this.movieList) {
            assertTrue(bookmarks.contains(movie));
        }  
    }
    
    @Test
    public void bookCanBeAddedWithObject() {
        Book book = new Book("Aleksis kivi", "Seitsemän veljestä", 1870, 427);
        assertTrue(vinkkiService.addBook(book));
        assertTrue(vinkkiService.listBooks().contains(book));
    }

    @Test
    public void bookCanBeEdited() {
        Book book = vinkkiService.listBooks().get(0);
        book.setTitle("The Reapers are the Angels");
        book.setKirjoittaja("Alden Bell");
        book.setJulkaisuvuosi(2010);
        book.setSivumaara(225);
        book.setISBN("978-0805092431");
        
        Book modified = new Book(
            "Alden Bell", "The Reapers are the Angels",
            2010, 225, "978-0805092431"
        );

        assertTrue(vinkkiService.modifyBook(book));
        assertEquals(modified, vinkkiService.listBooks().get(0));
    }
    
    @Test
    public void bookCanBeDeleted() {
        assertTrue(vinkkiService.listBooks().size() == 3);
        Book book = vinkkiService.listBooks().get(0);
        assertTrue(vinkkiService.deleteBook(book.getId()));
        assertTrue(vinkkiService.listBooks().size() == 2);
        assertFalse(vinkkiService.listBooks().contains(book));
    }    
    
    @Test
    public void searchBookByISBNWorks() {
        Book searchResult = vinkkiService.searchBookByISBN("978-0590353427");

        assertEquals(this.bookList[1], searchResult);
    }
    
    @Test
    public void searchBookByAuthorWorks() {
        ArrayList<Book> searchResults;
        searchResults = vinkkiService.searchBookByAuthor("J. K. Rowling");

        assertTrue(searchResults.size() == 2);
        for (int i = 1; i < 3; i++) {
            assertTrue(searchResults.contains(this.bookList[i]));
        }   
    }
    
    @Test
    public void searchBookByNameWorks() {
        ArrayList<Book> searchResults;
        searchResults = vinkkiService.searchBookByName(
            "Clean Code: A Handbook of Agile Software Craftsmanship"
        );
        
        assertTrue(searchResults.size() == 1);
        assertTrue(searchResults.contains(this.bookList[0]));
    }
    
    @Test
    public void findBookByAuthorAndNameWorks() {
        Book searchResult = vinkkiService.findBookByAuthorAndTitle(
            "J. K. Rowling", "Harry Potter ja Azkabanin vanki"
        );

        assertEquals(this.bookList[2], searchResult);
    }
    
    @Test
    public void urlCanBeAddedWithObject() {
        Url url = new Url("Varjocafe", "http://varjocafe.net/");
        assertTrue(vinkkiService.addURL(url));
        assertTrue(vinkkiService.listURLs().contains(url));
    }
    
    @Test
    public void urlCanBeEdited() {
        Url url = vinkkiService.listURLs().get(0);
        url.setUrl("https://www.tko-aly.fi/");
        url.setOtsikko("TKO-äly ry");

        Url modified = new Url("TKO-äly ry", "https://www.tko-aly.fi/");
        assertTrue(vinkkiService.modifyUrl(url));
        assertEquals(modified, vinkkiService.listURLs().get(0));
    }
    
    @Test
    public void urlCanBeDeleted() {
        assertTrue(vinkkiService.listURLs().size() == 2);
        Url url = vinkkiService.listURLs().get(0);
        assertTrue(vinkkiService.deleteUrl(url.getId()));
        assertTrue(vinkkiService.listURLs().size() == 1);
        assertFalse(vinkkiService.listURLs().contains(url));
    }
    
    @Test
    public void searchUrlByNameWorks() {
        ArrayList<Url> searchResults;
        searchResults = vinkkiService.searchUrlByName("Nice music video ;)");

        assertTrue(searchResults.size() == 1);
        assertEquals(this.urlList[1], searchResults.get(0));
    }
    
    @Test
    public void movieCanBeAddedWithObject() {
        Movie movie = new Movie(
            "The Godfather", "Francis Ford Coppola", 1972, 175
        );

        assertTrue(vinkkiService.addMovie(movie));
        assertTrue(vinkkiService.listMovies().contains(movie));
    }
    
    @Test
    public void movieCanBeEdited() {
        Movie movie = vinkkiService.listMovies().get(0);

        assertTrue(movie.equals(this.movieList[0]));

        movie.setTitle("Full Metal Jacket");
        movie.setDirector("Stanley Kubrick");
        movie.setReleaseYear(1987);
        movie.setLength(116);

        Movie modified = new Movie(
            "Full Metal Jacket", "Stanley Kubrick", 1987, 116
        );

        assertTrue(vinkkiService.modifyMovie(movie));
        assertEquals(modified, vinkkiService.listMovies().get(0));
    }
    
    @Test
    public void movieCanBeDeleted() {
        assertTrue(vinkkiService.listMovies().size() == 2);
        Movie movie = vinkkiService.listMovies().get(0);
        assertTrue(vinkkiService.deleteMovie(movie.getId()));
        assertTrue(vinkkiService.listMovies().size() == 1);
        assertFalse(vinkkiService.listMovies().contains(movie));
    }
    
    @Test
    public void allBooksCanBeDeleted() {
        assertTrue(vinkkiService.deleteAllBooks());
        assertTrue(vinkkiService.listBooks().isEmpty());
    }
    
    @Test
    public void allUrlsCanBeDeleted() {
        assertTrue(vinkkiService.deleteAllURLs());
        assertTrue(vinkkiService.listURLs().isEmpty());
    }
    
    @Test
    public void allMoviesCanBeDeleted() {
        assertTrue(vinkkiService.deleteAllMovies());
        assertTrue(vinkkiService.listMovies().isEmpty());
    }
    
    @Test
    public void allBookmarksCanBeDeleted() {
        assertTrue(vinkkiService.deleteAllBookMarks());
        assertTrue(vinkkiService.listAllBookmarks().isEmpty());
    }

}
