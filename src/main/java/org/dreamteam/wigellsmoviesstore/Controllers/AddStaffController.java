package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Country;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.StaffManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.*;
import java.sql.Blob;
import java.sql.SQLException;

public class AddStaffController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Image image;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField eMail;
    @FXML
    private TextField userName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;
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
    private PasswordField password;
    @FXML
    private PasswordField password2;

    Blob blob;

    StaffManager staffManager = new StaffManager();
    private ObservableList countryList = FXCollections.observableArrayList();

    public void initialize(){
        viewManager = new ViewManager();
        countryList.addAll(staffManager.getAllCountriesAsList());
        country.setItems(countryList);
    }

    @FXML
    private void onSaveStaffButtonClicked(){ // Country bör vara dropdown list.
        boolean createSuccessfull = false;
        boolean emailUnique = staffManager.validateUniqueEmail(eMail.getText());
        boolean usernameUnique = staffManager.validateUniqueUsername(userName.getText());
        if(!IoValidator.stringOneEqualsStringTwo(password.getText(), password2.getText())){
            IoValidator.displayAlert("Fel lösenord", "Lösenorden matchar ej");
        }
        if(usernameUnique && emailUnique) {
            createSuccessfull = staffManager.createNewStaff(firstName.getText(), lastName.getText(), eMail.getText(), userName.getText(), phoneNumber.getText(), password.getText(), password2.getText(), address.getText(), address2.getText(), district.getText(), postalCode.getText(), city.getText(), country.getValue(),blob);
        }
        else{
            IoValidator.displayAlert("Användarnamn eller email upptaget", "Testa annat användarnamn eller email");
        }
       if(createSuccessfull){
           IoValidator.displayConfirmation("Medarbetare skapad", "Ny medarbetare skapad");
        }
       else{
           IoValidator.displayAlert("Felaktig input", "Kontrollera uppgifterna");
        }
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showStaffView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onUploadPictureButtonClick(){
        blob = staffManager.getImageFromDisk();

        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);
        imageView.setImage(IoConverter.convertBlobToImage(blob));
    }
}
