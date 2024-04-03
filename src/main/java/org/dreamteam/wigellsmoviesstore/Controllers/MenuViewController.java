package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;

public class MenuViewController {
   private ViewManager viewManager;
   @FXML
   private Label menuLabel;
    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    protected void onBackButton() throws IOException {
        viewManager.showStartView((Stage) menuLabel.getScene().getWindow());
    }
    @FXML
    protected void onCustomerButtonClick() throws IOException{
        viewManager.showCustomerView((Stage) menuLabel.getScene().getWindow());
    }
    @FXML
    protected void onMovieButtonClick() throws IOException {
        viewManager.showMovieView((Stage) menuLabel.getScene().getWindow());
    }
    @FXML
    protected void onPosButtonClick() throws IOException {
        viewManager.showPosView((Stage) menuLabel.getScene().getWindow());
    }
    @FXML
    protected void onStaffButtonClick() throws IOException {
        viewManager.showStaffView((Stage) menuLabel.getScene().getWindow());
    }
}
