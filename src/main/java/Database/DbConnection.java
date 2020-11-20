package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;
    
    public DbConnection() {
        connection = null;
    }
    
    private void connect() throws SQLException {
        connection = DriverManager.getConnection("jdbc:sqlite:lukuvinkit.db");
        createDbIfNotExists();
    }

    
    public Connection getConnection() throws Exception {
        if (this.connection == null) {
            this.connect();
        }
        return this.connection;
    }    

    private void createDbIfNotExists() {
        String createBookTableQuery = "CREATE TABLE IF NOT EXISTS Books "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "kirjoittaja varchar,"
                + "nimeke varchar,"
                + "julkaisuvuosi varchar,"
                + "sivumaara INTEGER,"
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

}
