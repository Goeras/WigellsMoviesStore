package org.dreamteam.wigellsmoviesstore;

import org.dreamteam.wigellsmoviesstore.DAO.CustomerDAO;
import org.dreamteam.wigellsmoviesstore.DAO.StaffDAO;
import org.dreamteam.wigellsmoviesstore.Entitys.Customer;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;

public class CurrentCustomer {

    private static CurrentCustomer instance;
    private Customer currentCustomer;
    private CustomerDAO customerDAO = new CustomerDAO();

    private CurrentCustomer(){}

    public static synchronized CurrentCustomer getInstance(){
        if(instance == null){
            instance = new CurrentCustomer();
        }
        return instance;
    }

    public Customer getCurrentCustomer() {
        return currentCustomer;
    }
    public void setCurrentCustomer(Customer customer) {
        this.currentCustomer = customer;
    }
    public void updateCurrentCustomer(){
        this.currentCustomer = customerDAO.readCustomer(currentCustomer.getId());
    }
}
