package vinkkikirjasto.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.cucumber.java.en.Then;
import static org.junit.Assert.assertEquals;

import Dao.BookDao;
import Dao.UrlDao;
import Database.SqlDbBookDao;
import Database.SqlDbUrlDao;
import Domain.Url;
import Service.VinkkiService;

public class Stepdefs {
    private BookDao bookDao; 
    private UrlDao urlDao;
    private VinkkiService vinkkiService;
    Url lisattava;
    
    @Given("Database is initialized")
    public void databaseIsInitialized() throws Exception {
        String db = "jdbc:sqlite::test:";
        bookDao = new SqlDbBookDao(db);
        urlDao = new SqlDbUrlDao(db);
        vinkkiService = new VinkkiService(bookDao, urlDao);
    }
    
    @When("URL {string} and header {string} are entered")
    public void nonEmptyUrlAndHeaderAreEntered(String url, String header) {
        lisattava = new Url(url, header);
        vinkkiService.addURL(url, header);
    }
    
    @Then("Database contains entered data")
    public void dataBaseContainsEnteredData() {
        Url url = vinkkiService.listURLs().get(0);
        assertEquals(url.getUrl(), lisattava.getUrl());
        assertEquals(url.getOtsikko(), lisattava.getOtsikko());
    }
}
