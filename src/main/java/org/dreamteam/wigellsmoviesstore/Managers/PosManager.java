package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;
import org.dreamteam.wigellsmoviesstore.IoConverter;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PosManager {
    DAOmanager daOmanager = new DAOmanager();

    public LocalDateTime calculateReturnDate(int duration){
        LocalDateTime localDate = LocalDateTime.now();
        LocalDateTime returnDate = LocalDateTime.now();
        returnDate = localDate.plusDays(duration);
        return returnDate;
    }
    public ObservableList addFilmToCart(ObservableList<Film> filmList, String id){
        int filmid = IoConverter.stringToInteger(id);
        Film film = daOmanager.getFilmDAO().getFilmById(filmid);
        filmList.add(film);
        return filmList;
    }
    public String[] searchFilm(String id){
        int filmId = IoConverter.stringToInteger(id);
        Film film = daOmanager.getFilmDAO().getFilmById(filmId);
        String[] strings = {Integer.toString(film.getFilmId()), film.getTitle()};
        return strings;
    }
    public String[] searchCustomer(String id){
        int custId = IoConverter.stringToInteger(id);
        Customer customer = daOmanager.getCustomerDAO().readCustomer(custId);
        String[] info = {Integer.toString(customer.getId()), (customer.getFirstName() + " " + customer.getLastName())};
        return info;
    }
    public Rental newRental(int filmId, Customer customer, Staff staff){
        List<Inventory> inventories = daOmanager.getInventoryDAO().getInventoryByFilmAndStore(filmId, CurrentStore.getInstance().getCurrentStore().getId());
        Inventory inventory = inventories.get(0);
        Rental rental = new Rental();
        rental.setInventory(inventory);
        rental.setCustomer(customer);
        rental.setStaff(staff);
        rental.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        Date rentalDate = new Date(System.currentTimeMillis());
        rental.setRentalDate(rentalDate);
        daOmanager.getRentalDAO().createRental(rental);

        return rental;
    }
    public void confirmRentals(ObservableList<Film> cart, String custId, int staffId) {
        int customerId = IoConverter.stringToInteger(custId);
        Customer customer = daOmanager.getCustomerDAO().readCustomer(customerId);
        Staff staff = daOmanager.getStaffDAO().getStaffById(staffId);

        for(Film film : cart){
            Rental rental = newRental(film.getFilmId(), customer, staff);
            System.out.println(rental.getRentalId());
            newPayment(customer, staff, rental, film.getRentalRate());
        }
    }
    public void newPayment(Customer customer, Staff staff, Rental rental, double amount){
        Payment payment = new Payment();
        payment.setCustomer(customer);
        payment.setAmount(amount);
        payment.setStaff(staff);
        payment.setRental(rental);
        payment.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        daOmanager.getPaymentDAO().createPayment(payment);
    }
    public void replaceFilm(Rental rental, String staffIdString){
        int staffId = IoConverter.stringToInteger(staffIdString);
        Staff staff = daOmanager.getStaffDAO().getStaffById(staffId);
        Film film = rental.getInventory().getFilm();
        double amount = film.getReplacementCost();
        Customer customer = rental.getCustomer();
        newPayment(customer, staff, rental, amount);
    }
    public ObservableList<Rental> getObservableRentals(String custId){
        int customerId = IoConverter.stringToInteger(custId);
        Customer customer = daOmanager.getCustomerDAO().readCustomer(customerId);
        List<Rental> allRentals =  customer.getRentals();
        List<Rental> result = new ArrayList<>();
        for(Rental rental : allRentals){
            if(rental.getReturnDate() == null){
                rental.setReturnDate(new Date(System.currentTimeMillis()));
                result.add(rental);
            }
        }
        ObservableList<Rental> resultList = FXCollections.observableList(result);
        return resultList;
    }
    public void returnRental(Rental rental){
        daOmanager.getRentalDAO().updateRental(rental);
    }
    public boolean checkDate(Rental rental){
        boolean late = false;
        Date rentalDate = rental.getRentalDate();
        LocalDateTime rentalLocalDateTime = rentalDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
        long duration = rental.getInventory().getFilm().getRentalDuration();

        if(!rentalLocalDateTime.plusDays(duration).isAfter(LocalDateTime.now())){
            late = true;
        }
        return late;
    }
    public ObservableList<Staff> getStaffList(){
        List<Staff> staffList = daOmanager.getStaffDAO().getAllStaffs();
        ObservableList<Staff> staffs = FXCollections.observableList(staffList);
        return staffs;
    }
    public void payReturnFees(Customer customer, Staff staff, Rental rental, String amountString){
        double amount = Double.parseDouble(amountString);
        if(amount > 0) {
            newPayment(customer, staff, rental, amount);
        }
    }
}
