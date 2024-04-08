package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Entitys.Inventory;
import org.dreamteam.wigellsmoviesstore.Entitys.Staff;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.PosManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PosController {
    @FXML
    private Label topLabel;
    ViewManager viewManager;
    PosManager posManager;
    @FXML
    private HBox searchCustomer;
    @FXML
    private HBox confirmCustomer;
    @FXML
    private HBox chosenCustInfo;
    @FXML
    private Button backButton;
    @FXML
    private TableView<Inventory> tableView;
    @FXML
    private TableColumn<Inventory, Integer> filmId;
    @FXML
    private TableColumn<Inventory, String> title;
    @FXML
    private TableColumn<Inventory, Double> price;
    private List<Inventory> filmList;
    private ObservableList<Inventory> cart;
    @FXML
    private TextField filmIdField;
    @FXML
    private Label filmIdLabel;
    @FXML
    private Label filmTitleLabel;
    @FXML
    private Label confirmCustId;
    @FXML
    private Label confirmCustName;
    @FXML
    private Label chosenCustId;
    @FXML
    private Label chosenCustName;
    @FXML
    private TextField searchCustomerField;
    @FXML
    private ChoiceBox<Staff> staffBox;
    @FXML
    private Button okButton;
    @FXML
    private Button addToCartButton;

    ObservableList<Staff> staffList;
    String inventoryId;

    public void initialize(){
        viewManager = new ViewManager();
        posManager = new PosManager();
        filmList = new ArrayList<>();
        staffList = posManager.getStaffList();
        staffBox.setItems(staffList);
        cart = FXCollections.observableArrayList(filmList);
        initializeTable();
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onSearchCustomerButton(){
        if(IoValidator.validateInteger(searchCustomerField.getText())) {
            String id = searchCustomerField.getText();
            String[] info = posManager.searchCustomer(id);
            confirmCustId.setText(info[0]);
            confirmCustName.setText(info[1]);
            okButton.setVisible(true);
        }
        else{
            IoValidator.displayAlert("Ingen träff", "Kontrollera Kund-ID");
        }
    }
    @FXML
    private void onConfirmCustomerButton(){
        chosenCustId.setText(confirmCustId.getText());
        chosenCustName.setText(confirmCustName.getText());
        confirmCustId.setText("");
        confirmCustName.setText("");
        searchCustomerField.setText("");
        chosenCustInfo.setManaged(true);
        chosenCustInfo.setVisible(true);
        searchCustomer.setManaged(false);
        searchCustomer.setVisible(false);
        confirmCustomer.setManaged(false);
        confirmCustomer.setVisible(false);
    }
    @FXML
    private void onChangeCustomerButton(){
        searchCustomer.setManaged(true);
        searchCustomer.setVisible(true);
        confirmCustomer.setManaged(true);
        confirmCustomer.setVisible(true);
        chosenCustInfo.setManaged(false);
        chosenCustInfo.setVisible(false);
        okButton.setVisible(false);
    }
    private void initializeTable(){
        filmId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getFilm().getFilmId()).asObject());
        title.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getFilm().getTitle()));
        price.setCellValueFactory(cellData -> IoConverter.doubleToSimpleDoubleProperty(cellData.getValue().getFilm().getRentalRate()).asObject());
    }
    @FXML
    private void addToCart(){
        posManager.addFilmToCart(cart, filmIdField.getText());
        tableView.setItems(cart);
        inventoryId = filmIdField.getText();
    }
    @FXML
    private void searchFilm(){
        if(!filmIdField.getText().isBlank()) {
            if(IoValidator.validateInteger(filmIdField.getText())) {
                String id = filmIdField.getText();
                String[] info = posManager.searchFilm(id);
                filmIdLabel.setText(info[0]);
                filmTitleLabel.setText(info[1]);
                addToCartButton.setVisible(true);
            }
            else{
                IoValidator.displayAlert("Ingen träff", "kontrollera Film-ID");
            }
        }
        else{
            IoValidator.displayAlert("Tomt sökfält", "Sökfältet är tomt");
        }
    }
    @FXML
    private void onConfirmRentalButtons(){
        if(!(staffBox.getValue() == null) && !chosenCustId.getText().isBlank()) {
            int staff = staffBox.getValue().getId();
            posManager.confirmRentals(cart, chosenCustId.getText(), staff);
            filmIdField.setText("");
            cart.removeAll(cart);
            tableView.setItems(cart);
            chosenCustName.setText("");
            chosenCustId.setText("");
            filmIdLabel.setText("");
            filmTitleLabel.setText("");
            addToCartButton.setVisible(false);

        }
        else if(staffBox.getValue() == null){
            IoValidator.displayAlert("Ingen anställd vald","Välj butiksanställd i dropdown menyn");
        }
        else if(chosenCustId.getText().isBlank()){
            IoValidator.displayAlert("Kontrollera kund","Bekräfta kunduppgifter genom att trycka på OK");
        }
    }
    @FXML
    private void onReturnButtonClick() throws IOException {
        viewManager.showReturnRentalView((Stage) topLabel.getScene().getWindow());
    }

    public void onSeeTransactionsClick() throws IOException {
        viewManager.showTransactionView((Stage) topLabel.getScene().getWindow());
    }
    public void onRegretFilmButtonClick(){
        cart.remove(tableView.getSelectionModel().getSelectedItem());
        tableView.setItems(cart);
    }
}
