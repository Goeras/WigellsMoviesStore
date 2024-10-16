package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Actor;
import org.dreamteam.wigellsmoviesstore.HelloApplication;
import org.dreamteam.wigellsmoviesstore.Managers.ActorManager;
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
    private List<Actor> selectedActors;
    @FXML
    private VBox newActor;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    ActorManager actorManager;
    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private VBox upDateActor;
    private AddFilmController addFilmController;
    private UpdateFilmController updateFilmController;

    public void initialize(){
        actorManager = new ActorManager();
        DAOmanager daOmanager = new DAOmanager();
        List<Actor> actors = daOmanager.getActorDao().getAllActors();
        actorList = FXCollections.observableList(actors);
        actorListView.setItems(actorList);
        selectedActors = new ArrayList<>();
        actorListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        actorListView.setCellFactory(param -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    // Hantera CheckBox-händelse
                    Actor actor = getItem();
                    if (newVal) {
                        // Om CheckBox är markerad, lägg till aktören i listan för markerade objekt
                        selectedActors.add(actor);
                    } else {
                        // Om CheckBox är avmarkerad, ta bort aktören från listan för markerade objekt
                        selectedActors.remove(actor);
                    }
                    System.out.println("CheckBox " + (newVal ? "selected" : "deselected"));
                });
            }

            @Override
            protected void updateItem(Actor actor, boolean empty) {
                super.updateItem(actor, empty);
                if (empty || actor == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(selectedActors.contains(actor));
                    checkBox.setText(actor.toString());
                    setGraphic(checkBox);
                }
            }
        });
    }
    public void initialize(Stage stage, AddFilmController controller){
        addFilmController = controller;

        actorManager = new ActorManager();
        DAOmanager daOmanager = new DAOmanager();
        List<Actor> actors = daOmanager.getActorDao().getAllActors();
        actorList = FXCollections.observableList(actors);
        actorListView.setItems(actorList);
        selectedActors = new ArrayList<>();
        actorListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        actorListView.setCellFactory(param -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    // Hantera CheckBox-händelse
                    Actor actor = getItem();
                    if (newVal) {
                        // Om CheckBox är markerad, lägg till aktören i listan för markerade objekt
                        selectedActors.add(actor);
                    } else {
                        // Om CheckBox är avmarkerad, ta bort aktören från listan för markerade objekt
                        selectedActors.remove(actor);
                    }
                    System.out.println("CheckBox " + (newVal ? "selected" : "deselected"));
                });
            }

            @Override
            protected void updateItem(Actor actor, boolean empty) {
                super.updateItem(actor, empty);
                if (empty || actor == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(selectedActors.contains(actor));
                    checkBox.setText(actor.toString());
                    setGraphic(checkBox);
                }
            }
        });
    }

    public void initialize(Stage stage, UpdateFilmController controller){
        updateFilmController = controller;

        actorManager = new ActorManager();
        DAOmanager daOmanager = new DAOmanager();
        List<Actor> actors = daOmanager.getActorDao().getAllActors();
        actorList = FXCollections.observableList(actors);
        actorListView.setItems(actorList);
        selectedActors = new ArrayList<>();
        actorListView.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        actorListView.setCellFactory(param -> new ListCell<>() {
            private final CheckBox checkBox = new CheckBox();

            {
                checkBox.selectedProperty().addListener((obs, oldVal, newVal) -> {
                    // Hantera CheckBox-händelse
                    Actor actor = getItem();
                    if (newVal) {
                        // Om CheckBox är markerad, lägg till aktören i listan för markerade objekt
                        selectedActors.add(actor);
                    } else {
                        // Om CheckBox är avmarkerad, ta bort aktören från listan för markerade objekt
                        selectedActors.remove(actor);
                    }
                    System.out.println("CheckBox " + (newVal ? "selected" : "deselected"));
                });
            }

            @Override
            protected void updateItem(Actor actor, boolean empty) {
                super.updateItem(actor, empty);
                if (empty || actor == null) {
                    setGraphic(null);
                } else {
                    checkBox.setSelected(selectedActors.contains(actor));
                    checkBox.setText(actor.toString());
                    setGraphic(checkBox);
                }
            }
        });
    }
    @FXML
    private void onOpenUpdateFilmView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addFilm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        updateFilmController.initialize(selectedActors);
        Stage stage = (Stage) newActor.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onOpenAddFilmView() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("addFilm-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 600, 600);
        addFilmController.initialize(selectedActors);
        Stage stage = (Stage) newActor.getScene().getWindow();
        stage.close();
    }
    @FXML
    private void onNewActorButton(){
        newActor.setManaged(true);
        newActor.setVisible(true);
    }
    @FXML
    private void onSaveActorButton(){
        actorManager.newActor(firstName.getText(), lastName.getText());
    }
    @FXML
    private void onUpdateActorButton(){
        upDateActor.setVisible(true);
        upDateActor.setManaged(true);
        firstNameField.setText(selectedActors.get(0).getFirstName());
        lastNameField.setText(selectedActors.get(0).getLastName());
    }
    @FXML
    private void onSaveUpdate(){
        actorManager.updateActor(selectedActors.get(0).getId(), firstNameField.getText(), lastNameField.getText());
        firstNameField.setText("");
        lastNameField.setText("");
        upDateActor.setManaged(false);
        upDateActor.setVisible(false);
    }
    @FXML
    private void onDeleteActorButton(){
        actorManager.deleteActor(selectedActors.get(0).getId());
        actorList.remove(selectedActors.get(0));
        selectedActors.remove(selectedActors.get(0));
        firstNameField.setText("");
        lastNameField.setText("");
        upDateActor.setManaged(false);
        upDateActor.setVisible(false);
    }
}
