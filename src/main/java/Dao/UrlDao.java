package Dao;

import Domain.Url;
import java.util.ArrayList;

public interface UrlDao {
    boolean createURL(Url url);
    boolean modifyURL(Url url);
    boolean deleteURL(int id);
    ArrayList<Url> getAllURLs();
    boolean deleteAllURLs();
    ArrayList<Url> findByOtsikko(String otsikko);
}
