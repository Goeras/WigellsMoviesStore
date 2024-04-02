package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "inventory")
public class Inventory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "inventory_id")
    private int id;

    /*@Column(name = "film_id")
    private int film_id;*/

    /*@Column(name = "store_id")
    private int store_id;*/

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id")
    private Film film;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "store_id")
    private Store store;

    public Inventory() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    /*public int getFilm_id() {
        return film_id;
    }

    public void setFilm_id(int film_id) {
        this.film_id = film_id;
    }*/

    /*public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }*/

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}

