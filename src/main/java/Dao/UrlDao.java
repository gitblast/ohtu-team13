package Dao;

import Domain.Url;
import java.util.ArrayList;

public interface UrlDao {
    public void createURL(String otsikko, String url);
    ArrayList<Url> getAllURLs();
}
