package org.dreamteam.wigellsmoviesstore.Controllers;

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
import org.dreamteam.wigellsmoviesstore.Managers.StaffManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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

    Store store = CurrentStore.getInstance().getCurrentStore();
    Staff staff = CurrentStaff.getInstance().getCurrentStaff();

    private Image image;

    StaffManager staffManager =  new StaffManager();

    public void initialize(){

        CurrentStore.getInstance().updateCurrentStore();
        viewManager = new ViewManager();

        Address address = staff.getAdress();

        firstName.setText(staff.getFirstName());
        lastName.setText(staff.getLastName());
        phoneNumber.setText(address.getPhone());
        eMail.setText(staff.getEmail());
        userName.setText(staff.getUserName());

        status.setValue(staffManager.isActiveStringFromBoolean(staff.getActive()));
        address1.setText(address.getAddress());
        address2.setText(address.getAddress2());
        district.setText(address.getDistrict());
        postalCode.setText(address.getPostalCode());
        city.setText(address.getCity().getName());
        country.setValue(address.getCity().getCountry());
        imageView.setImage(IoConverter.convertBlobToImage(staff.getPicture()));

    }
    public void onBackButtonClick() throws IOException {
        viewManager.showStaffView((Stage) topLabel.getScene().getWindow());
    }

    public void onUploadPictureButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Välj bild");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Bilder", "*.jpg", "*.png", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile!=null){
            try{
                InputStream inputStream = new FileInputStream(selectedFile);
                image = new Image(inputStream);
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Fel vid inläsning av bild");
            }
        }
        imageView.setFitWidth(60);
        imageView.setFitHeight(70);
        imageView.setImage(image);
    }

    @FXML
    public void onSaveButtonClick(){
        System.out.println("save button clicked");
    }
}
