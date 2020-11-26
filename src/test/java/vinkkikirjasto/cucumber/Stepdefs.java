package vinkkikirjasto.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

import Dao.BookDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Domain.Book;
import Domain.Url;
import Service.VinkkiService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class Stepdefs {

    private BookDao bookDao;
    private UrlDao urlDao;
    private VinkkiService vinkkiService;
    private Url lisattava;
    private Book lisattavaKirja;
    private boolean validUrl;

    @Given("URLs Database is initialized")
    public void databaseIsInitialized() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao);
    }

    @When("URL {string} and header {string} are entered")
    public void nonEmptyUrlAndHeaderAreEntered(String url, String header) {
        lisattava = new Url(url, header);
        vinkkiService.addURL(url, header);
    }

    @When("URL {string} and empty header are entered")
    public void validUrlAndEmptyHeaderAreEntered(String url) {
        Url lisattava = new Url(url, null);
        validUrl = vinkkiService.addURL(lisattava);
    }

    @When("Header {string} and empty URL are entered")
    public void validHeaderAndEmptyUrlAreEntered(String header) {
        Url lisattava = new Url(null, header);
        validUrl = vinkkiService.addURL(lisattava);
    }

    @Then("Database contains entered URL")
    public void dataBaseContainsEnteredData() {
        Url url = vinkkiService.listURLs().get(0);
        assertEquals(url.getUrl(), lisattava.getUrl());
        assertEquals(url.getOtsikko(), lisattava.getOtsikko());
    }

    @Then("Service will return value false")
    public void serviceWillReturnValueFalse() {
        assertFalse(validUrl);
    }
    
    @Given("Books Database is initialized")
    public void bookDatabaseIsInitialized() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao);
    }
    
    @When("Author {string} and title {string} are entered")
    public void nonEmptyAuthorAndTitleAreEntered(String url, String header) {
        lisattavaKirja = new Book(url, header, 1991, 82);
        vinkkiService.addBook(lisattavaKirja);
    }
    
    @Then("Database contains entered book")
    public void databaseContainsEnteredBook() {
        Book book = vinkkiService.listBooks().get(0);
        assertEquals(book.getKirjoittaja(), lisattavaKirja.getKirjoittaja());
        assertEquals(book.getNimeke(), lisattavaKirja.getNimeke());
    }
}
