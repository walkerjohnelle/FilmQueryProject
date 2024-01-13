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
            System.out.println("***********Menu Options:***********");
            System.out.println("1. Look Up Film By ID:            |");
            System.out.println("2. Look Up Film By Search Keyword:|");
            System.out.println("3. Exit Application               |");
            System.out.println("***********Menu Options:***********");

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
                    // TODO: Implement searching by keyword
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
        System.out.println("Film ID: " + film.getId());
        System.out.println("Title: " + film.getTitle());
        System.out.println("Description: " + film.getDescription());
        System.out.println("Release Year: " + film.getReleaseYear());
        System.out.println("Language: " + film.getLanguage());
        System.out.println("Rental Duration In Days: " + film.getRentalDurationInDays());
        System.out.println("Rental Rate: $" + film.getRentalRate());
        System.out.println("Film Length In Minutes: " + film.getFilmLengthInMinutes());
        System.out.println("Replacement Cost: $" + film.getReplacementCost());
        System.out.println("Rating: " + film.getRating());
        System.out.println("Special Features: " + film.getSpecialFeatures());

        List<Actor> actors = film.getFilmCast();
        if (actors != null && !actors.isEmpty()) {
            System.out.println("Film Cast:");
            for (Actor actor : actors) {
                System.out.println("  " + actor);
            }
        }
    }
}
