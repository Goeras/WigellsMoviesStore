package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.CurrentFilm;
import org.dreamteam.wigellsmoviesstore.CurrentStaff;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.*;

import org.dreamteam.wigellsmoviesstore.IoValidator;

import org.dreamteam.wigellsmoviesstore.Managers.FilmManager;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.dreamteam.wigellsmoviesstore.IoConverter.*;

public class MovieViewController {
    @FXML
    private Label titleLabel;
    @FXML
    private Label rentalCostLabel;
    @FXML
    private Label replacementFeeLabel;
    @FXML
    private Label categoryLabel;
    @FXML
    private Label actorLabel;
    @FXML
    private ChoiceBox<Actor> actorBox;
    @FXML
    private Label releaseLabel;
    @FXML
    private Label languageLabel;
    @FXML
    private Label lengthLabel;
    @FXML
    private TextField searchField;
    @FXML
    private TextField filterField;
    @FXML
    private Label topLabel;
    @FXML
    private TableView<Film> movieTable;
    @FXML
    private TableColumn<Film, Integer> idColumn;
    @FXML
    private TableColumn<Film, String> titleColumn;
    @FXML
    private TableColumn<Film, String> categoryColumn;
    @FXML
    private TableColumn<Film, String> languageColumn;
    FilmManager filmManager;
    private ViewManager viewManager;
    private ObservableList<Film> filmList;
    Film film;
    Store store;

    public void initialize(){
        CurrentStore.getInstance().updateCurrentStore();
        viewManager = new ViewManager();
        filmManager = new FilmManager();
        store = CurrentStore.getInstance().getCurrentStore();
        List<Film> list = new ArrayList<>();
        filmList = FXCollections.observableList(list);
        setTable(filmList);


    }
    @FXML
    private void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    public void onNewFilmButton() throws IOException{
        viewManager.showNewFilmView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    public void onUpdateFilmButton() throws IOException {
        CurrentFilm.getInstance().setCurrentFilm(film);
        viewManager.showUpdateFilmView((Stage) topLabel.getScene().getWindow());
    }
    public void setTable(ObservableList<Film> observableList) {

        idColumn.setCellValueFactory(cellData -> integerToSimpleIntegerProperty(cellData.getValue().getFilmId()).asObject());
        titleColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getTitle()));
        categoryColumn.setCellValueFactory(cellData -> categoriesToStringProperty(cellData.getValue().getCategoryList()));
        languageColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getLanguage().getName()));
        movieTable.setItems(observableList);
    }
    @FXML
    public void onSearchAllButton(){
        filmList.clear();

        DAOmanager daoManager = new DAOmanager();
        List<Film> filmsFromDatabase = daoManager.getFilmDAO().getAllFilms();
        filmList.addAll(filmsFromDatabase);

        movieTable.setItems(filmList);
    }

    @FXML
    public void onFilterByNameButton(){

        String filterText = filterField.getText().trim().toLowerCase();
        ObservableList<Film> filteredList = FXCollections.observableArrayList(filmList);
        filteredList.setAll(filmList.filtered(film -> film.getTitle().toLowerCase().contains(filterText)));

        movieTable.setItems(filteredList);
    }
    @FXML
    public void onSearchByIdButton() throws IOException {
        String inputText = searchField.getText();

            if (IoValidator.validateInteger(inputText)) {
                int filmId = Integer.parseInt(inputText);
                DAOmanager daoManager = new DAOmanager();
                Film searchedFilm = daoManager.getFilmDAO().getFilmById(filmId);

                if (searchedFilm != null) {
                    setFilmInfo(searchedFilm);
                    film = searchedFilm;
                    CurrentFilm.getInstance().setCurrentFilm(searchedFilm);
                }
            }
        }
    private void setFilmInfo(Film film) {

        titleLabel.setText(film.getTitle());
        rentalCostLabel.setText(String.valueOf(film.getRentalRate()));
        replacementFeeLabel.setText(String.valueOf(film.getReplacementCost()));
        categoryLabel.setText(film.getCategoryList().toString());
        actorBox.setItems(FXCollections.observableList(film.getActors()));
        releaseLabel.setText(String.valueOf(film.getReleaseYear()));
        languageLabel.setText(film.getLanguage().getName());
        lengthLabel.setText(String.valueOf(film.getLength()));
    }
}
