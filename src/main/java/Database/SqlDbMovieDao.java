package Database;

import Domain.Movie;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Dao.MovieDao;

public class SqlDbMovieDao implements MovieDao {

    private DbConnection db;
    private Connection connection;
    private ArrayList<Movie> movieList;

    public SqlDbMovieDao(String dbFile) throws SQLException {
        this.db = new DbConnection(dbFile);
        this.connection = db.getConnection();
    }

    public SqlDbMovieDao() throws SQLException {
        this("jdbc:sqlite:lukuvinkit.db");
    }

    @Override
    public boolean createMovie(Movie movie) {
        if (movie == null) {
            return false;
        }
        String query = "INSERT INTO Movie(nimeke, ohjaaja, "
            + "julkaisuvuosi, kesto) VALUES (?, ?, ?, ?);";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, movie.getTitle());
            prepared.setString(2, movie.getDirector());
            prepared.setInt(3, movie.getReleaseYear());
            prepared.setInt(4, movie.getLength());
            prepared.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    @Override
    public ArrayList<Movie> getAllMovies() {
        movieList = new ArrayList<Movie>();
        String query = "SELECT id, nimeke, ohjaaja, "
            + "julkaisuvuosi, kesto "
            + "FROM Movie;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            ResultSet rs = prepared.executeQuery();
            while (rs.next()) {
                Integer id = rs.getInt("id");
                String nimeke = rs.getString("nimeke");
                String ohjaaja = rs.getString("ohjaaja");
                Integer julkaisuvuosi = rs.getInt("julkaisuvuosi");
                Integer kesto = rs.getInt("kesto");
                Movie lisattava = new Movie(id, nimeke, ohjaaja, julkaisuvuosi,
                    kesto);

                movieList.add(lisattava);
            }
        } catch (SQLException error) {
            System.out.println(error.getMessage());

            return null;
        }
        return movieList;
    }

    @Override
    public ArrayList<Movie> findByDirector(String director) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ArrayList<Movie> findByTitle(String title) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public boolean modifyMovie(Movie movie) {
        if (movie.getId() == null) {
            return false;
        }
        String query = "UPDATE Movie SET nimeke=?, ohjaaja=?, julkaisuvuosi=?, "
                + "kesto=? WHERE id=?;";
        try {
            PreparedStatement prepared = connection.prepareStatement(query);
            prepared.setString(1, movie.getTitle());
            prepared.setString(2, movie.getDirector());
            prepared.setInt(3, movie.getReleaseYear());
            prepared.setInt(4, movie.getLength());
            prepared.setInt(5, movie.getId());
            prepared.executeUpdate();
            return true;
        } catch (SQLException error) {
            System.out.println(error.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteMovie(int id) {
        String query = "DELETE FROM Movie WHERE id=?;";
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
    public boolean deleteAllMovies() {
        String query = "DELETE FROM Movie;";
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
