package rest;
import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import domain.Actor;
import domain.Comment;
import domain.Movie;
import domain.Rate;
import domain.services.ActorService;
import domain.services.CommentService;
import domain.services.MovieService;
import domain.services.RateService;

@Path("/v0.1")
public class Resources {
	private MovieService movieDB = new MovieService();
	private ActorService actorDB = new ActorService();
	
	@GET
	@Path("/movie")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getAll() {
		return movieDB.getAll();
	}
	
	@POST
	@Path("/movie")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response Add(Movie m) {
		movieDB.add(m);
		return Response.ok(m.getId()).build();
	}
	
	@GET
	@Path("/movie/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(@PathParam("id") int id) {
		Movie m = movieDB.get(id);
		if (m == null)
			return Response.status(404).build();
		return Response.ok(m).build();
	}
	
	@PUT
	@Path("/movie/{id}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") int id, Movie movie) {
		Movie m = movieDB.get(id);
		if (m == null)
			return Response.status(404).build();
		movie.setId(id);
		movieDB.update(movie);
		
		return Response.ok().build();
	}
	
	@GET
	@Path("/movie/{id}/comments")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Comment> getComments(@PathParam("id") int id) {
		Movie m = movieDB.get(id);
		if (m == null)
			return null;
		if (m.getComments() == null)
			m.setComments(new ArrayList<Comment>());
		return m.getComments();
	}
	
	@POST
	@Path("/movie/{id}/comments")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addComment(@PathParam("id") int id, Comment c) {
		Movie m = movieDB.get(id);
		if (m == null)
			return Response.status(404).build();
		if (m.getComments() == null)
			m.setComments(new ArrayList<Comment>());
		CommentService.add(m, c);
		return Response.ok().build();
	}
	
	@DELETE
	@Path("/movie/{movieId}/comment/{commentId}")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response deleteComment(@PathParam("movieId") int movieId, @PathParam("commentId") int commentId) {
		Movie m = movieDB.get(movieId);
		Comment c = CommentService.get(m, commentId);
		if (m == null)
			return Response.status(404).build();
		if (m.getComments() == null) {
			m.setComments(new ArrayList<Comment>());
			return Response.status(404).build();
		}
		if (c == null)
			return Response.status(404).build();
		CommentService.delete(m, c);
		return Response.ok().build();
	}
	
	@POST
	@Path("/movie/{id}/rate")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addRate(@PathParam("id") int id, Rate r) {
		Movie m = movieDB.get(id);
		if (m == null)
			return Response.status(404).build();
		if (m.getRates() == null)
			m.setRates(new ArrayList<Rate>());
		if (RateService.isCorrect(r))
			RateService.add(m, r);
		else
			return Response.status(404).build();
		return Response.ok().build();
	}
	
	@GET
	@Path("/actors")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getAllActors() {
		return actorDB.getAll();
	}
	
	@POST
	@Path("/actors")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response addActor(Actor a) {
		actorDB.add(a);
		return Response.ok(a.getId()).build();
	}
	
	@GET
	@Path("/movie/{movieId}/actors/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Actor> getActors(@PathParam("movieId") int movieId) {
		Movie m = movieDB.get(movieId);
		if (m == null)
			return null;
		if (m.getActors() == null)
			m.setActors(new ArrayList<Actor>());
		return m.getActors();
	}
	
	@POST
	@Path("/movie/{movieId}/actors")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response assignActor(@PathParam("movieId") int movieId, int actorId) {
		Movie m = movieDB.get(movieId);
		Actor a = actorDB.get(actorId);
		if (m == null || a == null)
			return Response.status(404).build();
		
		actorDB.assignToMovie(m, a);
		return Response.ok().build();
	}
	
	@GET
	@Path("/actor/{actorId}/movies")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Movie> getMovies(@PathParam("actorId") int actorId) {
		Actor a = actorDB.get(actorId);
		if (a == null)
			return null;
		if (a.getMovies() == null)
			a.setMovies(new ArrayList<Movie>());
		return a.getMovies();
	}
}
