package org.dreamteam.wigellsmoviesstore.Entitys;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "store_id", columnDefinition = "TINYINT UNSIGNED")
    private int id;

    @Column(name = "manager_staff_id")
    private byte managerStaffId;

    @Column(name = "last_update")
    private Timestamp lastUpdate;

    @ManyToOne(cascade = { CascadeType.MERGE, CascadeType.PERSIST})
    @JoinColumn(name = "address_id")
    private Address adress;

    @OneToMany(mappedBy = "store", fetch = FetchType.EAGER)
    private List<Staff> staffList;

    @OneToMany(mappedBy = "store")
    private List<Rental> rentals;

    public Store() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public byte getManagerStaffId() {
        return managerStaffId;
    }

    public void setManagerStaffId(byte managerStaffId) {
        this.managerStaffId = managerStaffId;
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


    @Override
    public String toString() {
        return "Store " + this.id;
    }

    public List<Staff> getStaffList() {
        return staffList;
    }

    public void setStaffList(List<Staff> staffList) {
        this.staffList = staffList;
    }

    public List<Rental> getRentals() {
        return rentals;
    }

    public void setRentals(List<Rental> rentals) {
        this.rentals = rentals;
    }
}
