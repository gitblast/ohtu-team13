package Dao;

import Domain.Url;
import java.util.ArrayList;

public interface UrlDao {
    boolean createURL(Url url);
    ArrayList<Url> getAllURLs();
    ArrayList<Url> findByOtsikko(String otsikko);
}
