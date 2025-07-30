# Implementations

2. Add the method to the interface 

```
Movie getById(int movieID);
```

- Changed it to optional

```
Optional<Movie> getById(int movieID);
```

Why Optional?
- Optional helps avoid null and makes it clear this might return no result
- Forces you to handle the "not found" case



2. Implement in MySqlMovieRepository
Go to the repository - mysql repo -> add the method for sql
- all my query stuff is inthe JDBC template
- need one mapper for every column case
- in ours, we have all 

```
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
```
Notes
- queryForObject() is for 1 result only — throws error if 0 or many rows
- Use the existing movieRowMapper() to map result to a Movie object
- Pass the value (movieID) after the mapper — matches ? in SQL
- Always catch EmptyResultDataAccessException for 0-row results

3. Use the method in DemoApplication
```
private void getById() {
		System.out.print("Enter Movie ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		Optional<Movie> result = movieRepo.getById(id);

		if (result.isPresent()) {
			Movie movie = result.get();
			System.out.printf("ID: %d%n", movie.getMovieID());
			System.out.printf("Title: %s%n", movie.getTitle());
			System.out.printf("Release Date: %s%n", movie.getReleaseDate());
		} else {
			System.out.printf("Movie with id %d not found!%n", id);
		}
	}
```
Notes
- Use scanner.nextLine() then Integer.parseInt() to safely get user input
- Optional.isPresent() checks if a movie was found
- If found, use get() to access the actual Movie object

4. Call the method inside run()
ADD in here 
5. 
```
@Override
	public void run(String... args) throws Exception {
		//displayAll();
		//createMovie();
		getById();
	}

```

---
Then follow the same process for Update, then delete

---
# getByGenre() Implementation
Step 0: SQL Query
```
SELECT MovieId, Title, GenreName, RatingCode
FROM movie m
    INNER JOIN genre g ON m.GenreID = g.GenreID
    INNER JOIN rating r ON m.RatngID = r.RatingID
WHERE g.GenreID = 1
```

1. Create the class -> need a new model for this
- This model combines data from multiple joined tables
```
package com.example.jdbc.demo.model;

public class MovieDetails {
    private int movieID;
    private String title;
    private String ratingCode;
    private String genreName;

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRatingCode() {
        return ratingCode;
    }

    public void setRatingCode(String ratingCode) {
        this.ratingCode = ratingCode;
    }

    public String getGenreName() {
        return genreName;
    }

    public void setGenreName(String genreName) {
        this.genreName = genreName;
    }
}

```
2. Add to the interface
- If you are adding filters, can add a filtering repo
- Otherwise, no need to create a separate repository 
- Use List<MovieDetails> to return multiple results

```
List<MovieDetails> getByGenre(int genreID);
```

3. Need a new mapper 
- 

```
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
```

4. Implement in MySqlMovieRepository
- grab sql from mysql workbench
```
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
```

5. Use in DemoApplication 
```
private void getByGenre() {
		System.out.print("Enter Genre ID: ");
		int id = Integer.parseInt(scanner.nextLine());

		List<MovieDetails> movies = movieRepo.getByGenre(id);

		for(MovieDetails deets : movies) {
			System.out.printf("%d\t%s\t%s\t%s%n", deets.getMovieID(), deets.getTitle(), deets.getGenreName(), deets.getRatingCode());
		}
	}
```


5. Call It in run()
```
getByGenre();
```

# Repo tests

```
package com.example.jdbc.demo;

import com.example.jdbc.demo.repository.MovieRepository;
import com.example.jdbc.demo.repository.MySqlMovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import static org.junit.jupiter.api.Assertions.*;

public class MovieRepositoryTests {

    @Test
    void canLoadMovies() {
        // Create datasource manually
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setUrl("jdbc:mysql://localhost:3306/moviedb");
        dataSource.setUsername("root");
        dataSource.setPassword("$Testing123");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");

        // Create JdbcTemplate manually
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // Create your repository manually (assuming it takes JdbcTemplate)
        MovieRepository movieRepository = new MySqlMovieRepository(jdbcTemplate);

        // Test it
        var movies = movieRepository.getAll();
        System.out.println("Found " + movies.size() + " movies");
        assertFalse(movies.isEmpty());
    }
}


```