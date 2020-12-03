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

    @Before
    public void setUp() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        movieDao = new SqlDbMovieDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao, movieDao);
        vinkkiService.addBook(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2009, 200, "978-0132350884"));
        vinkkiService.addBook(new Book("J. K. Rowling", "Harry Potter and the Sorcerer's Stone", 1999, 309, "978-0590353427"));
        vinkkiService.addBook(new Book("J. K. Rowling", "Harry Potter ja Azkabanin vanki", 2020, 456, "978-9520426415"));
        vinkkiService.addURL(new Url("Miniprojektin speksi", "https://ohjelmistotuotanto-hy.github.io/miniprojekti/"));
        vinkkiService.addURL(new Url("Nice music video ;)", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"));
        vinkkiService.addMovie(new Movie("Moomins on the Riviera", "Xavier Picard", 2014, 70));
        vinkkiService.addMovie(new Movie("Funny Games", "Michael Haneke", 1997, 108));
    }

    @Test
    public void listBooksWorks() {
        ArrayList<Book> books = vinkkiService.listBooks();
        assertEquals(3, books.size());
        assertTrue(books.contains(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2009, 200, "978-0132350884")));
        assertTrue(books.contains(new Book("J. K. Rowling", "Harry Potter and the Sorcerer's Stone", 1999, 309, "978-0590353427")));
        assertTrue(books.contains(new Book("J. K. Rowling", "Harry Potter ja Azkabanin vanki", 2020, 456, "978-9520426415")));
    }
    
    @Test
    public void listUrlsWorks() {
        ArrayList<Url> urls = vinkkiService.listURLs();
        assertEquals(2, urls.size());
        assertTrue(urls.contains(new Url("Miniprojektin speksi", "https://ohjelmistotuotanto-hy.github.io/miniprojekti/")));
        assertTrue(urls.contains(new Url("Nice music video ;)", "https://www.youtube.com/watch?v=dQw4w9WgXcQ")));
    }    
    
    @Test
    public void listMoviesWorks() {
        ArrayList<Movie> movies = vinkkiService.listMovies();
        assertEquals(2, movies.size());
        assertTrue(movies.contains(new Movie("Moomins on the Riviera", "Xavier Picard", 2014, 70)));
        assertTrue(movies.contains(new Movie("Funny Games", "Michael Haneke", 1997, 108)));
    }    
    
    @Test
    public void listAllBookmarksWorks() {
        ArrayList<Bookmark> bookmarks = vinkkiService.listAllBookmarks();
        assertEquals(7, bookmarks.size());
        assertTrue(bookmarks.contains(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2009, 200, "978-0132350884")));
        assertTrue(bookmarks.contains(new Book("J. K. Rowling", "Harry Potter and the Sorcerer's Stone", 1999, 309, "978-0590353427")));
        assertTrue(bookmarks.contains(new Book("J. K. Rowling", "Harry Potter ja Azkabanin vanki", 2020, 456, "978-9520426415")));
        assertTrue(bookmarks.contains(new Url("Miniprojektin speksi", "https://ohjelmistotuotanto-hy.github.io/miniprojekti/")));
        assertTrue(bookmarks.contains(new Url("Nice music video ;)", "https://www.youtube.com/watch?v=dQw4w9WgXcQ")));
        assertTrue(bookmarks.contains(new Movie("Moomins on the Riviera", "Xavier Picard", 2014, 70)));
        assertTrue(bookmarks.contains(new Movie("Funny Games", "Michael Haneke", 1997, 108)));       
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
        assertTrue(vinkkiService.modifyBook(book));
        assertEquals(new Book("Alden Bell", "The Reapers are the Angels", 2010, 225, "978-0805092431"), vinkkiService.listBooks().get(0));
    }
    
    @Test
    public void bookCanBeDeleted() {
        assertTrue(vinkkiService.listBooks().size()==3);
        Book book = vinkkiService.listBooks().get(0);
        assertTrue(vinkkiService.deleteBook(book.getId()));
        assertTrue(vinkkiService.listBooks().size()==2);
        assertFalse(vinkkiService.listBooks().contains(book));
    }    
    
    @Test
    public void searchBookByISBNWorks() {
        assertEquals(new Book("J. K. Rowling", "Harry Potter and the Sorcerer's Stone", 1999, 309, "978-0590353427"), vinkkiService.searchBookByISBN("978-0590353427"));
    }
    
    @Test
    public void searchBookByAuthorWorks() {
        ArrayList<Book> searchResults = vinkkiService.searchBookByAuthor("J. K. Rowling");
        assertTrue(searchResults.size()==2);
        assertTrue(searchResults.contains(new Book("J. K. Rowling", "Harry Potter and the Sorcerer's Stone", 1999, 309, "978-0590353427")));
        assertTrue(searchResults.contains(new Book("J. K. Rowling", "Harry Potter ja Azkabanin vanki", 2020, 456, "978-9520426415")));
        
    }
    
    @Test
    public void searchBookByNameWorks() {
        ArrayList<Book> searchResults = vinkkiService.searchBookByName("Clean Code: A Handbook of Agile Software Craftsmanship");
        assertTrue(searchResults.size()==1);
        assertTrue(searchResults.contains(new Book("Robert Martin", "Clean Code: A Handbook of Agile Software Craftsmanship", 2009, 200, "978-0132350884")));
    }
    
    @Test
    public void findBookByAuthorAndNameWorks() {
        assertEquals(new Book("J. K. Rowling", "Harry Potter ja Azkabanin vanki", 2020, 456, "978-9520426415"), vinkkiService.findBookByAuthorAndTitle("J. K. Rowling", "Harry Potter ja Azkabanin vanki"));
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
        assertTrue(vinkkiService.modifyUrl(url));
        assertEquals(new Url("TKO-äly ry", "https://www.tko-aly.fi/"), vinkkiService.listURLs().get(0));
    }
    
    @Test
    public void urlCanBeDeleted() {
        assertTrue(vinkkiService.listURLs().size()==2);
        Url url = vinkkiService.listURLs().get(0);
        assertTrue(vinkkiService.deleteUrl(url.getId()));
        assertTrue(vinkkiService.listURLs().size()==1);
        assertFalse(vinkkiService.listURLs().contains(url));
    }
    
    @Test
    public void searchUrlByNameWorks() {
        ArrayList<Url> searchResult = vinkkiService.searchUrlByName("Nice music video ;)");
        assertTrue(searchResult.size()==1);
        assertEquals(new Url("Nice music video ;)", "https://www.youtube.com/watch?v=dQw4w9WgXcQ"), searchResult.get(0));
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
        assertTrue(vinkkiService.listMovies().size()==2);
        Movie movie = vinkkiService.listMovies().get(0);
        assertTrue(vinkkiService.deleteMovie(movie.getId()));
        assertTrue(vinkkiService.listMovies().size()==1);
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
