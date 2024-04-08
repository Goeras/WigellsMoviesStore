package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.CurrentFilm;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Entitys.Language;
import org.dreamteam.wigellsmoviesstore.Entitys.Store;
import org.dreamteam.wigellsmoviesstore.IoConverter;
import org.dreamteam.wigellsmoviesstore.IoValidator;
import org.dreamteam.wigellsmoviesstore.Managers.FilmManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

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
    private ListView<Category> categoryBox;
    @FXML
    private ChoiceBox<String> ratingBox;
    @FXML
    private TextField userNotice;
    private IoValidator ioValidator;

    private List<String> selectedSpecialFeatures = new ArrayList<>();
    DAOmanager daoManager = new DAOmanager();
    Store store = CurrentStore.getInstance().getCurrentStore();
    Film film = CurrentFilm.getInstance().getCurrentFilm();
    FilmManager filmManager = new FilmManager();

    public void initialize() {
        CurrentStore.getInstance().updateCurrentStore();
        viewManager = new ViewManager();

        title.setText(film.getTitle());
        description.setText(film.getDescription());
        releaseYear.setText(String.valueOf(film.getReleaseYear()));
        rentalDuration.setText(String.valueOf(film.getRentalDuration()));
        length.setText(String.valueOf(film.getLength()));
        replacementCost.setText(String.valueOf(film.getReplacementCost()));
        rentalRate.setText(String.valueOf(film.getRentalRate()));

        List<Language> languageList = daoManager.getLanguageDAO().readAllLanguages();
        ObservableList<Language> languageNames = FXCollections.observableArrayList();
        languageNames.addAll(languageList);
        languageBox.setItems(languageNames);
        languageBox.getSelectionModel().select(film.getLanguage());

        List<Language> originalLanguageList = daoManager.getLanguageDAO().readAllLanguages();
        ObservableList<Language> originalLanguageNames = FXCollections.observableArrayList();
        originalLanguageNames.addAll(originalLanguageList);
        originalLanguageBox.setItems(originalLanguageNames);
        originalLanguageBox.getSelectionModel().select(film.getOriginalLanguage());

        List<Category> categoryList = daoManager.getCategoryDAO().readAllCategories();
        ObservableList<Category> categoryNames = FXCollections.observableArrayList();
        categoryNames.addAll(categoryList);
        categoryBox.setItems(categoryNames);
        categoryBox.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);

        List<Film> filmList = daoManager.getFilmDAO().getAllFilms();
        ObservableList<String> ratingNames = FXCollections.observableArrayList();
        for(Film film:filmList){
            String rating = film.getRating();
            ratingNames.add(rating);
        }
        ratingBox.setItems(ratingNames);
        ratingBox.getSelectionModel().select(film.getRating());
    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMovieView((Stage) topLabel.getScene().getWindow());
    }
    /*
    @FXML
   private void onSaveButtonClick() {
        ViewManager viewmanager = new ViewManager();
        String titleText = title.getText();
        boolean updateSuccessful = false;

        Film updatedFilm = new Film();

        if (titleText == null || titleText.isEmpty()) {
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
        } catch (NumberFormatException e) {
            e.printStackTrace();
            userNotice.setVisible(true);
            userNotice.setText("Kontrollera inmatningarna");
        }
        if (updateSuccessful) {
            userNotice.setVisible(true);
            userNotice.setText("Uppdateringen sparad");
        }
    }

    @FXML
    private void onSaveButtonClick(){
        filmManager.updateFilm(title.getText(), description.getText(), Short.parseShort(releaseYear.getText()), languageBox.getValue(), originalLanguageBox.getValue(), Byte.parseByte(rentalDuration.getText()), Short.parseShort(rentalRate.getText()), Short.parseShort(length.getText()), Double.parseDouble(replacementCost.getText()), ratingBox.getValue(), selectedSpecialFeatures.toString(), categoryBox.getItems());
    }
    */
    @FXML
    private void onSaveButtonClick(){
        boolean validTitle = ioValidator.validateStringNotEmpty(title.getText());
        boolean validLanguage = languageBox != null;

        if(!validTitle){
            ioValidator.displayAlert("Error","Du måste fylla i titel");
        }
        if(!validLanguage){
            ioValidator.displayAlert("Error","Du måste fylla i titel");
        }
        if(validTitle && validLanguage){
            filmManager.updateFilm(title.getText(), description.getText(), Short.parseShort(releaseYear.getText()), languageBox.getValue(), originalLanguageBox.getValue(), Byte.parseByte(rentalDuration.getText()), Double.parseDouble(rentalRate.getText()), Short.parseShort(length.getText()), Double.parseDouble(replacementCost.getText()), ratingBox.getValue(), IoConverter.specialFeaturesToString(selectedSpecialFeatures), categoryBox.getSelectionModel().getSelectedItems());

            IoValidator.displayConfirmation("Sparat","Information uppdaterad");
        } else {
            System.out.println("fel");
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
