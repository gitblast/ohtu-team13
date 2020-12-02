package vinkkikirjasto.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import Dao.BookDao;
import Dao.MovieDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbMovieDao;
import Database.SqlDbUrlDao;
import Domain.Book;
import Service.VinkkiService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BookStepdefs {

    private BookDao bookDao;
    private UrlDao urlDao;
    private MovieDao movieDao;
    private VinkkiService vinkkiService;
    private Book lisattavaKirja;
    private boolean validBook;

    @Given("Books database is initialized")
    public void bookDatabaseIsInitialized() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        movieDao = new SqlDbMovieDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao, movieDao);
    }

    @When("Author {string}, title {string}, "
        + "pages {int} and published {int} are entered")
    public void nonEmptyAuthorAndTitleAreEntered(String url,
        String header, int pages, int published) {
        lisattavaKirja = new Book(url, header, pages, published);
        vinkkiService.addBook(lisattavaKirja);
    }

    @When("empty author and empty title with "
        + "{int} pages and published {int} are entered")
    public void emptyAuthorandEmptyTitle(int pages, int published) {
        lisattavaKirja = new Book(null, null, pages, published);
        validBook = vinkkiService.addBook(lisattavaKirja);
    }

    @When("empty author and {string} with "
        + "{int} pages and published {int} are entered")
    public void emptyAuthorAndvalidTitle(String title,
        int pages, int published) {
        lisattavaKirja = new Book(null, title, pages, published);
        validBook = vinkkiService.addBook(lisattavaKirja);
    }

    @When("{string} and empty title with "
        + "{int} pages and published {int} are entered")
    public void validAuthorAndEmptyTitle(String author,
        int pages, int published) {
        lisattavaKirja = new Book(author, null, pages, published);
        validBook = vinkkiService.addBook(lisattavaKirja);
    }

    @Then("Database contains entered book")
    public void databaseContainsEnteredBook() {
        Book book = vinkkiService.listBooks().get(0);
        assertEquals(book.getKirjoittaja(), lisattavaKirja.getKirjoittaja());
        assertEquals(book.getTitle(), lisattavaKirja.getTitle());
        assertEquals(book.getSivumaara(), lisattavaKirja.getSivumaara());
        assertEquals(book.getJulkaisuvuosi(),
            lisattavaKirja.getJulkaisuvuosi());
    }

    @Then("Service will return false value")
    public void serviceWillReturnFalseValue() {
        assertFalse(validBook);
    }
}
