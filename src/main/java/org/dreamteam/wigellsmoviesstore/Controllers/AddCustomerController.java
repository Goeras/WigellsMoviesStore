package org.dreamteam.wigellsmoviesstore.Controllers;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Country;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.CustomerManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;

public class AddCustomerController {
    @FXML
    private Label topLabel;

    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private TextField address1;
    @FXML
    private TextField address2;
    @FXML
    private TextField district;
    @FXML
    private TextField postalCode;
    @FXML
    private TextField city;
    @FXML
    private ChoiceBox<Country> country;
    @FXML
    private TextField userNotice;

    private ViewManager viewManager;
    private CustomerManager customerManager;
    private IoValidator ioValidator;
    private ObservableList countries = FXCollections.observableArrayList();


    public void initialize(){
        viewManager = new ViewManager();
        customerManager = new CustomerManager();
        countries.addAll(customerManager.getCountryList());
        country.setItems(countries);

    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager = new ViewManager();
        viewManager.showCustomerView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    public void onSaveButtonClick() throws IOException{


        // valideringar från iovalidator innan något läggs in.  //ska göras om till mer individuella när sakerna fungerar riktigt.
        boolean validName = ioValidator.validateStringNotEmpty(firstName.getText());
        boolean validLastName = ioValidator.validateStringNotEmpty(lastName.getText());
        boolean validEmail = ioValidator.validateEmail(email.getText());
        boolean uniqueEmail = customerManager.validateUniqueEmail(email.getText()) || email.getText().isEmpty();
        boolean phoneNotEmpty = ioValidator.validateStringNotEmpty(phone.getText());
        boolean validPhoneNum = ioValidator.validateInteger(phone.getText()) || phone.getText().isEmpty();
        boolean validAddess = ioValidator.validateStringNotEmpty(address1.getText());
        boolean validDistrict = ioValidator.validateStringNotEmpty(district.getText());
        boolean validPostCode = ioValidator.validatePostNum(postalCode.getText());
        boolean validCity = ioValidator.validateStringNotEmpty(city.getText());

        if(validName && validLastName && validEmail && uniqueEmail && validPhoneNum && phoneNotEmpty && validAddess && validDistrict && validPostCode && validCity && country != null){
            customerManager.newCustomer(firstName.getText(),lastName.getText(),email.getText(),phone.getText(),address1.getText(),address2.getText(),district.getText(),postalCode.getText(),city.getText(),country.getValue());

        } else {
            userNotice.setVisible(true);
            userNotice.setText("Kontrollera uppgifterna och försök igen");
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Kontrollera uppgifterna och försök igen");

            alert.showAndWait();
        }

    }


}