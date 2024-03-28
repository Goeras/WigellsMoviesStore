package org.dreamteam.wigellsmoviesstore;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Controllers.ViewManager;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        ViewManager viewManager = new ViewManager();
        viewManager.showStartView(stage);
    }

    public static void main(String[] args) {
        launch();
    }
}