package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "actor")
public class Actor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "actor_id", columnDefinition = "SMALLINT UNSIGNED")
    private int Id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_actor",
    joinColumns = {@JoinColumn(name = "actor_id")},
    inverseJoinColumns = {@JoinColumn(name = "film_id")})
    private List<Film> films = new ArrayList<>();

    // Constructor
    public Actor() {
    }

    // Getters & Setters

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Film> getFilms() {
        return films;
    }

    public void setFilms(List<Film> films) {
        this.films = films;
    }

    public void addFilm(Film film) {
        this.films.add(film);
    }

    public void removeFilmFromList(Film film){
        this.films.remove(film);
    }

    @Override
    public String toString() {
        return firstName.toLowerCase() + " " + lastName.toLowerCase();
    }
}
