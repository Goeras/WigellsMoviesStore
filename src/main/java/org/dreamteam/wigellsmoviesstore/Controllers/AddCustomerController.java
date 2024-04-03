package org.dreamteam.wigellsmoviesstore.Controllers;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;

public class AddCustomerController {
    @FXML
    private Label topLabel;
    private ViewManager viewManager;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showCustomerView((Stage) topLabel.getScene().getWindow());
    }


}
