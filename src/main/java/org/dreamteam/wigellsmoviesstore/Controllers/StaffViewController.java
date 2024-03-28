package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffViewController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onNewStaffButtonClick() throws IOException {
        viewManager.showNewStaffView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onUpdateStaffButtonClick() throws IOException{
        viewManager.showUpdateStaffView((Stage) topLabel.getScene().getWindow());
    }
}
