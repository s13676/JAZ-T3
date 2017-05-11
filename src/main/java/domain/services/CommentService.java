package domain.services;

import domain.Comment;
import domain.Movie;

public class CommentService {
	private static int currentId = 1;
	
	public static void add(Movie m, Comment c) {
		c.setId(currentId++);
		m.getComments().add(c);
	}
	public static Comment get(Movie m, int id) {
		if (m == null)
			return null;
		for (Comment c: m.getComments()) {
			if (c.getId() == id)
				return c;
		}
		return null;
	}
	public static void delete(Movie m, Comment c) {
		m.getComments().remove(c);
	}
}
