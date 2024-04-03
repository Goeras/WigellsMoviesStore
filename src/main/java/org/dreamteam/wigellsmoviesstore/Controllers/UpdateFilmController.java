package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.hibernate.annotations.View;
import javafx.scene.*;

import java.io.IOException;
import java.util.List;

public class UpdateFilmController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private TextField title;
    @FXML
    private TextField description;
    @FXML
    private TextField releaseYear;
    @FXML
    private TextField rentalDuration;
    @FXML
    private TextField length;
    @FXML
    private TextField replacementCost;
    @FXML
    private TextField rentalRate;
    @FXML
    private CheckBox behindTheScenes;
    @FXML
    private CheckBox deletedScenes;
    @FXML
    private CheckBox trailers;
    @FXML
    private CheckBox commentaries;
    @FXML
    private ChoiceBox<Language> languageBox;
    @FXML
    private ChoiceBox<Language> originalLanguage;
    @FXML
    private ChoiceBox<String> ratingBox;

    public void initialize(){
        viewManager = new ViewManager();
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMovieView((Stage) topLabel.getScene().getWindow());
    }
}
