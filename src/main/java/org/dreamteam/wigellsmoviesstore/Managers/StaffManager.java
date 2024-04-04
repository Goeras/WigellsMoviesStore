package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;
import org.locationtech.jts.geom.Geometry;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class StaffManager {

    DAOmanager daOmanager = new DAOmanager();


    public List<Staff> filterByName(ObservableList observableList, String name){
        List<Staff> staffList = new ArrayList<>();
        for(Object o : observableList){
            if(o instanceof Staff) {
                Staff s = (Staff) o;
                if (s.getFirstName().equalsIgnoreCase(name) || s.getLastName().equalsIgnoreCase(name)) {
                    staffList.add(s);
                }
            }
        }
        return staffList;
    }

    public Staff searchByName(int id){
        Staff staff = null;
        for(Staff s : CurrentStore.getInstance().getCurrentStore().getStaffList()){
            if(s.getId() == id){
                staff = s;
            }
        }
        return staff;
    }

    public String isActiveStringFromBoolean(Boolean bool){
        if(!bool){
            return "Ej Aktiv";
        }
        else{
            return "Aktiv";
        }
    }


    public boolean createNewStaff(String firstName, String lastName, String eMail, String userName, String phoneNumber, String password, String password2, String address1, String address2, String district, String postalCode, String city, Country country ) {
        // kontrollerar om något nödvändigt fällt ej är ifyllt av användaren. returnerar isf false.
        if (firstName.isEmpty() || lastName.isEmpty() || eMail.isEmpty() || userName.isEmpty() || password.isEmpty() || password2.isEmpty() || address1.isEmpty() || district.isEmpty() || postalCode.isEmpty() || city.isEmpty() || country == null) {
            return false; // Om någon parameter är tom, returnera false
        } else {

            country.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            daOmanager.getCountryDAO().updateCountry(country);

            City newCity = daOmanager.getCityDAO().getCityByName(city); // hämta city från databas om den redan finns.

            if (newCity == null) { // skapa ny city om den inte redan existerar.
                newCity = createNewCity(city, country);
                country.addCity(newCity);
            }

            Address newAddress = createNewAdress(newCity, address1, address2, district, postalCode, phoneNumber);

            Staff newStaff = new Staff();
            newStaff.setFirstName(firstName);
            newStaff.setLastName(lastName);
            newStaff.setEmail(eMail);
            newStaff.setUserName(userName);
            newStaff.setPassword(password);
            newStaff.setAdress(newAddress);
            newStaff.setActive(true);
            newStaff.setStore(CurrentStore.getInstance().getCurrentStore());
            newStaff.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
            daOmanager.getStaffDAO().updateStaff(newStaff);

            return true;
        }
    }

    public City createNewCity(String cityName, Country country){
        City city = new City();
        city.setName(cityName);
        city.setCountry(country);
        city.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        daOmanager.getCityDAO().updateCity(city);

        City cityFromDataBase = daOmanager.getCityDAO().getCityByName(cityName);
        return cityFromDataBase;
    }

    public Address createNewAdress(City city, String address1, String address2, String district, String postalCode, String phoneNumber){
        Address address = new Address();
        address.setCity(city);
        address.setAddress(address1);
        address.setAddress2(address2);
        address.setDistrict(district);
        address.setPostalCode(postalCode);
        address.setLastUpdate(Timestamp.valueOf(LocalDateTime.now()));
        address.setLocation(getDefaultGeometry());
        address.setPhone(phoneNumber);

        daOmanager.getAddressDAO().createAddress(address);

        return daOmanager.getAddressDAO().getAddressByName(address1); // Returnerar addressen från databasen för att få med ID.
    }

    public Geometry getDefaultGeometry() { // Skapar och returnerar default geometry punkt.

        org.locationtech.jts.geom.Coordinate coordinate = new org.locationtech.jts.geom.Coordinate(1.0, 2.0);

        org.locationtech.jts.geom.GeometryFactory geometryFactory = new org.locationtech.jts.geom.GeometryFactory();
        org.locationtech.jts.geom.Point point = geometryFactory.createPoint(coordinate);

        return point;
    }
    public boolean validateUniqueUsername(String username){

            List<Staff> staffList = daOmanager.getStaffDAO().getAllStaffs();
            for (Staff s : staffList) {
                if (s.getUserName().equalsIgnoreCase(username)) {
                    return false;
                }
            }
        return true;
    }

    public boolean validateUniqueEmail(String eMail){
        List<Staff> staffList = daOmanager.getStaffDAO().getAllStaffs();
        for(Staff s : staffList){
            if(s.getEmail().equalsIgnoreCase(eMail)){
                return false;
            }
        }
        return true;
    }

    public List<Country> getAllCountriesAsList(){
        List<Country> countryList = daOmanager.getCountryDAO().getAllCountries();
        return countryList;
    }

}
