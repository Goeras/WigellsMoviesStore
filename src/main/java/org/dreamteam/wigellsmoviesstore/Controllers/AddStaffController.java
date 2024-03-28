package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AddStaffController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showStaffView((Stage) topLabel.getScene().getWindow());
    }

}
