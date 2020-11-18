package Database;

import java.sql.*;
import java.util.ArrayList;
import Domain.*;

public class db {
    private Connection connection;
    private ArrayList<Book> bookList;
    private ArrayList<Url> urlList;
    
    public db(String database) throws Exception {
        connection = DriverManager.getConnection(database);
        
        String createBookTableQuery = "CREATE TABLE IF NOT EXISTS Books "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "kirjoittaja varchar,"
                + "otsikko varchar,"
                + "tyyppi varchar,"
                + "ISBN varchar,"
                + "tagit varchar,"
                + "relatedCourses varchar)"; 
        
        String createUrlTableQuery = "CREATE TABLE IF NOT EXISTS Url "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "otsikko varchar,"
                + "url varchar,"
                + "tyyppi varchar,"
                + "kommentti varchar"
                + "releatedCourses varchar)";
        
        try {
           connection.createStatement().execute(createBookTableQuery); 
           connection.createStatement().execute(createUrlTableQuery);
        } catch(SQLException error) {
            System.out.println(error.getMessage());
        }
        
    }
    
    public void closeConnection() throws Exception {
        connection.close();
    }
    
    public void createBook() {
        String query = "INSERT INTO books (kirjoittaja, otsikko) values (?, ?);";       
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, "testi");
            prepared.setString(2, "Aihe testeista");
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
    
    public void createURL() {
        String query = "INSERT INTO Url (otsikko, url) values (?, ?);";
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, "Juttuja juttuja");
            prepared.setString(2, "www.google.fi");
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
    
    public ArrayList<Url> getAllURLs() {
        urlList = new ArrayList<Url>();
        String query = "SELECT otsikko, url from Url;";
        
        try(Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String otsikko = rs.getString(("otsikko"));
                String url = rs.getString("url");
                Url lisattava = new Url();
                lisattava.setOtsikko(otsikko);
                lisattava.setUrl(url);
                urlList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return urlList;
    }
    
    public ArrayList<Book> getAllBooks() {
        bookList = new ArrayList<Book>();
        String query = "SELECT kirjoittaja, otsikko FROM books;";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String kirjoittaja = rs.getString("kirjoittaja");
                String otsikko = rs.getString("otsikko");
                Book lisattava = new Book(kirjoittaja, otsikko);
                bookList.add(lisattava);
            }       
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }        
        return bookList;
    }
}
