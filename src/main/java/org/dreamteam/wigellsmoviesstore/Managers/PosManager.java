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

    public ObservableList addFilmToCart(ObservableList<Inventory> filmList, String id){
        int inventorIid = IoConverter.stringToInteger(id);
        Inventory inventory = daOmanager.getInventoryDAO().readInventory(inventorIid);
        if(inventory.getStore().getId() == CurrentStore.getInstance().getCurrentStore().getId()){
            filmList.add(inventory);
        }
        return filmList;
    }
    public String[] searchFilm(String id){
        int inventoryId = IoConverter.stringToInteger(id);
        Inventory inventory = daOmanager.getInventoryDAO().readInventory(inventoryId);
        if(inventory.getStore().getId() == CurrentStore.getInstance().getCurrentStore().getId()){
        Film film = inventory.getFilm();
        String[] strings = {Integer.toString(film.getFilmId()), film.getTitle()};
            return strings;
        }
        else{
            String[] strings = new String[2];
            strings[0] = "Okänd film";
            strings[1] = "Okänd film";
            return strings;
        }
    }
    public String[] searchCustomer(String id){
        int custId = IoConverter.stringToInteger(id);
        Customer customer = daOmanager.getCustomerDAO().readCustomer(custId);
        String[] info = {Integer.toString(customer.getId()), (customer.getFirstName() + " " + customer.getLastName())};
        return info;
    }
    public Rental newRental(Inventory inventory, Customer customer, Staff staff){
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
    public void confirmRentals(ObservableList<Inventory> cart, String custId, int staffId) {
        int customerId = IoConverter.stringToInteger(custId);
        Customer customer = daOmanager.getCustomerDAO().readCustomer(customerId);
        Staff staff = daOmanager.getStaffDAO().getStaffById(staffId);
        if(cart.size() > 0) {
            for (Inventory inventory : cart) {
                Rental rental = newRental(inventory, customer, staff);
                newPayment(customer, staff, rental, inventory.getFilm().getRentalRate());
            }
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
    public void replaceFilm(Rental rental){
        rental.setReturnDate(new Date(System.currentTimeMillis()));
        daOmanager.getRentalDAO().updateRental(rental);
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
        List<Staff> staffList = CurrentStore.getInstance().getCurrentStore().getStaffList();
        ObservableList<Staff> staffs = FXCollections.observableList(staffList);
        return staffs;
    }

    public void payReturnFees(Customer customer, Staff staff, Rental rental, String amountString){
        double amount = Double.parseDouble(amountString);
        if(amount > 0) {
            newPayment(customer, staff, rental, amount);
        }
    }
    public ObservableList<Payment> seePayments(LocalDateTime from, LocalDateTime to){
        Timestamp fromStamp = Timestamp.valueOf(from);
        Timestamp toStamp = Timestamp.valueOf(to);
        List<Payment> resultList = daOmanager.getPaymentDAO().getInventoryByFilmAndStore(fromStamp, toStamp);
        return FXCollections.observableList(resultList);
    }
    public ObservableList<Rental> seeRentals() {
        List<Rental> resultList = daOmanager.getRentalDAO().getInventoryByFilmAndStore();
        return FXCollections.observableList(resultList);
    }
}
