package Dao;

import Domain.Movie;
import java.util.ArrayList;

public interface MovieDao {
    
    boolean createMovie(Movie movie);
    
    boolean modifyMovie(Movie movie);
    
    boolean deleteMovie(int id);
    
    ArrayList<Movie> getAllMovies();

    ArrayList<Movie> findByDirector(String director);

    ArrayList<Movie> findByTitle(String title);
}
