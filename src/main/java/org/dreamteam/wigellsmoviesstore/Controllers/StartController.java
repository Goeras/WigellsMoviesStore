package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.CurrentStore;
import org.dreamteam.wigellsmoviesstore.DAO.DAOmanager;
import org.dreamteam.wigellsmoviesstore.Entitys.Store;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.IOException;
import java.util.List;

public class StartController {
    @FXML
    private Label topLabel;
    @FXML
    private ChoiceBox<Store> storeBox;
   private ViewManager viewManager;
   private DAOmanager daOmanager;

    public void initialize(){
        viewManager = new ViewManager();
        daOmanager = new DAOmanager();
        List<Store> storeList = daOmanager.getStoreDAO().getAllStores();
        ObservableList<Store> storeObservableList = FXCollections.observableList(storeList);
        storeBox.setItems(storeObservableList);
    }
    @FXML
    protected void onMenuButtonClick() throws IOException {
        Store store = storeBox.getValue();
        if(store != null) {
            CurrentStore.getInstance().setCurrentStore(store);
            viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
        }
    }
}