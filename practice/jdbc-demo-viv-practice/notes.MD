1. Add the method to the interface 

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