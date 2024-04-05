package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Customer;
import org.dreamteam.wigellsmoviesstore.Entitys.Rental;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.Managers.CustomerManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CustomerViewController {
    private int idHolder = 0;
    @FXML
    private Label topLabel;
    ViewManager viewManager;
    CustomerManager customerManager;
    @FXML
    private TextField searchCustomer;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label email;
    @FXML
    private Label active;
    @FXML
    private Label createDate;
    @FXML
    private Label address1;
    @FXML
    private Label address2;
    @FXML
    private Label disctrict;
    @FXML
    private Label postalCode;
    @FXML
    private Label city;
    @FXML
    private Label country;
    ObservableList<Rental> rentalHistory;
    @FXML
    private TableView<Rental> rentalHistoryTable;
    @FXML
    private TableColumn<Rental, Integer> rentalId;
    @FXML
    private TableColumn<Rental, String> rentalDate;
    @FXML
    private TableColumn<Rental, String> returnDate;
    @FXML
    private TableColumn<Rental, String> store;
    @FXML
    private TableColumn<Rental, Integer> filmId;
    @FXML
    private TableColumn<Rental, String> filmTitle;
    @FXML
    private TableView<Customer> customerTable;
    @FXML
    private TableColumn<Customer, Integer> customerid;
    @FXML
    private TableColumn<Customer, String> name;
    @FXML
    private TableColumn<Customer, String> mail;
    @FXML
    private TableColumn<Customer, String> phone;
    ObservableList<Customer> customers;
    @FXML
    private TextField searchByName;

//    public int getIdHolder() {
//        return idHolder;
//    }
//
//    public void setIdHolder(int idHolder) {
//        this.idHolder = idHolder;
//    }
//    public CustomerViewController(int idHolder){
//        this.idHolder = idHolder;
//    }

    public void initialize(){
        List<Customer> customerList = new ArrayList<>();
        customers = FXCollections.observableList(customerList);
        viewManager = new ViewManager();
        customerManager = new CustomerManager();
        setRentHistoryTable();
        setCustomerTable();
}
@FXML
private void onBackButtonClick() throws IOException {
    viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
}
@FXML
    private void onNewCustomerClick() throws IOException {
    viewManager.showNewCustomerView((Stage) topLabel.getScene().getWindow());
}
@FXML
    private void onUpdateCustomerClick() throws IOException {
    viewManager.showUpdateCustomerView((Stage) topLabel.getScene().getWindow());


}
@FXML
    private void onSearchButtonClick(){
        String customerId = searchCustomer.getText();
        String[] info = customerManager.getCustomerInfo(customerId);
        firstName.setText(info[0]);
        lastName.setText(info[1]);
        email.setText(info[2]);
        active.setText(info[3]);
        createDate.setText(info[4]);
        address1.setText(info[5]);
        address2.setText(info[6]);
        disctrict.setText(info[7]);
        city.setText(info[8]);
        postalCode.setText(info[9]);
        country.setText(info[10]);
        rentalHistory = customerManager.getCustomerRentals(customerId);
        rentalHistoryTable.setItems(rentalHistory);
}
@FXML
    private void setRentHistoryTable(){
    rentalId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getRentalId()).asObject());
    rentalDate.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getRentalDate().toString()));
    returnDate.setCellValueFactory(cellData -> IoConverter.dateToSimpleStringProperty(cellData.getValue().getReturnDate()));
    store.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getCustomer().getStore().toString()));
    filmId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getInventory().getFilm().getFilmId()).asObject());
    filmTitle.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getInventory().getFilm().getTitle()));
    rentalHistoryTable.setItems(rentalHistory);
}
@FXML
    private void setCustomerTable(){

    customerid.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getId()).asObject());
    name.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getFirstName() + " " + cellData.getValue().getLastName()));
    mail.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getEmail()));
    phone.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getAdress().getPhone()));
    customerTable.setItems(customers);
}
@FXML
    private void onSeeAllCustomers(){
    customers = customerManager.getAllCustomer();
    customerTable.setItems(customers);
}
@FXML
    private void filterOnName(){
    String filterString = searchByName.getText();
    List<Customer> filteredList = new ArrayList<>();
    for(Customer customer : customers){
        if((customer.getFirstName().toUpperCase() + " " + customer.getLastName().toUpperCase()).contains(filterString.toUpperCase())){
            filteredList.add(customer);
        }
    }
    customers = FXCollections.observableList(filteredList);
    customerTable.setItems(customers);
}
}
