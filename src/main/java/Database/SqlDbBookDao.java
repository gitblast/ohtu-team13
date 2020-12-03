package Database;

import Domain.Book;
import Dao.BookDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SqlDbBookDao implements BookDao {

    private DbConnection db;
    private Connection connection;
    private ArrayList<Book> bookList;

    public SqlDbBookDao(String dbFile) throws SQLException {
        this.db = new DbConnection(dbFile);
        this.connection = db.getConnection();
    }

    public SqlDbBookDao() throws SQLException {
        this("jdbc:sqlite:lukuvinkit.db");
    }

    @Override
    public boolean createBook(Book book) {
        /*
        if (book == null 
            || book.getKirjoittaja() == null 
            || book.getNimeke() == null 
            || book.getJulkaisuvuosi() == null 
            || book.getSivumaara() == null 
            || book.getISBN() == null) {
            
            return false;
        }*/
        if (book == null) {
            return false; //kun k�li tukee kirjan lis��mist� ISBN:ll� 
            //voi poistaa t�n ja ottaa k�ytt��n yll�olevan tarkistuksen
        }
        if (book.getKirjoittaja() == null || book.getTitle() == null) {
            return false; // Cucumber testej� varten
        }
        String query = "INSERT INTO books (kirjoittaja, nimeke, julkaisuvuosi, "
                + "sivumaara, ISBN) VALUES (?, ?, ?, ?, ?);";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, book.getKirjoittaja());
            prepared.setString(2, book.getTitle());
            prepared.setInt(3, book.getJulkaisuvuosi());
            prepared.setInt(4, book.getSivumaara());
            prepared.setString(5, book.getISBN());
            prepared.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    public ArrayList<Book> getAllBooks() {
        bookList = new ArrayList<Book>();
        String query = "SELECT id, kirjoittaja, nimeke, "
                + "julkaisuvuosi, sivumaara, ISBN "
                + "FROM books;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String kirjoittaja = rs.getString("kirjoittaja");
                String nimeke = rs.getString("nimeke");
                Integer julkaisuvuosi = rs.getInt("julkaisuvuosi");
                Integer sivumaara = rs.getInt("sivumaara");
                String ISBN = rs.getString("ISBN");
                Book lisattava = new Book(id, kirjoittaja, nimeke,
                        julkaisuvuosi, sivumaara, ISBN);
                bookList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());

            return null;
        }
        return bookList;
    }

    public Book findByISBN(String ISBN) {
        Book book = null;
        String query = "SELECT id, kirjoittaja, nimeke, "
                + "julkaisuvuosi, sivumaara, ISBN "
                + "FROM books WHERE ISBN=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, ISBN);
            ResultSet rs = prepared.executeQuery();
            book = new Book(rs.getInt("id"),
                    rs.getString("kirjoittaja"), rs.getString("nimeke"),
                    rs.getInt("julkaisuvuosi"), rs.getInt("sivumaara"),
                    rs.getString("ISBN"));
        } catch (SQLException error) {
            System.out.println(error.getMessage());

            return null;
        }
        return book;
    }

    public ArrayList<Book> findByKirjoittaja(String searchTerm) {
        bookList = new ArrayList<Book>();
        String query = "SELECT id, kirjoittaja, nimeke, "
                + "julkaisuvuosi, sivumaara, ISBN "
                + "FROM books WHERE kirjoittaja=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, searchTerm);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String kirjoittaja = rs.getString("kirjoittaja");
                String nimeke = rs.getString("nimeke");
                Integer julkaisuvuosi = rs.getInt("julkaisuvuosi");
                Integer sivumaara = rs.getInt("sivumaara");
                String ISBN = rs.getString("ISBN");
                Book lisattava = new Book(id, kirjoittaja, nimeke,
                        julkaisuvuosi, sivumaara, ISBN);

                bookList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());

            return null;

        }
        return bookList;
    }

    public ArrayList<Book> findByNimeke(String searchTerm) {
        bookList = new ArrayList<Book>();
        String query = "SELECT id, kirjoittaja, nimeke, "
                + "julkaisuvuosi, sivumaara, ISBN "
                + "FROM books WHERE kirjoittaja=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, searchTerm);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String kirjoittaja = rs.getString("kirjoittaja");
                String nimeke = rs.getString("nimeke");
                Integer julkaisuvuosi = rs.getInt("julkaisuvuosi");
                Integer sivumaara = rs.getInt("sivumaara");
                String ISBN = rs.getString("ISBN");
                Book lisattava = new Book(id, kirjoittaja, nimeke,
                        julkaisuvuosi, sivumaara, ISBN);

                bookList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());

            return null;
        }
        return bookList;
    }
    
    public Book findWithAuthorAndTitle(String author, String title) {
        Book book = null;
        String query = "SELECT id, kirjoittaja, nimeke, "
                + "julkaisuvuosi, sivumaara, ISBN "
                + "FROM books WHERE kirjoittaja=? AND nimeke=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, author);
            prepared.setString(2, title);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String kirjoittaja = rs.getString("kirjoittaja");
                String nimeke = rs.getString("nimeke");
                Integer julkaisuvuosi = rs.getInt("julkaisuvuosi");
                Integer sivumaara = rs.getInt("sivumaara");
                String ISBN = rs.getString("ISBN");
                book = new Book(id, kirjoittaja, nimeke, julkaisuvuosi,
                        sivumaara, ISBN);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
        return book;
    }

    @Override
    public boolean modifyBook(Book book) {
        if (book.getId() == null) {
            return false;
        }
        String query = "UPDATE books SET kirjoittaja=?,"
                + "nimeke=?, julkaisuvuosi=?,"
                + "sivumaara=?, ISBN=? WHERE id=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, book.getKirjoittaja());
            prepared.setString(2, book.getTitle());
            prepared.setInt(3, book.getJulkaisuvuosi());
            prepared.setInt(4, book.getSivumaara());
            prepared.setString(5, book.getISBN());
            prepared.setInt(6, book.getId());
            prepared.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteBook(int id) {
        String query = "DELETE FROM books WHERE id=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setInt(1, id);
            prepared.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAllBooks() {
        String query = "DELETE FROM books;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }
}
