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
		for (Actor a: m.getActors()) {
			if (actor.getId() == a.getId()) {
				m.getActors().add(a);
				a.getMovies().add(m);
				return;
			}
		}
	}
}
