package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class PosController {
    @FXML
    private Label topLabel;
    ViewManager viewManager;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
}
