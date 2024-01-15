// FilmQueryApp.java
package com.skilldistillery.filmquery.app;

import java.util.List;
import java.util.Scanner;

import com.skilldistillery.filmquery.database.DatabaseAccessor;
import com.skilldistillery.filmquery.database.DatabaseAccessorObject;
import com.skilldistillery.filmquery.entities.Actor;
import com.skilldistillery.filmquery.entities.Film;

public class FilmQueryApp {

	DatabaseAccessor db = new DatabaseAccessorObject();

	public static void main(String[] args) {
		FilmQueryApp app = new FilmQueryApp();
		app.launch();
	}

	private void launch() {
		Scanner input = new Scanner(System.in);

		startUserInterface(input);

		input.close();
	}

	private void startUserInterface(Scanner input) {
		boolean validEntry = true;

		while (validEntry) {
			System.out.println("*********** Menu Options ************");
			System.out.println("1. Look Up Film By ID:              |");
			System.out.println("2. Look Up Film By Search Keyword:  |");
			System.out.println("3. Exit Application                 |");
			System.out.println("*************************************");

			int menuSelection = input.nextInt();
			input.nextLine();

			switch (menuSelection) {
			case 1:
				System.out.print("Enter Film ID: ");
				int filmId = input.nextInt();
				Film film = db.findFilmById(filmId);
				if (film != null) {
					displayFilmDetails(film);
				} else {
					System.out.println("Film not found for ID: " + filmId);
				}
				break;
			case 2:
				System.out.print("Enter Keyword From Film Title / Description: ");
				String keyword = input.nextLine();
				List<Film> films = db.findFilmByKeyword(keyword);
				if (films != null && !films.isEmpty()) {
					displayFilmDetails(films);
				} else {
					System.out.println("No results found based on provided keyword. " + keyword);
				}

				break;
			case 3:
				validEntry = false;
				System.out.println("Exiting Application.");
				break;
			default:
				System.out.println("Invalid entry. Please select number (1 - 3): ");
			}
		}
	}

	private void displayFilmDetails(Film film) {
		StringBuilder output = new StringBuilder();

		output.append("Film ID: ").append(film.getId()).append("\n");
		output.append("Title: ").append(film.getTitle()).append("\n");
		output.append("Description: ").append(film.getDescription()).append("\n");
		output.append("Release Year: ").append(film.getReleaseYear()).append("\n");
		output.append("Language: ").append(film.getLanguage()).append("\n");
		output.append("Rental Rate: $").append(film.getRentalRate()).append("\n");
		output.append("Film Length In Minutes: ").append(film.getFilmLengthInMinutes()).append("\n");
		output.append("Rating: ").append(film.getRating()).append("\n");

		List<Actor> actors = film.getFilmCast();
		if (actors != null && !actors.isEmpty()) {
			output.append("Film Cast:\n");
			for (Actor actor : actors) {
				output.append("  ").append(actor).append("\n");
			}
		}

		System.out.println(output.toString());
	}

	private void displayFilmDetails(List<Film> films) {
		for (Film film : films) {
			StringBuilder output = new StringBuilder();

			output.append("Film ID: ").append(film.getId()).append("\n");
			output.append("Title: ").append(film.getTitle()).append("\n");
			output.append("Description: ").append(film.getDescription()).append("\n");
			output.append("Release Year: ").append(film.getReleaseYear()).append("\n");
			output.append("Language: ").append(film.getLanguage()).append("\n");
			output.append("Rental Rate: $").append(film.getRentalRate()).append("\n");
			output.append("Film Length In Minutes: ").append(film.getFilmLengthInMinutes()).append("\n");
			output.append("Rating: ").append(film.getRating()).append("\n");

			List<Actor> actors = film.getFilmCast();
			if (actors != null && !actors.isEmpty()) {
				output.append("Film Cast:\n");
				for (Actor actor : actors) {
					output.append("  ").append(actor).append("\n");
				}
			}

			System.out.println(output.toString());
			System.out.println("-------------------------------");
		}
	}

}
