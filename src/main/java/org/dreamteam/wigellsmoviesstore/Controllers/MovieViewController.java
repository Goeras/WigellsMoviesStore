package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Entitys.Film;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.dreamteam.wigellsmoviesstore.IoConverter.stringToSimpleIntegerProperty;
import static org.dreamteam.wigellsmoviesstore.IoConverter.stringToSimpleStringProperty;

public class MovieViewController {
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
    public void setTable(ObservableList<Film> observableList){
        idColumn.setCellValueFactory(cellData -> stringToSimpleIntegerProperty((cellData.getValue().getFilmId()));
        titleColumn.setCellValueFactory(cellData -> cellData.getValue().getTitle());
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategory().getName());
        languageColumn.setCellValueFactory(cellData -> cellData.getValue().getLanguage());
        movieTable.setItems(observableList);
    }
    public void onSearchAllButton(){
        System.out.println("Hämta alla filmer via dao-manager");
        movieTable.setItems(filmList);
    }
    public void onSearchByIdButton(){
        System.out.println("Hämta korrekt film från DAO och lägg in den info vi vill ha");
    }
}
