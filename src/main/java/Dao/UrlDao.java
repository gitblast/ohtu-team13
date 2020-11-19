package Dao;

import Domain.Url;
import java.util.ArrayList;

public interface UrlDao {
    public void createURL();
    ArrayList<Url> getAllURLs();
}
