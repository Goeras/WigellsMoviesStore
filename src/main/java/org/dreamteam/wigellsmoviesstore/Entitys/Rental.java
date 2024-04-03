package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;


@Entity
@Table(name = "rental")
public class Rental {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "rental_id")
    private int rentalId;

    @Column(name = "rental_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date rentalDate;

    @ManyToOne
    @JoinColumn(name = "inventory_id")
    private Inventory inventory;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @Column(name = "return_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date returnDate;

    @ManyToOne
    @JoinColumn(name = "staff_id")
    private Staff staff;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "store_id")
    private Store store;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "film_id")
    private Film film;

    public Rental() {
    }

    public int getRentalId() {
        return rentalId;
    }

    public void setRentalId(int rentalId) {
        this.rentalId = rentalId;
    }

    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }


    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Film getFilm() {
        return film;
    }

    public void setFilm(Film film) {
        this.film = film;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }


}
