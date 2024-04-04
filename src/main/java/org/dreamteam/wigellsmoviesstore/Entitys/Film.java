package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "film")
public class Film {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "film_id", columnDefinition = "SMALLINT UNSIGNED")
    private int filmId;

    @Column(name = "title", length = 128)
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "release_year", columnDefinition = "YEAR")
    private short releaseYear;

    @ManyToOne
    @JoinColumn(name = "language_id")
    private Language language;

    @ManyToOne
    @JoinColumn(name = "original_language_id")
    private Language originalLanguage;

    @Column(name = "rental_duration", columnDefinition = "TINYINT UNSIGNED")
    private byte rentalDuration;

    @Column(name = "rental_rate", columnDefinition = "DECIMAL(4,2) NOT NULL DEFAULT 4.99")
    private double rentalRate;

    @Column(name = "length",columnDefinition = "SMALLINT UNSIGNED")
    private short length;

    @Column(name = "replacement_cost", columnDefinition = "DECIMAL(5,2) NOT NULL DEFAULT 19.99")
    private double replacementCost;

    @Column(name = "rating", columnDefinition = "ENUM('G','PG','PG-13','R','NC-17') DEFAULT 'G'")
    private String rating;


    @Column(name = "special_features",columnDefinition = "SET('Trailers','Commentaries','Deleted Scenes','Behind the Scenes')")
    private String specialFeatures;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_category",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")})
    private List<Category> categoryList = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_actor",
            joinColumns = {@JoinColumn(name = "film_id")},
            inverseJoinColumns = {@JoinColumn(name = "actor_id")})
    private List<Actor> actors = new ArrayList<>();


    @OneToMany(mappedBy = "film")
    private List<Inventory> inventories;

    public Film() {

    }

    public Film(short filmId, String title, String description, short releaseYear, Language language, Language originalLanguage, byte rentalDuration, double rentalRate, short length, double replacementCost, String rating, String specialFeatures, Timestamp lastUpdate, List<Category> categoryList) {
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
        this.categoryList = categoryList;
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

    public void setReleaseYear(short releaseYear) {
        this.releaseYear = releaseYear;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }


    public Language getOriginalLanguage() {
        return originalLanguage;
    }

    public void setOriginalLanguage(Language originalLanguage) {
        this.originalLanguage = originalLanguage;
    }


    public byte getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(byte rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
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

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<Category> categoryList) {
        this.categoryList = categoryList;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }
}
