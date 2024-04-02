package org.dreamteam.wigellsmoviesstore.DAO;

import org.dreamteam.wigellsmoviesstore.Entitys.Customer;

public class DAOmanager {

    private ActorDAO actorDao = new ActorDAO();
    private AddressDAO addressDAO = new AddressDAO();
    private CategoryDAO categoryDAO = new CategoryDAO();
    private CityDAO cityDAO = new CityDAO();
    private CountryDAO countryDAO = new CountryDAO();
    private CustomerDAO customerDAO = new CustomerDAO();
    private FilmDAO filmDAO = new FilmDAO();
    private InventoryDAO inventoryDAO = new InventoryDAO();
    private LanguageDAO languageDAO = new LanguageDAO();
    private PaymentDAO paymentDAO = new PaymentDAO();
    private RentalDAO rentalDAO = new RentalDAO();
    private StaffDAO staffDAO = new StaffDAO();
    private StoreDAO storeDAO = new StoreDAO();

    public ActorDAO getActorDao() {
        return actorDao;
    }

    public AddressDAO getAddressDAO() {
        return addressDAO;
    }

    public CategoryDAO getCategoryDAO() {
        return categoryDAO;
    }

    public CityDAO getCityDAO() {
        return cityDAO;
    }

    public CountryDAO getCountryDAO() {
        return countryDAO;
    }

    public CustomerDAO getCustomerDAO() {
        return customerDAO;
    }

    public FilmDAO getFilmDAO() {
        return filmDAO;
    }

    public InventoryDAO getInventoryDAO() {
        return inventoryDAO;
    }

    public LanguageDAO getLanguageDAO() {
        return languageDAO;
    }

    public PaymentDAO getPaymentDAO() {
        return paymentDAO;
    }

    public RentalDAO getRentalDAO() {
        return rentalDAO;
    }

    public StaffDAO getStaffDAO() {
        return staffDAO;
    }

    public StoreDAO getStoreDAO() {
        return storeDAO;
    }
}
