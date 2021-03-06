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
import Domain.Url;
import Service.VinkkiService;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class UrlStepdefs {

    private BookDao bookDao;
    private UrlDao urlDao;
    private MovieDao movieDao;
    private VinkkiService vinkkiService;
    private Url lisattava;
    private boolean validUrl;

    @Given("URLs Database is initialized")
    public void databaseIsInitialized() throws Exception {
        String db = "jdbc:sqlite::memory:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        movieDao = new SqlDbMovieDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao, movieDao);
    }

    @When("URL {string} and header {string} are entered")
    public void nonEmptyUrlAndHeaderAreEntered(String url, String header) {
        lisattava = new Url(url, header);
        vinkkiService.addURL(lisattava);
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
        assertEquals(url.getTitle(), lisattava.getTitle());
    }

    @Then("Service will return value false")
    public void serviceWillReturnValueFalse() {
        assertFalse(validUrl);
    }
}
