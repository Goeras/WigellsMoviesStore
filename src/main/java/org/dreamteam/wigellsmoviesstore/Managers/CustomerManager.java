package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.CountryDAO;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;
import org.dreamteam.wigellsmoviesstore.IoConverter;

import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {

    private Country country;
    private Store store;
    private CurrentStore currentStore;
    private DAOmanager daoManager = new DAOmanager();

    public void newCustomer(String firstName, String lastName, String email, String phone, String address1, String address2, String district, String postalcode, String city, Country country){

        // hämta adress id ? eller? list lost
        Address newAddress = new Address();
        newAddress.setAddress(address1);
        if(!address2.isEmpty()){
            newAddress.setAddress2(address2);
        }
        newAddress.setDistrict(district);
        newAddress.setPostalCode(postalcode);
        newAddress.setPhone(phone);
        newAddress.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        Store store = currentStore.getInstance().getCurrentStore();

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setActive(true);
        newCustomer.setCreateDate(new Date(System.currentTimeMillis()));
        newCustomer.setLast_update(Timestamp.valueOf(LocalDateTime.now()));
        newCustomer.setStore(store);


        City newCity = daoManager.getCityDAO().getCityByName(city);
        if(newCity == null){
            newCity = new City();
            newCity.setName(city);
            newCity.setCountry(country);
            newCity.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        }

        newAddress.setCity(newCity);

        daoManager.getCustomerDAO().createCustomer(newCustomer);
    }
    public List<Country> getCountryList(){

        List<Country> countryList = daoManager.getCountryDAO().getAllCountries();

        return countryList;
    }
    public int getCountryId(String country){

        List<Country> countryList = daoManager.getCountryDAO().getAllCountries();

        for (Country currentCountry : countryList) {
            if (currentCountry.getName().equalsIgnoreCase(country)) {
                return currentCountry.getId();
            }
        }
        // Returnera -1 om landet inte hittades
        return -1;
    }
    public boolean validateUniqueEmail(String email){
        List<Customer> customerList = daoManager.getCustomerDAO().readAllCustomers();
        for(Customer custSearch : customerList){
            if(custSearch.getEmail().equalsIgnoreCase(email)){
                return false;
            }
        }
        return true;
    }
    public String[] getCustomerInfo(String custId){
        int customerId = IoConverter.stringToInteger(custId);
        String[] info = new String[11];
        Customer customer = daoManager.getCustomerDAO().readCustomer(customerId);
        info[0] = customer.getFirstName();
        info[1] = customer.getLastName();
        info[2] = customer.getEmail();
        info[3] = Boolean.toString(customer.getActive());
        info[4] = customer.getCreateDate().toString();
        info[5] = customer.getAdress().getAddress();
        info[6] = customer.getAdress().getAddress2();
        info[7] = customer.getAdress().getDistrict();
        info[8] = customer.getAdress().getPostalCode();
        info[9] = customer.getAdress().getCity().getName();
        info[10] =customer.getAdress().getCity().getCountry().getName();
        return info;
    }
    public ObservableList<Rental> getCustomerRentals(String custId){
        int customerId = IoConverter.stringToInteger(custId);
        Customer customer = daoManager.getCustomerDAO().readCustomer(customerId);
        ObservableList<Rental> rentals = FXCollections.observableList(customer.getRentals());
        return rentals;
    }

}