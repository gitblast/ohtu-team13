package Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
    private Connection connection;
    private String dbFile;
    
    public DbConnection(String dbFile) {
        this.connection = null;
        this.dbFile = dbFile;
    }

    private void connect() throws SQLException {
        connection = DriverManager.getConnection(dbFile);
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
                + "kirjoittaja VARCHAR,"
                + "nimeke VARCHAR,"
                + "julkaisuvuosi INTEGER,"
                + "sivumaara INTEGER,"
                + "ISBN VARCHAR)"; 
        
        String createUrlTableQuery = "CREATE TABLE IF NOT EXISTS Url "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "otsikko VARCHAR,"
                + "url VARCHAR)";
        
        String createMovieTableQuery = "CREATE TABLE IF NOT EXISTS Movie "
                + "(id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nimeke VARCHAR,"
                + "ohjaaja VARCHAR,"
                + "julkaisuvuosi INTEGER,"
                + "kesto INTEGER)";
        try {
            connection.createStatement().execute(createBookTableQuery); 
            connection.createStatement().execute(createUrlTableQuery);
            connection.createStatement().execute(createMovieTableQuery);
        } catch (SQLException error) {
            System.out.println(error.getMessage());
        }
    }

}
