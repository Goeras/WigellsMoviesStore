package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.CurrentStaff;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.Entitys.Address;
import org.dreamteam.wigellsmoviesstore.Entitys.Country;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;
import org.dreamteam.wigellsmoviesstore.Entitys.Store;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.StaffManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;

public class UpdateStaffController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField eMail;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField userName;
    @FXML
    private PasswordField password1;
    @FXML
    private PasswordField password2;
    @FXML
    private ChoiceBox<String> status;
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

    Blob blob;

    Store store = CurrentStore.getInstance().getCurrentStore();
    Staff staff = CurrentStaff.getInstance().getCurrentStaff();

    private Image image;

    StaffManager staffManager =  new StaffManager();

    private ObservableList<Country> countryList = FXCollections.observableArrayList();
    ObservableList<String> statusOptions = FXCollections.observableArrayList("Aktiv", "Ej Aktiv");

    public void initialize(){

        CurrentStore.getInstance().updateCurrentStore();
        viewManager = new ViewManager();

        countryList.addAll(staffManager.getAllCountriesAsList());
        country.setItems(countryList);

        Address address = staff.getAdress();

        firstName.setText(staff.getFirstName());
        lastName.setText(staff.getLastName());
        phoneNumber.setText(address.getPhone());
        eMail.setText(staff.getEmail());
        userName.setText(staff.getUserName());
        password1.setText(staff.getPassword());
        password2.setText(staff.getPassword());

        status.setItems(statusOptions);
        status.setValue(staffManager.isActiveStringFromBoolean(staff.getActive()));

        address1.setText(address.getAddress());
        address2.setText(address.getAddress2());
        district.setText(address.getDistrict());
        postalCode.setText(address.getPostalCode());
        city.setText(address.getCity().getName());
        country.setValue(address.getCity().getCountry());
        imageView.setFitWidth(70);
        imageView.setFitHeight(70);
        imageView.setPreserveRatio(true);
        imageView.setImage(IoConverter.convertBlobToImage(staff.getPicture()));
        blob = staff.getPicture();

    }
    public void onBackButtonClick() throws IOException {
        viewManager.showStaffView((Stage) topLabel.getScene().getWindow());
    }

    public void onUploadPictureButtonClick() {
        blob = staffManager.getImageFromDisk();

        imageView.setFitWidth(90);
        imageView.setFitHeight(90);
        imageView.setPreserveRatio(true);
        imageView.setImage(IoConverter.convertBlobToImage(blob));

    }

    @FXML
    public void onSaveButtonClick(){
        System.out.println("save button clicked");
        boolean createSuccessfull = false;
        boolean emailUnique = staffManager.validateUniqueEmail(eMail.getText());
        boolean usernameUnique = staffManager.validateUniqueUsername(userName.getText());
        if(!IoValidator.stringOneEqualsStringTwo(password1.getText(), password2.getText())){
            IoValidator.displayAlert("Fel lösenord", "Lösenorden matchar ej");
        }
        if(usernameUnique && emailUnique) {
            createSuccessfull = staffManager.updateStaff(firstName.getText(), lastName.getText(), eMail.getText(), userName.getText(), phoneNumber.getText(), password1.getText(), password2.getText(), address1.getText(), address2.getText(), district.getText(), postalCode.getText(), city.getText(), country.getValue(),blob, staffManager.isActiveBooleanFromString(status.getValue()));
        }
        else{
            IoValidator.displayAlert("Användarnamn eller email upptaget", "Testa annat användarnamn eller email");
        }
        if(createSuccessfull){
            IoValidator.displayConfirmation("Medarbetare uppdaterad", "Medarbetare uppdaterad");
        }
        else{
            IoValidator.displayAlert("Felaktig input", "Kontrollera uppgifterna");
        }
    }
}
