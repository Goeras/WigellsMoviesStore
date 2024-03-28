package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class StartController {
    @FXML
    private Label topLabel;
   private ViewManager viewManager;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    protected void onMenuButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
}