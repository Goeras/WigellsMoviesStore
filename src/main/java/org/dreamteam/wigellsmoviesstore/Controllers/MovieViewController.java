package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Category;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.dreamteam.wigellsmoviesstore.IoConverter.*;

public class MovieViewController {
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
    private ViewManager viewManager;
    private ObservableList<Film> filmList;

    public void initialize(){

        List<Film> list = new ArrayList<>();
        filmList = FXCollections.observableList(list);
        setTable(filmList);
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
    public void setTable(ObservableList<Film> observableList) {

        idColumn.setCellValueFactory(cellData -> integerToSimpleIntegerProperty(cellData.getValue().getFilmId()).asObject());
        titleColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getTitle()));
        categoryColumn.setCellValueFactory(cellData -> categoriesToStringProperty(cellData.getValue().getCategoryList()));
        languageColumn.setCellValueFactory(cellData -> stringToSimpleStringProperty(cellData.getValue().getLanguage().getName()));
        movieTable.setItems(observableList);
    }
    public void onSearchAllButton(){
        filmList.clear();

        DAOmanager daoManager = new DAOmanager();
        List<Film> filmsFromDatabase = daoManager.getFilmDAO().getAllFilms();
        filmList.addAll(filmsFromDatabase);

        movieTable.setItems(filmList);
    }

    public void onFilterByNameButton(){
    String filterText = filterField.getText().trim().toLowerCase();
    ObservableList<Film> filteredList = FXCollections.observableArrayList(filmList);
    filteredList.setAll(filmList.filtered(film -> film.getTitle().toLowerCase().contains(filterText)));

    movieTable.setItems(filteredList);
    }
    public void onSearchByIdButton(){
        System.out.println("Hämta korrekt film från DAO och lägg in den info vi vill ha");
    }
}
