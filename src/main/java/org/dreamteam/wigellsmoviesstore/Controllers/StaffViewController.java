package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.CurrentStaff;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Address;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;
import org.dreamteam.wigellsmoviesstore.Entitys.Store;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.StaffManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.dreamteam.wigellsmoviesstore.IoConverter.integerToSimpleIntegerProperty;
import static org.dreamteam.wigellsmoviesstore.IoConverter.stringToSimpleStringProperty;

public class StaffViewController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private ImageView imageView;
    @FXML
    private Label firstName;
    @FXML
    private Label lastName;
    @FXML
    private Label phoneNumber;
    @FXML
    private Label email;
    @FXML
    private Label username;
    @FXML
    private Label active;
    @FXML
    private Label address1;
    @FXML
    private Label address2;
    @FXML
    private Label district;
    @FXML
    private Label postalCode;
    @FXML
    private Label city;
    @FXML
    private Label country;
    @FXML
    private TableView<Staff> staffTable;
    @FXML
    private TableColumn<Staff, Integer> staffIdColumn;
    @FXML
    private TableColumn<Staff, String> firstNameColumn;
    @FXML
    private TableColumn<Staff, String> lastNameColumn;
    @FXML
    private TableColumn<Staff, String> emailColumn;
    @FXML
    private TableColumn<Staff, String> phoneColumn;
    @FXML
    private TextField filterStaff;
    @FXML
    private TextField idToSearch;
    @FXML
    private Button updateButton;

    private ObservableList<Staff> observableStaffList;


    Store store;
    Staff staff;
    DAOmanager daOmanager = new DAOmanager();
    StaffManager staffManager = new StaffManager();

    public void initialize(){
        CurrentStore.getInstance().updateCurrentStore();
        viewManager = new ViewManager();

        store = CurrentStore.getInstance().getCurrentStore();
        List<Staff> staffs = new ArrayList<>();
        staffs.addAll(store.getStaffList());
        observableStaffList = FXCollections.observableList(staffs);
        setTable(observableStaffList);

    }

    private void setTable(ObservableList<Staff> observableList){

        staffIdColumn.setCellValueFactory(cellData -> integerToSimpleIntegerProperty(cellData.getValue().getId()).asObject());
        firstNameColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getFirstName()));
        lastNameColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getLastName()));
        emailColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getEmail()));
        phoneColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getAdress().getPhone()));


        staffTable.setItems(observableList);
    }

    private void setStaffInfo(Staff staff){
        Address address = staff.getAdress();

        firstName.setText("Förnamn: "+staff.getFirstName());
        lastName.setText("Efternamn: "+staff.getLastName());
        phoneNumber.setText("Telefon nr: "+address.getPhone());
        email.setText("Mail: "+staff.getEmail());
        username.setText("Användarnamn: "+staff.getUserName());
        active.setText("Status: "+staffManager.isActiveStringFromBoolean(staff.getActive()));

        address1.setText("Adress 1: "+address.getAddress());
        address2.setText("Adress 2: "+address.getAddress2());
        district.setText("Distrikt: "+address.getDistrict());
        postalCode.setText("Post nr: "+address.getPostalCode());
        city.setText("Stad: "+address.getCity().getName());
        country.setText("Land: "+address.getCity().getCountry().getName());

        Image image = new Image("file:src/images/test.jpg");
        imageView.setFitWidth(100);
        imageView.setFitHeight(120);
        imageView.setImage(IoConverter.convertBlobToImage(staff.getPicture()));
    }

    @FXML // Sök medarbetare
    private void onSearchStaffButtonClick() throws IOException{
        boolean isInteger = IoValidator.validateInteger(idToSearch.getText());
        if (isInteger){
            staff = staffManager.searchByName(Integer.parseInt(idToSearch.getText()));
            if(staff != null){
                setStaffInfo(staff);
                updateButton.setVisible(true);
            }
        }
        else{
            IoValidator.displayAlert("Ingen träff", "Ange ett giltigt ID");
        }
    }

    @FXML // Sök alla
    private void onSearchAllStaffButtonClicked()throws IOException{

        observableStaffList.clear();
        observableStaffList.addAll(CurrentStore.getInstance().getCurrentStore().getStaffList());
        staffTable.setItems(observableStaffList);
        staffTable.refresh();
    }

    @FXML // Filtrera på namn
    private void onFilterByNameButtonClicked()throws IOException{
        if(filterStaff.getText().isEmpty()) {
            IoValidator.displayAlert("Ingen input", "Skriv in ett namn");
        }
        else{
            List<Staff> filteredStaffList = staffManager.filterByName(observableStaffList, filterStaff.getText());
            if(filteredStaffList.isEmpty()){
                IoValidator.displayAlert("Ingen träff", "Testa ett annat namn");
            }
            else {
                observableStaffList.clear();
                observableStaffList.addAll(filteredStaffList);
                staffTable.setItems(observableStaffList);
                staffTable.refresh();
            }
        }
    }

    @FXML // Ny medarbetare
    private void onNewStaffButtonClick() throws IOException {
        viewManager.showNewStaffView((Stage) topLabel.getScene().getWindow());
    }
    @FXML // Ändra medarbetare
    private void onUpdateStaffButtonClick() throws IOException{
        CurrentStaff.getInstance().setCurrentStaff(staff);
        viewManager.showUpdateStaffView((Stage) topLabel.getScene().getWindow());
    }
    @FXML // Tillbaka
    protected void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
}
