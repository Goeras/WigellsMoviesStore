package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "country")
public class Country {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "country_id")
    private int id;

    @Column(name = "country", length = 50)
    private String name;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    public Country() {

    }
    public Country(int id, String name, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int countryId) {
        this.id = countryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String countryName) {
        this.name = countryName;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }
}
