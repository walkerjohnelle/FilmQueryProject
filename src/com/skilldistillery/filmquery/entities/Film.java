package com.skilldistillery.filmquery.entities;

import java.util.Arrays;
import java.util.List;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private String languageId;
	private String language;
	private int rentalDurationInDays;
	private double rentalRate;
	private int filmLengthInMinutes;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> filmCast;

	public Film() {

	}

	public Film(int id, String title, String description, int releaseYear, String language, int rentalDurationInDays,
			double rentalRate, int rentalLengthInMinutes, double replacementCost, String rating, String specialFeatures,
			List<Actor> filmCast) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.language = language;
		this.rentalDurationInDays = rentalDurationInDays;
		this.rentalRate = rentalRate;
		this.filmLengthInMinutes = rentalLengthInMinutes;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
		this.filmCast = filmCast;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReleaseYear() {
		return releaseYear;
	}

	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}

	public String getLanguageId() {
		return languageId;
	}

	public void setLanguageId(String languageId) {
		this.languageId = languageId;
	}

	public void setLanguage(String language) {
		if (isValidLanguage(language)) {
			this.language = language;
		} else {
			throw new IllegalArgumentException("Invalid language: " + language);
		}
	}

	private boolean isValidLanguage(String language) {
		String[] validLanguages = { "English", "Italian", "Japanese", "Mandarin", "French", "German" };
		return Arrays.asList(validLanguages).contains(language);
	}

	public String getLanguage() {
		return language;
	}

	public int getRentalDurationInDays() {
		return rentalDurationInDays;
	}

	public void setRentalDurationInDays(int rentalDurationInDays) {
		this.rentalDurationInDays = rentalDurationInDays;
	}

	public double getRentalRate() {
		return rentalRate;
	}

	public void setRentalRate(double rentalRate) {
		this.rentalRate = rentalRate;
	}

	public int getFilmLengthInMinutes() {
		return filmLengthInMinutes;
	}

	public void setFilmLengthInMinutes(int filmLengthInMinutes) {
		this.filmLengthInMinutes = filmLengthInMinutes;
	}

	public double getReplacementCost() {
		return replacementCost;
	}

	public void setReplacementCost(double replacementCost) {
		this.replacementCost = replacementCost;
	}

	public String getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = rating;
	}

	public String getSpecialFeatures() {
		return specialFeatures;
	}

	public void setSpecialFeatures(String specialFeatures) {
		this.specialFeatures = specialFeatures;
	}

	public List<Actor> getFilmCast() {
		return filmCast;
	}

	public void setFilmCast(List<Actor> filmCast) {
		this.filmCast = filmCast;
	}

	@Override
	public String toString() {
		return "Film ID: " + id + "|| Title: " + title + ", Description: " + description + ", Release Year: "
				+ releaseYear + ", Language: " + language + ", Rental Duration In Days: " + rentalDurationInDays
				+ ", Rental Rate: $" + rentalRate + ", Film Length In Minutes: " + filmLengthInMinutes
				+ ", Replacement Cost: $" + replacementCost + ", Rating: " + rating + ", Special Features: "
				+ specialFeatures + ", Film's Cast: " + filmCast;
	}

}