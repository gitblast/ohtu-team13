package Database;

import Domain.Url;
import Dao.UrlDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlDbUrlDao implements UrlDao {

    private DbConnection db;
    private Connection connection;
    private ArrayList<Url> urlList;

    public SqlDbUrlDao(String dbFile) throws Exception {
        this.db = new DbConnection(dbFile);
        this.connection = db.getConnection();
    }

    public SqlDbUrlDao() throws Exception {
        this("jdbc:sqlite:lukuvinkit.db");
    }

    @Override
    public boolean createURL(Url url) {
        if (url==null || url.getOtsikko() == null || url.getUrl() == null) {
            return false;
        }

        String query = "INSERT INTO Url (otsikko, url) VALUES (?, ?);";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, url.getOtsikko());
            prepared.setString(2, url.getUrl());
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }

    public ArrayList<Url> getAllURLs() {
        urlList = new ArrayList<Url>();
        String query = "SELECT otsikko, url FROM Url;";

        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String otsikko = rs.getString(("otsikko"));
                String osoite = rs.getString("url");
                Url lisattava = new Url(otsikko, osoite);
                urlList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return urlList;
    }

    public ArrayList<Url> findByOtsikko(String searchTerm) {
        urlList = new ArrayList<Url>();
        String query = "SELECT otsikko, url FROM Url WHERE otsikko=?;";

        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, searchTerm);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String otsikko = rs.getString(("otsikko"));
                String osoite = rs.getString("url");
                Url lisattava = new Url(otsikko, osoite);
                urlList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return urlList;
    }

}
