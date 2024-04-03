package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;

public class CustomerViewController {
@FXML
    private Label topLabel;
ViewManager viewManager;

public void initialize(){
    viewManager = new ViewManager();
}
@FXML
private void onBackButtonClick() throws IOException {
    viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
}
@FXML
    private void onNewCustomerClick() throws IOException {
    viewManager.showNewCustomerView((Stage) topLabel.getScene().getWindow());
}
@FXML
    private void onUpdateCustomerClick() throws IOException {
    viewManager.showUpdateCustomerView((Stage) topLabel.getScene().getWindow());
}
}
