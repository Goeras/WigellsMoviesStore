package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Actor;
import org.dreamteam.wigellsmoviesstore.HelloApplication;
import org.dreamteam.wigellsmoviesstore.Managers.FilmManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ActorController {
    @FXML
    private ListView<Actor> actorListView;
    private ObservableList<Actor> actorList;
    @FXML
    private CheckBox checkBox;
    private List<Actor> chosenActor;

    public void initialize(){
        FilmManager filmManager = new FilmManager();
        DAOmanager daOmanager = new DAOmanager();
        List<Actor> actors = daOmanager.getActorDao().getAllActors();
        actorList = FXCollections.observableList(actors);
        actorListView.setItems(actorList);
        chosenActor = new ArrayList<>();

        actorListView.setCellFactory(param -> new ListCell<Actor>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    // Hantera CheckBox-händelse
                    System.out.println("CheckBox " + (newVal ? "selected" : "deselected"));
                    Actor actor = getItem();
                    chosenActor.add(actor);
                    System.out.println(getItem().getFirstName());

                });
            }

            @Override
            protected void updateItem(Actor item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setGraphic(null);
                } else {
                    checkBox.setText(item.toString());
                    setGraphic(checkBox);
                }
            }
        });
    }
    @FXML
    private void onOpenActorView() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addFilm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        stage.setTitle("Lämna tillbaka film!");
        stage.setScene(scene);
        stage.show();
        AddFilmController controller = (AddFilmController) fxmlLoader.getController();
        controller.initialize(chosenActor);

    }
}
