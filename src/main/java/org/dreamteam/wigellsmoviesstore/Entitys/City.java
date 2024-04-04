package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id", columnDefinition = "SMALLINT UNSIGNED")
    private int id;

    @Column(name = "city", length = 50)
    private String name;

    @ManyToOne(fetch = FetchType.EAGER/*cascade = { CascadeType.MERGE, CascadeType.PERSIST }*/)
    @JoinColumn(name = "country_id")
    private Country country;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @OneToMany(mappedBy = "city")
    private List<Address> addresses;





    public City() {

    }
    public City(int id, String name, Country country, Timestamp lastUpdate) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.lastUpdate = lastUpdate;
    }

    public int getId() {
        return id;
    }

    public void setId(int cityId) {
        this.id = cityId;
    }

    public String getName() {
        return name;
    }

    public void setName(String cityName) {
        this.name = cityName;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public List<Address> getAddresses() {
        return addresses;
    }

    public void setAddresses(List<Address> addresses) {
        this.addresses = addresses;
    }

}