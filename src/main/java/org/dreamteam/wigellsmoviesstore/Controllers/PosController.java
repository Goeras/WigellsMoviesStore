package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
<<<<<<< Updated upstream
=======
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.Managers.PosManager;
>>>>>>> Stashed changes
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
    private TableView<Film> tableView;
    @FXML
    private TableColumn<Film, Integer> filmId;
    @FXML
    private TableColumn<Film, String> title;
    @FXML
    private TableColumn<Film, String> returnDate;
    @FXML
    private TableColumn<Film, Double> price;
    private List<Film> filmList;
    private ObservableList<Film> cart;
    @FXML
    private TextField filmIdField;
    @FXML
    private Label filmIdLabel;
    @FXML
    private Label filmTitleLabel;

    public void initialize(){
        viewManager = new ViewManager();
        posManager = new PosManager();
        filmList = new ArrayList<>();
        cart = FXCollections.observableArrayList(filmList);
        initializeTable();
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onConfirmCustomerButton(){
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
    }
    private void initializeTable(){
        filmId.setCellValueFactory(cellData -> IoConverter.integerToSimpleIntegerProperty(cellData.getValue().getFilmId()).asObject());
        title.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(cellData.getValue().getTitle()));
        price.setCellValueFactory(cellData -> IoConverter.doubleToSimpleDoubleProperty(cellData.getValue().getRentalRate()).asObject());
        returnDate.setCellValueFactory(cellData -> IoConverter.stringToSimpleStringProperty(posManager.calculateReturnDate(cellData.getValue().getRentalDuration())));
    }
    @FXML
    private void addToCart(){
        posManager.addFilmToCart(cart, filmIdField.getText());
        tableView.setItems(cart);
    }
    @FXML
    private void searchFilm(){
        if(!filmIdField.getText().isBlank()) {
            String id = filmIdField.getText();
            String[] info = posManager.searchFilm(id);
            filmIdLabel.setText(info[0]);
            filmTitleLabel.setText(info[1]);
        }
    }
}
