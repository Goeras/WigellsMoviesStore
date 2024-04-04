package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Rental;
import org.dreamteam.wigellsmoviesstore.Managers.CustomerManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;
import java.lang.reflect.Array;

public class CustomerViewController {
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


public void initialize(){

    viewManager = new ViewManager();
    customerManager = new CustomerManager();
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
}

}
