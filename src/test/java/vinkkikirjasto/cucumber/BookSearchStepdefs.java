package vinkkikirjasto.cucumber;

import vinkkikirjasto.Main;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.TextField;
import org.junit.Test;
import static org.testfx.api.FxAssert.verifyThat;
import org.testfx.framework.junit.ApplicationTest;
import static org.testfx.matcher.base.NodeMatchers.hasChild;
import static org.testfx.matcher.control.LabeledMatchers.hasText;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;

import Scenes.*;
import Dao.BookDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Domain.Book;
import Service.VinkkiService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class BookSearchStepdefs extends ApplicationTest {

    private BookDao bookDao;
    private UrlDao urlDao;
    private VinkkiService vinkkiService;
    private Book lisattavaKirja;
    private boolean validBook;

    void initializeDatabase() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao);
    }

    @Given("Books database is initialized and author {string}, title {string}, "
        + "pages {int} and published {int} are entered")
    public void bookDatabaseIsInitializedAndBookIsEntered(String author,
        String title, int pages, int published) {
        try {
        initializeDatabase();
        } catch (Exception e) {
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