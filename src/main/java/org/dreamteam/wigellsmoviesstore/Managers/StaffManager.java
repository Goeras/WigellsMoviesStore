package org.dreamteam.wigellsmoviesstore.Managers;

import javafx.collections.ObservableList;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public boolean createNewStaff(String firstName, String lastName, String eMail, String userName, String password, String password2, String address1, String address2, String district, String postalCode, String city, Country country ) {
        if (firstName.isEmpty() || lastName.isEmpty() || eMail.isEmpty() || userName.isEmpty() || password.isEmpty() || password2.isEmpty() || address1.isEmpty() || district.isEmpty() || postalCode.isEmpty() || city.isEmpty() || country == null) {
            return false; // Om någon parameter är tom, returnera false
        } else {
            // Staff
            Staff newStaff = new Staff();
            newStaff.setFirstName(firstName);
            newStaff.setLastName(lastName);
            newStaff.setEmail(eMail);
            newStaff.setUserName(userName);
            newStaff.setPassword(password);

            // Adress
            Address newAddress = new Address();
            newAddress.setAddress(address1);
            if(!address2.isEmpty()){
                newAddress.setAddress2(address2);
            }
            newAddress.setDistrict(district);
            newAddress.setPostalCode(postalCode);
            newStaff.setAdress(newAddress);

            City newCity = daOmanager.getCityDAO().getCityByName(city);
            if(newCity == null){
                newCity = new City();
                newCity.setName(city);
                newCity.setCountry(country);
            }

            newAddress.setCity(newCity);

            daOmanager.getStaffDAO().createStaff(newStaff);

            return true; // Om ingen parameter är tom, returnera true
        }
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

    // Metod för att konvertera en lista av länder till en lista av ländernas namn
    public List<String> convertCountryListToStringList(List<Country> countryList) {
        return countryList.stream()
                .map(Country::getName) // Hämta namnet för varje land
                .collect(Collectors.toList()); // Samla in namnen i en lista
    }

}
