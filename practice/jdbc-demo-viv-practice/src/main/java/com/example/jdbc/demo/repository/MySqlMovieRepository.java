package com.example.jdbc.demo.repository;

import com.example.jdbc.demo.model.Movie;
import com.example.jdbc.demo.model.MovieDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import org.springframework.jdbc.support.KeyHolder;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Date;

import java.time.LocalDate;
import java.util.List;

@Repository
@Primary
public class MySqlMovieRepository implements MovieRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public Optional<Movie> getById(int movieID) {
        String sql = "SELECT * FROM movie WHERE MovieID = ?";

        try {
            Movie movie = jdbcTemplate.queryForObject(sql, movieRowMapper(). movieID);
            return Optional.of(movie);

        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }

        catch(Exception e) {
            System.out.println(e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Movie> getAll() {
        String sql = "SELECT * FROM movie ORDER By Title";
        //create mapper first before the try catch
        try {
            return jdbcTemplate.query(sql, movieRowMapper());
        } catch(Exception e) {
          //we are printing here, do not do this in summative or ever-> just to show, io
            System.out.println(e.getMessage());
        }
        //just gives id
        return List.of();
    }

    @Override
    public Movie add(Movie movie) {
        //Why use ? placeholders? -> prevents SQL injection, injects values safely, order matters: must match your parameter order
        String sql = "INSERT INTO movie (Title, ReleaseDate, GenreID, RatingID) VALUES(?, ?, ?, ?)";
        KeyHolder keyHolder = new GeneratedKeyHolder();

        try {
            jdbcTemplate.update(connection -> {
                // ALWAYS USE THE PREPARED STATEMENTS
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

                ps.setString(1, movie.getTitle());
                ps.setDate(2, Date.valueOf(movie.getReleaseDate()));
                ps.setInt(3, movie.getGenreID());
                ps.setInt(4, movie.getRatingID());
                return ps;
            }, keyHolder);
            movie.setMovieID(keyHolder.getKey().intValue());
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return movie;
    }

    @Override
    public Movie update(Movie movie) {
        String sql = "UPDATE movie SET Title = ?, ReleaseDate = ?, GenreID = ?, RatingID = ? WHERE MovieID = ?";

        try {
            int rowsAffected = jdbcTemplate.update(sql,
                    movie.getTitle(),
                    movie.getReleaseDate(),
                    movie.getGenreID(),
                    movie.getRatingID(),
                    movie.getMovieID());

            if (rowsAffected == 0) {
                throw new RuntimeException("Movie with ID " + movie.getMovieID() + " is not found!");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return movie;
    }

    @Override
    public boolean delete(int movieID) {
        String sql = "DELETE FROM movie WHERE MovieID = ?";
        try {
            int rowsAffected = jdbcTemplate.update(sql, movieID);
            return rowsAffected > 0;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return false;
    }

    @Override
    public List<MovieDetails> getByGenre(int genreID) {
        String sql = """
                SELECT MovieID, Title, GenreName, RatingCode 
                FROM movie m 
                    INNER JOIN genre g ON m.GenreID = g.GenreID 
                    INNER JOIN rating r ON m.RatingID = r.RatingID 
                WHERE g.GenreID = ?
                """;

        try {
            return jdbcTemplate.query(sql, movieDetailRowMapper(), genreID);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return List.of();
    }


    //this is the function takig the data from the SQL database and it's turning it into a java obejct
    /*
    I'm about to use some objects that aren't being passed in as parameters, but this is part of the magic.
    But the nice thing is this magic, you can copy and paste it into every repository class you have. And just change this from movie and change the internal code to whatever you're using. Genres, you know, school, student, teacher, whatever.


    When you're retrieving data from a database using JDBC, the ResultSet holds the rows of data returned by your query.
    To turn each row into a Movie object, you typically do the following:
    1. Create a new Movie object
    2. Use setters to assign values from the ResultSet to your object

    Why you use rs.getString() or rs.getInt()
    - You need to tell Java the type of data you're pulling from the database:
        - Use getString() for VARCHAR, TEXT, etc.
        - Use getInt() for INTEGER
        - Use getBoolean(), getDouble(), etc. for other types
     */

    //mapper function tells JDBC how to convert SWL querys oclumns into a java model
    private RowMapper<Movie> movieRowMapper() {
        return (rs, rowNum) -> {
            Movie movie = new Movie();
            movie.setMovieID(rs.getInt("MovieID"));
            movie.setTitle(rs.getString("Title"));
            movie.setReleaseDate(rs.getObject("ReleaseDate", LocalDate.class));
            movie.setGenreID(rs.getInt("GenreID"));
            movie.setRatingID(rs.getInt("RatingID"));

            return movie;
        };

        private RowMapper<MovieDetails> movieDetailRowMapper() {
            return (rs, rowNum) -> {
                MovieDetails movie = new MovieDetails();

                movie.setMovieID(rs.getInt("MovieID"));
                movie.setTitle(rs.getString("Title"));
                movie.setGenreName(rs.getString("GenreName"));
                movie.setRatingCode(rs.getString("RatingCode"));

                return movie;
            };
        }
    }


}
