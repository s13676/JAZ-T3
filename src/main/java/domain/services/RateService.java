package domain.services;

import domain.Movie;
import domain.Rate;

public class RateService {
	public static boolean isCorrect(Rate r) {
		return r.getRate() > 0 && r.getRate() < 11;
	}
	public static void add(Movie m, Rate r) {
		m.getRates().add(r);
		float rating = 0;
		for (Rate rate: m.getRates())
			rating += rate.getRate();
		rating = rating / m.getRates().size();
		m.setRating(rating);
	}
}
