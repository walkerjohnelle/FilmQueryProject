package com.skilldistillery.filmquery.entities;

import java.util.List;
import java.util.Objects;

public class Film {
	private int id;
	private String title;
	private String description;
	private int releaseYear;
	private int languageId;
	private int rentalDurationInDays;
	private double rentalRate;
	private int rentalLengthInMinutes;
	private double replacementCost;
	private String rating;
	private String specialFeatures;
	private List<Actor> filmCast;

	public Film() {

	}

	public Film(int id, String title, String description, int releaseYear, int languageId, int rentalDurationInDays,
			double rentalRate, int rentalLengthInMinutes, double replacementCost, String rating,
			String specialFeatures) {
		super();
		this.id = id;
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		this.languageId = languageId;
		this.rentalDurationInDays = rentalDurationInDays;
		this.rentalRate = rentalRate;
		this.rentalLengthInMinutes = rentalLengthInMinutes;
		this.replacementCost = replacementCost;
		this.rating = rating;
		this.specialFeatures = specialFeatures;
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

	public int getLanguageId() {
		return languageId;
	}

	public void setLanguageId(int languageId) {
		this.languageId = languageId;
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

	public int getRentalLengthInMinutes() {
		return rentalLengthInMinutes;
	}

	public void setRentalLengthInMinutes(int rentalLengthInMinutes) {
		this.rentalLengthInMinutes = rentalLengthInMinutes;
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
	public int hashCode() {
		return Objects.hash(description, filmCast, id, languageId, rating, releaseYear, rentalDurationInDays,
				rentalLengthInMinutes, rentalRate, replacementCost, specialFeatures, title);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Film other = (Film) obj;
		return Objects.equals(description, other.description) && Objects.equals(filmCast, other.filmCast)
				&& id == other.id && languageId == other.languageId && Objects.equals(rating, other.rating)
				&& releaseYear == other.releaseYear && rentalDurationInDays == other.rentalDurationInDays
				&& rentalLengthInMinutes == other.rentalLengthInMinutes
				&& Double.doubleToLongBits(rentalRate) == Double.doubleToLongBits(other.rentalRate)
				&& Double.doubleToLongBits(replacementCost) == Double.doubleToLongBits(other.replacementCost)
				&& Objects.equals(specialFeatures, other.specialFeatures) && Objects.equals(title, other.title);
	}

	@Override
	public String toString() {
		return "Film [id=" + id + ", title=" + title + ", description=" + description + ", releaseYear=" + releaseYear
				+ ", languageId=" + languageId + ", rentalDurationInDays=" + rentalDurationInDays + ", rentalRate="
				+ rentalRate + ", rentalLengthInMinutes=" + rentalLengthInMinutes + ", replacementCost="
				+ replacementCost + ", rating=" + rating + ", specialFeatures=" + specialFeatures + ", filmCast="
				+ filmCast + "]";
	}

}