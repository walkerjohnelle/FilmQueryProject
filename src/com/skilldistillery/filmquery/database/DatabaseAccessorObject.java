// DatabaseAccessorObject.java
package com.skilldistillery.filmquery.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class DatabaseAccessorObject implements DatabaseAccessor {
	private static final String URL = "jdbc:mysql://localhost:3306/sdvid";
	private static final String USER = "student";
	private static final String PWD = "student";

	static {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	@Override
	public Film findFilmById(int filmId) {
		Film film = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
				PreparedStatement ps = conn.prepareStatement("SELECT * FROM film WHERE id = ?")) {

			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					film = createFilmFromResultSet(rs);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return film;
	}

	@Override
	public Actor findActorById(int actorId) {
		Actor actor = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
				PreparedStatement ps = conn
						.prepareStatement("SELECT id, first_name, last_name FROM actor WHERE id = ?")) {

			ps.setInt(1, actorId);
			try (ResultSet actorResult = ps.executeQuery()) {
				if (actorResult.next()) {
					actor = createActorFromResultSet(actorResult);
					actor.setFilm(findFilmsByActorId(actorId));
				} else {
					System.out.println("Actor ID does not exist. Please enter a valid ID.");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return actor;
	}

	@Override
	public List<Film> findFilmsByActorId(int actorId) {
		List<Film> films = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
				PreparedStatement ps = conn
						.prepareStatement("SELECT id, title, description, release_year, language_id, rental_duration, "
								+ "rental_rate, length, replacement_cost, rating, special_features "
								+ "FROM film JOIN film_actor ON film.id = film_actor.film_id WHERE actor_id = ?")) {

			ps.setInt(1, actorId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Film film = createFilmFromResultSet(rs);
					films.add(film);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return films;
	}

	@Override
	public List<Actor> findActorsByFilmId(int filmId) {
		List<Actor> filmCast = new ArrayList<>();
		try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
				PreparedStatement ps = conn
						.prepareStatement("SELECT actor.id, actor.first_name, actor.last_name FROM actor "
								+ "JOIN film_actor ON actor.id = film_actor.actor_id "
								+ "JOIN film ON film.id = film_actor.film_id WHERE film.id = ?")) {

			ps.setInt(1, filmId);
			try (ResultSet rs = ps.executeQuery()) {
				while (rs.next()) {
					Actor actor = createActorFromResultSet(rs);
					filmCast.add(actor);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return filmCast;
	}

	@Override
	public List<Film> findFilmByKeyword(String keyword) {
	    List<Film> keywordSearchResult = new ArrayList<>();
	    try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
	            PreparedStatement ps = conn
	                    .prepareStatement("SELECT * FROM film WHERE title LIKE ? OR description LIKE ?")) {

	        ps.setString(1, "%" + keyword + "%");
	        ps.setString(2, "%" + keyword + "%");

	        try (ResultSet rs = ps.executeQuery()) {
	            while (rs.next()) {
	                Film film = createFilmFromResultSet(rs);
	                keywordSearchResult.add(film);
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }

	    System.out.println("Keyword: " + keyword);
	    System.out.println("Search Result Size: " + keywordSearchResult.size());

	    for (Film film : keywordSearchResult) {
	        System.out.println(film);
	    }

	    return keywordSearchResult; 
	}


	private Film createFilmFromResultSet(ResultSet rs) throws SQLException {
		int filmId = rs.getInt("id");
		String title = rs.getString("title");
		String description = rs.getString("description");
		short releaseYear = rs.getShort("release_year");
		String languageId = rs.getString("language_id");
		int rentalDurationInDays = rs.getInt("rental_duration");
		double rentalRate = rs.getDouble("rental_rate");
		int rentalLengthInMinutes = rs.getInt("length");
		double replacementCost = rs.getDouble("replacement_cost");
		String rating = rs.getString("rating");
		String specialFeatures = rs.getString("special_features");
		List<Actor> filmCast = findActorsByFilmId(filmId);

		Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDurationInDays, rentalRate,
				rentalLengthInMinutes, replacementCost, rating, specialFeatures, filmCast);

		String language = getLanguageNameFromDatabase(languageId);
		film.setLanguage(language);

		return film;
	}

	private Actor createActorFromResultSet(ResultSet rs) throws SQLException {
		int actorId = rs.getInt("id");
		String firstName = rs.getString("first_name");
		String lastName = rs.getString("last_name");

		return new Actor(actorId, firstName, lastName);
	}

	private String getLanguageNameFromDatabase(String languageId) {
		String language = null;
		try (Connection conn = DriverManager.getConnection(URL, USER, PWD);
				PreparedStatement ps = conn.prepareStatement("SELECT name FROM language WHERE id = ?")) {

			ps.setString(1, languageId);
			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					language = rs.getString("name");
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return language;
	}
}
