package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Actor;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.dreamteam.wigellsmoviesstore.HelloApplication;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.FilmManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

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
    private ListView<Category> categoryBox;
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
    private ObservableList<Actor> selectedActors;
    @FXML
    private ListView<Actor> actorList;


    DAOmanager daoManager = new DAOmanager();
    FilmManager filmManager = new FilmManager();

    public void initialize() {
        viewManager = new ViewManager();

        ObservableList<Category> categoryList = FXCollections.observableList(daoManager.getCategoryDAO().readAllCategories());
        categoryBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        categoryBox.setItems(categoryList);

        ObservableList<Language> languageList = FXCollections.observableList(daoManager.getLanguageDAO().readAllLanguages());
        languageBox.setItems(languageList);
        originalLanguage.setItems(languageList);

        List<String> ratingList = new ArrayList<>();
        ratingList.add("G");
        ratingList.add("PG");
        ratingList.add("PG-13");
        ratingList.add("R");
        ratingList.add("NC-17");
        ObservableList<String> filmRatingList = FXCollections.observableList(ratingList);
        ratingBox.setItems(filmRatingList);
    }

    public void initialize(List<Actor> actors) {
        selectedActors = FXCollections.observableList(actors);
        viewManager = new ViewManager();

        ObservableList<Category> categoryList = FXCollections.observableList(daoManager.getCategoryDAO().readAllCategories());
        categoryBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        categoryBox.setItems(categoryList);

        ObservableList<Language> languageList = FXCollections.observableList(daoManager.getLanguageDAO().readAllLanguages());
        languageBox.setItems(languageList);
        originalLanguage.setItems(languageList);

        List<String> ratingList = new ArrayList<>();
        ratingList.add("G");
        ratingList.add("PG");
        ratingList.add("PG-13");
        ratingList.add("R");
        ratingList.add("NC-17");
        ObservableList<String> filmRatingList = FXCollections.observableList(ratingList);
        ratingBox.setItems(filmRatingList);
        actorList.setItems(selectedActors);
    }

    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMovieView((Stage) topLabel.getScene().getWindow());
    }

    @FXML
    private void onSaveButtonClick() {
        List<String> specialFeaturesList = new ArrayList<>();

        if (behindTheScenes.isSelected()) {
            specialFeaturesList.add("Behind the Scenes");
        }

        if (deletedScenes.isSelected()) {
            specialFeaturesList.add("Deleted Scenes");
        }

        if (trailers.isSelected()) {
            specialFeaturesList.add("Trailers");
        }

        if (commentaries.isSelected()) {
            specialFeaturesList.add("Commentaries");
        }
        String specialFeaturesString = IoConverter.specialFeaturesToString(specialFeaturesList);


        List<Category> categoryList = categoryBox.getSelectionModel().getSelectedItems();

//        if(selectedActors == null) {
//            IoValidator.displayAlert("Missing Actors", "Vänligen fyll i Skådespelare");
//        } else if
//        (categoryList == null || categoryList.isEmpty()) {
//            IoValidator.displayAlert("Missing Categories", "Vänligen fyll i Kategori");
//        } else if
//        (title.getText() == null || title.getText().isEmpty()) {
//            IoValidator.displayAlert("Missing Title", "Vänligen fyll i Filmtitel");
//        } else if
//        (languageBox.getValue() == null) {
//            IoValidator.displayAlert("Missing Language", "Vänligen fyll i Språk");
//        } else if
//        (originalLanguage.getValue() == null) {
//            IoValidator.displayAlert("Missing Originallanguage", "Vänligen fyll Ursprungsspråk");
//        } else if (releaseYear.getText() == null) {
//            IoValidator.displayAlert("Missing releaseYear", "Vänligen fyll i releaseyear");
//        } else if (rentalDuration.getText() == null) {
//            IoValidator.displayAlert("Missing rentalDuration", "Vänligen fyll i Hyrperiod");
//        }else {
//            List<String> infoList = new ArrayList<>();
//            infoList.add(title.getText());
//            infoList.add(description.getText());
//            infoList.add(releaseYear.getText());
//            infoList.add(IoConverter.integerToString(languageBox.getValue().getId()));
//            infoList.add(IoConverter.integerToString(originalLanguage.getValue().getId()));
//            infoList.add(rentalDuration.getText());
//            infoList.add(length.getText());
//            infoList.add(replacementCost.getText());
//            infoList.add(rentalRate.getText());
//            infoList.add(specialFeaturesString);
//            infoList.add(ratingBox.getValue());

        filmManager.addFilm(title.getText(), description.getText(), releaseYear.getText(), rentalDuration.getText(), length.getText(), replacementCost.getText(), rentalRate.getText(), ratingBox.getValue(), specialFeaturesString, languageBox.getValue(), originalLanguage.getValue(), categoryList);
//            filmManager.addFilm(infoList, categoryList, selectedActors);
    }
//}
    @FXML
    private void onOpenActorView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("actor-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 500, 600);
        stage.setTitle("Lämna tillbaka film!");
        ActorController controller = (ActorController) fxmlLoader.getController();
        controller.initialize(stage, this);
        stage.setScene(scene);
        stage.show();
        }
    }

