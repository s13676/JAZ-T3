package domain.services;

import java.util.ArrayList;
import java.util.List;

import domain.Actor;
import domain.Movie;

public class ActorService {
	private static List<Actor> db = new ArrayList<Actor>();
	private static int currentId = 1;
	
	public List<Actor> getAll() {
		return db;
	}
	public void add(Actor a) {
		a.setId(currentId++);
		db.add(a);
	}
	public Actor get(int id) {
		for (Actor m: db) {
			if (m.getId() == id)
				return m;
		}
		return null;
	}
	public void assignToMovie(Movie m, Actor actor) {
		for (Movie movie: actor.getMovies()) {
			if (movie.getId() == m.getId())
				return;
		}
		actor.getMovies().add(m);
	}
}
