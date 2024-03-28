package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.hibernate.annotations.View;
import javafx.scene.*;

import java.io.IOException;

public class UpdateFilmController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMovieView((Stage) topLabel.getScene().getWindow());
    }
}
