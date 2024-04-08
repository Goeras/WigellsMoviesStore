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

        // valideringar från iovalidator
        boolean validName = ioValidator.validateStringNotEmpty(firstName.getText());
        boolean validLastName = ioValidator.validateStringNotEmpty(lastName.getText());
        boolean validEmail = ioValidator.validateEmail(email.getText());
        boolean uniqueEmail = customerManager.validateUniqueEmail(email.getText()) || email.getText().isEmpty();
        boolean phoneNotEmpty = ioValidator.validateStringNotEmpty(phone.getText());
        boolean validPhoneNum = ioValidator.validateLong(phone.getText()) || phone.getText().isEmpty();
        boolean validAddress = ioValidator.validateStringNotEmpty(address1.getText());
        boolean validDistrict = ioValidator.validateStringNotEmpty(district.getText());
        boolean validPostCode = ioValidator.validatePostNum(postalCode.getText());
        boolean validCity = ioValidator.validateStringNotEmpty(city.getText());


        // Ett försök till inviduell validering som inte riktigt fungerar. antingen visas alla när alla fält är tomma eller inte alls.
//        boolean allFieldsEmpty = true;
//        boolean someFieldsAreEmpty = false;
//
//        if (!firstName.getText().isEmpty() || !lastName.getText().isEmpty() || !email.getText().isEmpty() ||
//                !phone.getText().isEmpty() || !address1.getText().isEmpty() || !address2.getText().isEmpty() ||
//                !district.getText().isEmpty() || !postalCode.getText().isEmpty() || !city.getText().isEmpty() ||
//                country.getValue() != null) {
//            allFieldsEmpty = false; // Inte alla fält är tomma
//            someFieldsAreEmpty = true; // Åtminstone ett fält är ifyllt
//        }
//
//        if (someFieldsAreEmpty) {
//            if (!validName) {
//                ioValidator.displayAlert("Error", "Du måste fylla i namn");
//            }
//            if (!validLastName) {
//                ioValidator.displayAlert("Error", "Du måste fylla i efternamn");
//            }
//            if(!validEmail){
//                ioValidator.displayAlert("Error","Du måste ange en giltlig email. t.ex: mail@host.com ");
//            }
//            if(!validPhoneNum){
//                ioValidator.displayAlert("Error","Du måste ange ett telefonnummer");
//            }
//            if(!validAddress){
//                ioValidator.displayAlert("Error","Du måste fylla i adress");
//            }
//            if(!validDistrict){
//                ioValidator.displayAlert("Error","Vänligen ange distrikt");
//            }
//            if(!validPostCode){
//                ioValidator.displayAlert("Error","Du måste ange ett giltligt postnummer");
//            }
//            if(!validCity){
//                ioValidator.displayAlert("Error","Du måste fylla i stad");
//            }
//        } else {
//            ioValidator.displayAlert("Error", "Alla fält är tomma. Vänligen fyll i");
//        }


        // Om uppgifterna är korrekt så sparas dom.
        if(validName && validLastName && validEmail && uniqueEmail && validPhoneNum && phoneNotEmpty && validAddress && validDistrict && validPostCode && validCity && country != null){
            customerManager.newCustomer(firstName.getText(),lastName.getText(),email.getText(),phone.getText(),address1.getText(),address2.getText(),district.getText(),postalCode.getText(),city.getText(),country.getValue());
            ioValidator.displayConfirmation("Info","Kund sparad!");
        } else {
            ioValidator.displayAlert("Error","Kontrollera uppgifterna och försök igen");
        }
    }
}