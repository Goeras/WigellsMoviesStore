package org.dreamteam.wigellsmoviesstore.Entitys;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "category")
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id", columnDefinition = "TINYINT UNSIGNED")
    private int Id;

    @Column(name = "name", length = 25)
    private String name;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "film_category",
            joinColumns = {@JoinColumn(name = "category_id")},
            inverseJoinColumns = {@JoinColumn(name = "film_id")})
    private List<Film> filmList = new ArrayList<>();

    public Category() {
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Film> getFilmList() {
        return filmList;
    }

    public void setFilmList(List<Film> filmList) {
        this.filmList = filmList;
    }

    @Override
    public String toString() {
        return name;
    }
}
