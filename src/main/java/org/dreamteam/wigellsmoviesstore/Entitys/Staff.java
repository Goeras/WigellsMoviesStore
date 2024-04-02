package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.sql.Blob;
import java.sql.Timestamp;

@Entity
@Table(name = "staff")
public class Staff {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "staff_id")
    private int id;

    @Column(name = "first_name", length = 45)
    private String firstName;

    @Column(name = "last_name", length = 45)
    private String lastName;

    /*@Column(name = "address_id")
    private int addressId;*/

    @Lob
    @Column(name = "picture")
    private Blob picture;

    @Column(name = "email", length = 50)
    private String email;

    /*@Column(name = "store_id")
    private int storeId;*/

    @Column(name = "active")
    private int active;

    @Column(name = "username", length = 16)
    private String userName;

    @Column(name = "password", length = 40)
    private String password;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address adress;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "store_id")
    private Store store;

    public Staff() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    /*public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }*/

    public Blob getPicture() {
        return picture;
    }

    public void setPicture(Blob picture) {
        this.picture = picture;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /*public int getStoreId() {
        return storeId;
    }

    public void setStoreId(int storeId) {
        this.storeId = storeId;
    }*/

    public int getActive() {
        return active;
    }

    public void setActive(int active) {
        this.active = active;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Timestamp getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(Timestamp lastUpdate) {
        this.lastUpdate = lastUpdate;
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
