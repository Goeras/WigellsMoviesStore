package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "customer_id")
    private int id;

    @Column(name = "store_id")
    private int store_id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    @Column(name = "email", length = 50)
    private String email;

    @Column(name = "address_id")
    private int addressId;

    @Column(name = "active")
    private int active;

    @Column(name = "create_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createDate;

    @Column(name = "last_update")
    private Timestamp last_update;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address adress;



    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "store_id")
    private Store store;

    public Customer() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Timestamp getLast_update() {
        return last_update;
    }

    public void setLast_update(Timestamp last_update) {
        this.last_update = last_update;
    }

    public Address getAdress() {
        return adress;
    }

    public void setAdress(Address adress) {
        this.adress = adress;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
