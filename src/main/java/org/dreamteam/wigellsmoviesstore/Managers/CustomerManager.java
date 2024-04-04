package org.dreamteam.wigellsmoviesstore.Managers;

import org.dreamteam.wigellsmoviesstore.DAO.CountryDAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Address;
import org.dreamteam.wigellsmoviesstore.Entitys.City;
import org.dreamteam.wigellsmoviesstore.Entitys.Country;
import org.dreamteam.wigellsmoviesstore.Entitys.Customer;

import java.sql.Date;
import java.sql.Timestamp;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class CustomerManager {
    Customer newCustomer = new Customer();
    Address newAddress = new Address();
    City newCity = new City();
    Country newCountry = new Country();
    CountryDAO countryDAO = new CountryDAO();

    public void newCustomer(String firstName, String lastName, String email, String phone, String address1, String address2, String district, String postalcode, String city, String country){


        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setActive(true);
        newCustomer.setCreateDate(new Date(System.currentTimeMillis()));
        newCustomer.setLast_update(Timestamp.valueOf(LocalDateTime.now()));

        newAddress.setAddress(address1);
        newAddress.setAddress(address2);
        newAddress.setDistrict(district);
        newAddress.setPostalCode(postalcode);
        newAddress.setPhone(phone);
        //newAddress.setLocation();
        newAddress.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        //Gör en metod som söker på land och hämtar country id för att lägga till i en ny stad som läggs in.
        int countryId = getCountryId(country);
        //newCity.setCountry(countryId);
        newCity.setName(city);
        newCity.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));

        //bör länderna hämtas och visas som en lista?
        //lägga till en stad med country id?

        //newCity.setCountry();

        newCountry.setName(country);
        newCountry.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));


    }
    public List<Country> getCountryList(){

        List<Country> countryList = countryDAO.getAllCountries();

        return countryList;
    }
    public int getCountryId(String country){

        List<Country> countryList = countryDAO.getAllCountries();

        for (Country currentCountry : countryList) {
            if (currentCountry.getName().equalsIgnoreCase(country)) {
                return currentCountry.getId();
            }
        }
        // Returnera -1 om landet inte hittades
        return -1;
    }
    public void citySearch(){
        //metod som gör en query efter land och skickar tillbaks country id?
    }
}