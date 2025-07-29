package com.example.jdbc.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.CommandLineRunner;
import org.springframework.beans.factory.annotation.Autowired;

import com.example.jdbc.demo.model.Movie;
import com.example.jdbc.demo.repository.MovieRepository;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.time.LocalDate;

@SpringBootApplication
public class DemoApplication implements CommandLineRunner {

	@Autowired
	private MovieRepository movieRepo;

	private Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		//displayAll();
		//createMovie();
		getById();
	}


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

	private void displayAll() {
		List<Movie> movies = movieRepo.getAll();

		for (Movie m : movies) {
			System.out.printf("ID: %d%n", m.getMovieID());
			System.out.printf("Tite: %s%n", m.getTitle());
			System.out.printf("ReleaseDate: %s%n", m.getReleaseDate());
		}
	}

	private void createMovie() {
		Movie movie = new Movie();

		System.out.print("Enter Title: ");
		movie.setTitle(scanner.nextLine());

		System.out.print("Enter Release Date: (YYYY-MM-DD) ");
		movie.setReleaseDate(LocalDate.parse(scanner.nextLine()));

		System.out.print("Enter Genre ID: ");
		movie.setGenreID(Integer.parseInt(scanner.nextLine()));

		System.out.print("Enter Rating ID: ");
		movie.setRatingID(Integer.parseInt(scanner.nextLine()));

		movie = movieRepo.add(movie);
		System.out.printf("Movie with id: %d was created!%n", movie.getMovieID());
	}
}
