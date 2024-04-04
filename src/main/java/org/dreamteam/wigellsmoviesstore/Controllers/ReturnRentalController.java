package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Entitys.Rental;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.Managers.PosManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ReturnRentalController {
    private ViewManager viewManager;
    private PosManager posManager;
    @FXML
    private TextField customerNumber;
    @FXML
    private Label custId;
    @FXML
    private Label lastName;
    @FXML
    private TableView<Rental> rentalTableView;
    @FXML
    private TableColumn<Rental, Integer> rentalIdColumn;
    @FXML
    private TableColumn<Rental, String> dateColumn;
    @FXML
    private TableColumn<Rental, Integer> filmIdColumn;
    @FXML
    private TableColumn<Rental, String> filmTitle;
    @FXML
    private TableColumn<Rental, String> returnDate;
    private ObservableList<Rental> rentals;
    private ObservableList<Rental> returnList;
    @FXML
    private TableView<Rental> posTable;
    @FXML
    private TableColumn<Rental, Integer> filmId;
    @FXML
    private TableColumn<Rental, String> filmtitle;
    @FXML
    private TableColumn<Rental, String> price;
    @FXML
    private TableColumn<Rental, Integer> rentalId;

    public void initialize(){
        viewManager = new ViewManager();
        posManager = new PosManager();
        List<Rental> rentalList = new ArrayList<>();
        List<Rental> posList = new ArrayList<>();
        returnList = FXCollections.observableList(posList);
        rentals = FXCollections.observableList(rentalList);
        setRentalTableView();
        setPosTableView();
    }
    public void setRentalTableView(){
        rentalIdColumn.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getRentalId()).asObject());
        dateColumn.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getRentalDate().toString()));
        filmIdColumn.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getInventory().getFilm().getFilmId()).asObject());
        filmTitle.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getInventory().getFilm().getTitle()));
        returnDate.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getReturnDate().toString()));
        rentalTableView.setItems(rentals);
    }
    private void setPosTableView(){

        rentalId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getRentalId()).asObject());
        filmId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getInventory().getFilm().getFilmId()).asObject());
        filmtitle.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getInventory().getFilm().getTitle()));

        price.setCellValueFactory(cellData -> {
            Rental rental = cellData.getValue();
            String priceString = String.valueOf(posManager.checkDate(rental) ? 1 : 0);
            return new SimpleStringProperty(priceString);
        });
        posTable.setItems(returnList);
    }
    @FXML
    private void onSearchCustomer(){
        String[] info = posManager.searchCustomer(customerNumber.getText());
        custId.setText(info[0]);
        lastName.setText(info[1]);
        rentals = posManager.getObservableRentals(custId.getText());
        rentalTableView.setItems(rentals);
    }
    @FXML
    private void onReturnButtonClick() {
        posManager.returnRental(rentalTableView.getSelectionModel().getSelectedItem());
        returnList.add(rentalTableView.getSelectionModel().getSelectedItem());
        posTable.setItems(returnList);
    }
    @FXML
    private void onConfirmReturnButtonClick(){
        for(Rental rental : posTable.getItems()){
            posManager.payReturnFees(rental.getCustomer(), rental.getStaff(), rental, price.getCellData(rental));
        }
        returnList.removeAll(returnList);
        customerNumber.setText("");
        custId.setText("");
        lastName.setText("");
        rentals.removeAll(rentals);
        setPosTableView();
        setRentalTableView();
    }
    @FXML
    private void onReplaceButtonClick(){
        price.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(IoConverter.doubleToString(cellData.getValue().getInventory().getFilm().getReplacementCost())));
        returnList.add(rentalTableView.getSelectionModel().getSelectedItem());
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showPosView((Stage) posTable.getScene().getWindow());
    }
}
