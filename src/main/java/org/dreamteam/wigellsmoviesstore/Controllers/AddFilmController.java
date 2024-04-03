package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.dreamteam.wigellsmoviesstore.IoConverter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AddFilmController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private ChoiceBox<Language> languageBox;
    @FXML
    private ChoiceBox<Language> originalLanguage;
    @FXML
    private ChoiceBox<String> ratingBox;
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


    public void initialize(){
        viewManager = new ViewManager();
        List<Language> languageList = new ArrayList<>();
        ObservableList<Language> languageList1 = FXCollections.observableList(languageList);
        languageBox.setItems(languageList1);
        originalLanguage.setItems(languageList1);
        List<String> ratingList = new ArrayList<>();
        ratingList.add("PG_13");
        ratingList.add("PG");
        ratingList.add("R");
        ObservableList<String> filmratingList = FXCollections.observableList(ratingList);
        ratingBox.setItems(filmratingList);
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMovieView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onSaveButtonClick(){
        List<String> infoList = new ArrayList<>();
        infoList.add(title.getText());
        infoList.add(description.getText());
        infoList.add(releaseYear.getText());
        infoList.add(IoConverter.integerToString(languageBox.getValue().getId()));
        infoList.add(IoConverter.integerToString(originalLanguage.getValue().getId()));
        infoList.add(rentalDuration.getText());
        infoList.add(length.getText());
        infoList.add(replacementCost.getText());
        infoList.add(rentalRate.getText());
        infoList.add(Boolean.toString(behindTheScenes.isSelected()));
        infoList.add(Boolean.toString(deletedScenes.isSelected()));
        infoList.add(Boolean.toString(trailers.isSelected()));
        infoList.add(Boolean.toString(commentaries.isSelected()));

        System.out.println("Skicka infomation och skapa ny film" + infoList.size());
    }
}
