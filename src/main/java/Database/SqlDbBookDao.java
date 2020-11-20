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

    public SqlDbBookDao(String dbFile) throws Exception {
        this.db = new DbConnection(dbFile);
        this.connection = db.getConnection();
    }

    public SqlDbBookDao() throws Exception {
        this.db = new DbConnection("jdbc:sqlite:lukuvinkit.db");
        this.connection = db.getConnection();        
    }
    
    
    
    @Override
    public void createBook(String kirjoittaja, String nimeke, Integer julkaisuvuosi, Integer sivumaara) {
        String query = "INSERT INTO books (kirjoittaja, nimeke, julkaisuvuosi, sivumaara) values (?, ?, ?, ?);";       
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, kirjoittaja);
            prepared.setString(2, nimeke);
            prepared.setInt(3, julkaisuvuosi);
            prepared.setInt(4, sivumaara);
            prepared.executeUpdate();
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }
    
    @Override
    public ArrayList<Book> getAllBooks() {
        bookList = new ArrayList<Book>();
        String query = "SELECT kirjoittaja, nimeke, julkaisuvuosi, sivumaara FROM books;";
        
        try (Statement statement = connection.createStatement()) {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                String kirjoittaja = rs.getString("kirjoittaja");
                String nimeke = rs.getString("nimeke");
                Integer julkaisuvuosi = rs.getInt("julkaisuvuosi");
                Integer sivumaara = rs.getInt("sivumaara");
                Book lisattava = new Book(kirjoittaja, nimeke, julkaisuvuosi, sivumaara, null, null, null);
                bookList.add(lisattava);
            }       
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }        
        return bookList;
    }

}
