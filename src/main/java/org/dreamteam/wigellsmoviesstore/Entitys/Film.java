package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;
import org.dreamteam.wigellsmoviesstore.Enums.FilmRating;

import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id")
    private int filmId;

    @Column(name = "title", length = 128)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year")
    private int releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration")
    private int rentalDuration;

    @Column(name = "rental_rate")
    private double rentalRate;

    @Column(name = "length")
    private int length;

    @Column(name = "replacement_cost")
    private double replacementCost;

    @Enumerated(EnumType.STRING)
    @Column(name = "rating")
    private FilmRating rating;

    @ElementCollection
    @CollectionTable(name = "film_special_features", joinColumns = @JoinColumn(name = "film_id"))
    @Column(name = "special_feature")
    private Set<String> specialFeatures;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Film() {

    }

    public Film(int filmId, String title, String description, int releaseYear, Language language, Language originalLanguage, int rentalDuration, double rentalRate, int length, double replacementCost, FilmRating rating, Set<String> specialFeatures, Timestamp lastUpdate) {
        this.filmId = filmId;
        this.title = title;
        this.description = description;
        this.releaseYear = releaseYear;
        this.language = language;
        this.originalLanguage = originalLanguage;
        this.rentalDuration = rentalDuration;
        this.rentalRate = rentalRate;
        this.length = length;
        this.replacementCost = replacementCost;
        this.rating = rating;
        this.specialFeatures = specialFeatures;
        this.lastUpdate = lastUpdate;
    }

    public int getFilmId() {
        return filmId;
    }

    public void setFilmId(int filmId) {
        this.filmId = filmId;
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

    public void setReleaseYear(Short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    public Language getOriginalLanguageId() {
        return originalLanguage;
    }

    public void setOriginalLanguageId(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }


    public int getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Short rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public int getLength() {
        return length;
    }

    public void setLength(Short length) {
        this.length = length;
    }

    public double getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(double replacementCost) {
        this.replacementCost = replacementCost;
    }

    public FilmRating getRating() {
        return rating;
    }

    public void setRating(FilmRating rating) {
        this.rating = rating;
    }

    public Set<String> getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(Set<String> specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
