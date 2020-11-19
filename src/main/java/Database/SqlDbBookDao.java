package Database;

import Domain.Book;
import Dao.BookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class SqlDbBookDao implements BookDao {
    private DbConnection db;
    private Connection connection;
    private ArrayList<Book> bookList;

    public SqlDbBookDao() throws Exception {
        this.db = new DbConnection();
        this.connection = db.getConnection();
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
