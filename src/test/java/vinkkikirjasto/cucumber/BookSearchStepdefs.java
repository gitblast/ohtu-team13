package vinkkikirjasto.cucumber;

import org.testfx.framework.junit.ApplicationTest;

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

public class BookSearchStepdefs extends ApplicationTest {

    private BookDao bookDao;
    private UrlDao urlDao;
    private MovieDao movieDao;
    private VinkkiService vinkkiService;
    private Book lisattavaKirja;

    void initializeDatabase() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        movieDao = new SqlDbMovieDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao, movieDao);
    }

    @Given("Books database is initialized and author {string}, title {string}, "
        + "pages {int} and published {int} are entered")
    public void bookDatabaseIsInitializedAndBookIsEntered(String author,
        String title, int pages, int published) {
        try {
            initializeDatabase();
        } catch (Exception e) {
            // TODO
        }
        lisattavaKirja = new Book(author, title, pages, published);
        vinkkiService.addBook(lisattavaKirja);
    }
    
    @When("User selects Author and enters {string}")
    public void listBooksIsSelectedAndAuthorIsEntered(String author) {

        // clickOn("#listaa_kirjat_btn");
        throw new io.cucumber.java.PendingException();
    }

    @Then("Page shows author {string} book")
    public void pageShowsBook(String string) {

        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}