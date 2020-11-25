package Database;

import Domain.Url;
import Dao.UrlDao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        this.db = new DbConnection("jdbc:sqlite:lukuvinkit.db");
        this.connection = db.getConnection();        
    }

    @Override
    public boolean createURL(String otsikko, String url) {
        if (otsikko == null || url == null) {
            return false;
        }
        
        String query = "INSERT INTO Url (otsikko, url) values (?, ?);";
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, otsikko);
            prepared.setString(2, url);
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
        return true;
    }
    
    @Override
    public ArrayList<Url> getAllURLs() {
        urlList = new ArrayList<Url>();
        String query = "SELECT otsikko, url from Url;";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String otsikko = rs.getString(("otsikko"));
                String url = rs.getString("url");
                Url lisattava = new Url(otsikko, url);
                urlList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return urlList;
    }

    @Override
    public ArrayList<Url> findByOtsikko(String searchTerm) {
        urlList = new ArrayList<Url>();
        String query = "SELECT otsikko, url FROM Url WHERE otsikko=?;";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, searchTerm);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String otsikko = rs.getString(("otsikko"));
                String url = rs.getString("url");
                Url lisattava = new Url(otsikko, url);
                urlList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return urlList;
    }

}
