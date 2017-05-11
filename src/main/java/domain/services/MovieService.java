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
				m.setCountry(movie.getCountry());
				m.setDescription(movie.getDescription());
				m.setDirector(movie.getDirector());
				m.setGenre(movie.getGenre());
				m.setReleaseYear(movie.getReleaseYear());
				m.setTitle(movie.getTitle());
			}
		}
	}
	public void delete(Movie movie) {
		db.remove(movie);
	}
}
