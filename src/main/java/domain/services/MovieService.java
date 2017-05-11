package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Movie;

public class MovieService {
	private static List<Movie> db = new ArrayList<Movie>();
	private static int currentId = 1;
	
	public List<Movie> getAll() {
		return db;
	}
	public void add(Movie m) {
		m.setId(currentId++);
		db.add(m);
	}
	public Movie get(int id) {
		for (Movie m: db) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	public void update(Movie movie) {
		for (Movie m: db) {
			if (m.getId() == movie.getId()) {
				if (movie.getCountry() != null)
					m.setCountry(movie.getCountry());
				if (movie.getDescription() != null)
					m.setDescription(movie.getDescription());
				if (movie.getDirector() != null)
					m.setDirector(movie.getDirector());
				if (movie.getGenre() != null)
					m.setGenre(movie.getGenre());
				if (movie.getReleaseYear() != 0)
					m.setReleaseYear(movie.getReleaseYear());
				if (movie.getTitle() != null)
				m.setTitle(movie.getTitle());
			}
		}
	}
	public void delete(Movie movie) {
		db.remove(movie);
	}
}
