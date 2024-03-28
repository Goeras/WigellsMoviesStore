package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MovieViewController {
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
    public void onNewFilmButton() throws IOException{
        viewManager.showNewFilmView((Stage) topLabel.getScene().getWindow());
    }
    public void onUpdateFilmButton() throws IOException {
        viewManager.showUpdateFilmView((Stage) topLabel.getScene().getWindow());
    }
}
