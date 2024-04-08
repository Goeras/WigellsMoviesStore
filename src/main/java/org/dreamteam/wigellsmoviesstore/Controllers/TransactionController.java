package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Payment;
import org.dreamteam.wigellsmoviesstore.Entitys.Rental;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.Managers.PosManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;
import org.geolatte.geom.V;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class TransactionController {
    ViewManager viewManager;
    @FXML
    private TableView<Rental> rentals;
    @FXML
    private TableColumn<Rental, Integer> rentalId;
    @FXML
    private TableColumn<Rental, Integer> inventoryId;
    @FXML
    private TableColumn<Rental, String> filmTitle;
    @FXML
    private TableColumn<Rental, String> customer;
    @FXML
    private DatePicker fromPicker;
    @FXML
    private DatePicker toPicker;
    @FXML
    private TableView<Payment> payments;
    @FXML
    private TableColumn<Payment, Integer> paymentId;
    @FXML
    private TableColumn<Payment, Integer> rentalIdColumn;
    @FXML
    private TableColumn<Payment, String> timeStamp;
    @FXML
    private TableColumn<Payment, Integer> customerColumn;
    @FXML
    private TableColumn<Payment, Double> amount;
    private ObservableList<Rental> rentalList;
    private ObservableList<Payment> paymentList;
    private PosManager posManager;

    public void initialize(){
        viewManager = new ViewManager();
        posManager = new PosManager();
        List<Rental> rentalArrayList = new ArrayList<>();
        List<Payment> paymentArrayList = new ArrayList<>();
        rentalList = FXCollections.observableList(rentalArrayList);
        paymentList = FXCollections.observableList(paymentArrayList);
        setPaymentTable();
        setRentalTable();

    }
    public void setRentalTable(){
        rentalId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getRentalId()).asObject());
        inventoryId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getInventory().getId()).asObject());
        filmTitle.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getInventory().getFilm().getTitle()));
        customer.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getCustomer().getFirstName() + " " + cellData.getValue().getCustomer().getLastName()));
        rentals.setItems(rentalList);
    }
    public void setPaymentTable(){
        paymentId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getId()).asObject());
        amount.setCellValueFactory(cellData -> IoConverter.doubleToSimpleDoubleProperty(cellData.getValue().getAmount()).asObject());
        customerColumn.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getCustomer().getId()).asObject());
        rentalIdColumn.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getRental().getRentalId()).asObject());
        timeStamp.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getPaymentDate().toString()));
        payments.setItems(paymentList);
    }
    @FXML
    private void onSeePaymentsClick(){
        paymentList.remove(paymentList);
        payments.setItems(paymentList);
        LocalDateTime fromDateTime = LocalDateTime.of(fromPicker.getValue(), LocalTime.of(0,0));
        LocalDateTime toDateTime = LocalDateTime.of(toPicker.getValue(), LocalTime.of(23,59, 59, 99999));
        paymentList = posManager.seePayments(fromDateTime, toDateTime);
        payments.setItems(paymentList);
    }
    @FXML
    private void onSeeRentalsClick(){
        rentalList = posManager.seeRentals();
        rentals.setItems(rentalList);
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showPosView((Stage) payments.getScene().getWindow());
    }
}
