package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.io.IOException;

public class StaffViewController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private ImageView imageView;

    public void initialize(){
        viewManager = new ViewManager();
        try {
            Image image = new Image("file:src/images/test.jpg");
            imageView.setFitWidth(100);
            imageView.setFitHeight(120);
            imageView.setImage(image);
        }
        catch (Exception e){
            System.out.println("fel vid bildladdning");
        }

    }
    @FXML
    protected void onBackButtonClick() throws IOException {
        viewManager.showMenuView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onNewStaffButtonClick() throws IOException {
        viewManager.showNewStaffView((Stage) topLabel.getScene().getWindow());
    }
    @FXML
    private void onUpdateStaffButtonClick() throws IOException{
        viewManager.showUpdateStaffView((Stage) topLabel.getScene().getWindow());
    }
}
