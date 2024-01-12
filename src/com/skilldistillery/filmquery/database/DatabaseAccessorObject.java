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
    public Film findFilmById(int filmId) throws SQLException {
        Film film = null;
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PWD);

            String sql = "SELECT * FROM film WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, filmId);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String title = rs.getString("title");
                String description = rs.getString("description");
                short releaseYear = rs.getShort("release_year");
                int languageId = rs.getInt("language_id");
                int rentalDurationInDays = rs.getInt("rental_duration");
                double rentalRate = rs.getDouble("rental_rate");
                int rentalLengthInMinutes = rs.getInt("length");
                double replacementCost = rs.getDouble("replacement_cost");
                String rating = rs.getString("rating");
                String specialFeatures = rs.getString("special_features");

                film = new Film(filmId, title, description, releaseYear, languageId, rentalDurationInDays,
                        rentalRate, rentalLengthInMinutes, replacementCost, rating, specialFeatures);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return film;
    }

    @Override
    public Actor findActorById(int actorId) throws SQLException {
        Actor actor = null;
        Connection conn = DriverManager.getConnection(URL, USER, PWD);

        String sql = "SELECT id, first_name, last_name FROM actor WHERE id = ?";
        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, actorId);
        ResultSet actorResult = ps.executeQuery();

        if (actorResult.next()) {
            actor = new Actor();
            actor.setId(actorResult.getInt("id"));
            actor.setFirstName(actorResult.getString("first_name"));
            actor.setLastName(actorResult.getString("last_name"));
            actor.setFilm(findFilmsByActorId(actorId));

            actorResult.close();
            ps.close();
            conn.close();

        } else {
            System.out.println("Actor ID does not exist. Please enter a valid ID.");
        }
        return actor;
    }

    @Override
    public List<Film> findFilmsByActorId(int actorId) {
        List<Film> films = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PWD);
            String sql = "SELECT id, title, description, release_year, language_id, rental_duration, ";
            sql += " rental_rate, length, replacement_cost, rating, special_features "
                    + " FROM film JOIN film_actor ON film.id = film_actor.film_id " + " WHERE actor_id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, actorId);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                int filmId = rs.getInt("id");
                String title = rs.getString("title");
                String description = rs.getString("description");
                short releaseYear = rs.getShort("release_year");
                int languageId = rs.getInt("language_id");
                int rentalDurationInDays = rs.getInt("rental_duration");
                double rentalRate = rs.getDouble("rental_rate");
                int rentalLengthInMinutes = rs.getInt("length");
                double replacementCost = rs.getDouble("replacement_cost");
                String rating = rs.getString("rating");
                String specialFeatures = rs.getString("special_features");

                Film film = new Film(filmId, title, description, releaseYear, languageId, rentalDurationInDays,
                        rentalRate, rentalLengthInMinutes, replacementCost, rating, specialFeatures);

                films.add(film);
            }
            rs.close();
            ps.close();
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return films;
    }

    @Override
    public List<Actor> findActorsByFilmId(int filmId) throws SQLException {
        List<Actor> actors = new ArrayList<>();
        Connection conn = DriverManager.getConnection(URL, USER, PWD);
        String sql = "SELECT film.title, actor.first_name, actor.last_name\n" + "FROM actor\n"
                + "JOIN film_actor ON actor.id = film_actor.actor_id\n" + "JOIN film ON film.id = film_actor.film_id\n"
                + "WHERE film.id = ?";

        PreparedStatement ps = conn.prepareStatement(sql);
        ps.setInt(1, filmId);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Actor actor = new Actor();
            String title = rs.getString("title");
            actor.setFirstName(rs.getString("first_name"));
            actor.setLastName(rs.getString("last_name"));

            actors.add(actor);
        }

        rs.close();
        ps.close();
        conn.close();

        return actors;
    }
}
