package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.IOException;

public class PosController {
    @FXML
    private Label topLabel;
    ViewManager viewManager;
    @FXML
    private HBox searchCustomer;
    @FXML
    private HBox confirmCustomer;
    @FXML
    private HBox chosenCustInfo;
    @FXML
    private Button backButton;

    public void initialize(){
        viewManager = new ViewManager();
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
}
