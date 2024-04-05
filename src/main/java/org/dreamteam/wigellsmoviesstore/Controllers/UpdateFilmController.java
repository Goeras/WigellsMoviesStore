package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.StringConverter;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;
import org.hibernate.annotations.View;
import javafx.scene.*;

import java.io.IOException;
import java.util.ArrayList;
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
    private ChoiceBox<Language> originalLanguageBox;
    @FXML
    private ChoiceBox<Category> categoryBox;
    @FXML
    private ChoiceBox<Film> ratingBox;
    @FXML
    private TextField userNotice;

    private List<String> selectedSpecialFeatures = new ArrayList<>();
    DAOmanager daoManager = new DAOmanager();

    public void initialize() {
        viewManager = new ViewManager();

        Film currentFilm = new Film();

        title.setText(currentFilm.getTitle());
        description.setText(currentFilm.getDescription());
        releaseYear.setText(String.valueOf(currentFilm.getReleaseYear()));
        rentalDuration.setText(String.valueOf(currentFilm.getRentalDuration()));
        length.setText(String.valueOf(currentFilm.getLength()));
        replacementCost.setText(String.valueOf(currentFilm.getReplacementCost()));
        rentalRate.setText(String.valueOf(currentFilm.getRentalRate()));

        List<Language> languageList = daoManager.getLanguageDAO().readAllLanguages();
        ObservableList languageNames = FXCollections.observableArrayList();
        languageNames.addAll(languageList);
        languageBox.setItems(languageNames);

        List<Language> originalLanguageList = daoManager.getLanguageDAO().readAllLanguages();
        ObservableList originalLanguageNames = FXCollections.observableArrayList();
        originalLanguageNames.addAll(originalLanguageList);
        originalLanguageBox.setItems(originalLanguageNames);

        List<Category> categoryList = daoManager.getCategoryDAO().readAllCategories();
        ObservableList categoryNames = FXCollections.observableArrayList();
        categoryNames.addAll(categoryList);
        categoryBox.setItems(categoryNames);

        List<Film> filmList = daoManager.getFilmDAO().getAllFilms();
        ObservableList ratingNames = FXCollections.observableArrayList();
        for(Film film:filmList){
            String rating = film.getRating();
            ratingNames.add(rating);
        }
        ratingBox.setItems(ratingNames);


    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMovieView((Stage) topLabel.getScene().getWindow());
    }

    @FXML
   private void onSaveButtonClick(){
    ViewManager viewmanager = new ViewManager();
    String titleText = title.getText();
    boolean updateSuccessful = false;

    Film updatedFilm = new Film();

    if(titleText == null || titleText.isEmpty()){
        userNotice.setVisible(true);
        userNotice.setText("Du måste ange titeln för filmen.");
        return;
    }

       try {
           updatedFilm.setTitle(title.getText());
           updatedFilm.setDescription(description.getText());
           updatedFilm.setReleaseYear(Short.parseShort((releaseYear.getText())));
           updatedFilm.setRentalDuration(Byte.parseByte((releaseYear.getText())));
           updatedFilm.setLength(Short.parseShort((length.getText())));
           updatedFilm.setReplacementCost(Short.parseShort((releaseYear.getText())));
           updatedFilm.setRentalRate(Short.parseShort((releaseYear.getText())));

           String specialFeaturesString = String.join(",", selectedSpecialFeatures);
           updatedFilm.setSpecialFeatures(specialFeaturesString);

           updateSuccessful = true;
       }
       catch (NumberFormatException e){
            e.printStackTrace();
           userNotice.setVisible(true);
           userNotice.setText("Kontrollera inmatningarna");
       }
       if(updateSuccessful){
           userNotice.setVisible(true);
           userNotice.setText("Uppdateringen sparad");
       }

       }
    @FXML
    private void onCheckBoxClicked(){
        selectedSpecialFeatures.clear();

        if(behindTheScenes.isSelected()){
            selectedSpecialFeatures.add("Behind the scenes");
        }
        if(deletedScenes.isSelected()){
            selectedSpecialFeatures.add("Deleted scenes");
        }
        if(trailers.isSelected()){
            selectedSpecialFeatures.add("Trailer");
        }
        if(commentaries.isSelected()){
            selectedSpecialFeatures.add("Commentaries");
        }

    }
}
