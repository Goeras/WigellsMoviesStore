package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.CurrentCustomer;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.locationtech.jts.geom.Geometry;

import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.List;

public class CustomerManager {

    private Country country;
    private Store store;
    private CurrentStore currentStore;
    private DAOmanager daoManager = new DAOmanager();

    public void newCustomer(String firstName, String lastName, String email, String phoneNumber, String address1, String address2, String district, String postalCode, String city, Country country) {

        country.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        daoManager.getCountryDAO().updateCountry(country);

        City newCity = daoManager.getCityDAO().getCityByName(city);

        if(newCity == null){
            newCity = new City();
            newCity.setName(city);
            newCity.setCountry(country);
            newCity.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            daoManager.getCityDAO().updateCity(newCity);
        }
        City dataBaseCity = daoManager.getCityDAO().getCityByName(city);   // vet ej om detta hjälper än men hämtar från dbs
        country.addCity(dataBaseCity);

        Address newAddress = new Address();
        newAddress.setCity(dataBaseCity);
        newAddress.setAddress(address1);
        newAddress.setAddress2(address2);
        newAddress.setDistrict(district);
        newAddress.setPostalCode(postalCode);
        newAddress.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        newAddress.setLocation(getDefaultGeometry());
        newAddress.setPhone(phoneNumber);

        daoManager.getAddressDAO().createAddress(newAddress);
        Address dataBaseAddress = daoManager.getAddressDAO().getAddressByName(address1);

        Store store = currentStore.getInstance().getCurrentStore();
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setAdress(dataBaseAddress);
        newCustomer.setActive(true);
        newCustomer.setStore(store);
        newCustomer.setCreateDate(new Date(System.currentTimeMillis()));
        newCustomer.setLast_update(Timestamp.valueOf(LocalDateTime.now()));
        newCustomer.setStore(store);
        daoManager.getCustomerDAO().updateCustomer(newCustomer);

    }
    public void updateCustomer(String firstName, String lastName, String email, String phoneNumber, String address1, String address2, String district, String postalCode, String city, Country country, boolean active) {

        Customer updateCustomer = CurrentCustomer.getInstance().getCurrentCustomer();

        Address updateAddress = updateCustomer.getAdress();


        City newCity = daoManager.getCityDAO().getCityByName(city);

        if(newCity == null){
            newCity = new City();
            newCity.setName(city);
            newCity.setCountry(country);
            newCity.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            daoManager.getCityDAO().updateCity(newCity);
        }
        City dataBaseCity = daoManager.getCityDAO().getCityByName(city);
        country.addCity(dataBaseCity);

        if(!updateAddress.getAddress().equals(address1)){
            Address newAddress = new Address();
            newAddress.setCity(dataBaseCity);
            newAddress.setAddress(address1);
            newAddress.setAddress2(address2);
            newAddress.setDistrict(district);
            newAddress.setPostalCode(postalCode);
            newAddress.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            newAddress.setLocation(getDefaultGeometry());
            newAddress.setPhone(phoneNumber);

            daoManager.getAddressDAO().createAddress(newAddress);
        }

        Address dataBaseAddress = daoManager.getAddressDAO().getAddressByName(address1);
        updateCustomer.setFirstName(firstName);
        updateCustomer.setLastName(lastName);
        updateCustomer.setEmail(email);
        updateCustomer.setAdress(dataBaseAddress);
        updateCustomer.setActive(active);
        updateCustomer.setCreateDate(new Date(System.currentTimeMillis()));
        updateCustomer.setStore(CurrentStore.getInstance().getCurrentStore());
        updateCustomer.setLast_update(Timestamp.valueOf(LocalDateTime.now()));

        daoManager.getCustomerDAO().updateCustomer(updateCustomer);
    }
    public void updateCustomerValues(String firstName,String lastName,String email,Address dataBaseAddress, boolean active, Customer updateCustomer){



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
    public Customer getCustomer(String custId){
        int customerId = IoConverter.stringToInteger(custId);
        Customer customer = daoManager.getCustomerDAO().readCustomer(customerId);
        return customer;
    }
    public ObservableList<Customer> getAllCustomer(){
        return FXCollections.observableList(daoManager.getCustomerDAO().readAllCustomers());
    }
    public Geometry getDefaultGeometry() { // Skapar och returnerar default geometry punkt.

        org.locationtech.jts.geom.Coordinate coordinate = new org.locationtech.jts.geom.Coordinate(1.0, 2.0);

        org.locationtech.jts.geom.GeometryFactory geometryFactory = new org.locationtech.jts.geom.GeometryFactory();
        org.locationtech.jts.geom.Point point = geometryFactory.createPoint(coordinate);

        return point;
    }
    public String isActiveStringFromBoolean(Boolean bool){
        if(!bool){
            return "Ej Aktiv";
        }
        else{
            return "Aktiv";
        }
    }
    public boolean isActiveBooleanFromString(String string){
        return string.equals("Aktiv");
    }
}