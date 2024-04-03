package org.dreamteam.wigellsmoviesstore.Controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.dreamteam.wigellsmoviesstore.Managers.ViewManager;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class UpdateStaffController {
    private ViewManager viewManager;
    @FXML
    private Label topLabel;
    @FXML
    private ImageView imageView;
    private Image image;

    public void initialize(){
        viewManager = new ViewManager();
    }
    public void onBackButtonClick() throws IOException {
        viewManager.showStaffView((Stage) topLabel.getScene().getWindow());
    }

    public void onUploadPictureButtonClick() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Välj bild");
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Bilder", "*.jpg", "*.png", "*.jpeg"));
        File selectedFile = fileChooser.showOpenDialog(null);

        if(selectedFile!=null){
            try{
                InputStream inputStream = new FileInputStream(selectedFile);
                image = new Image(inputStream);
            }
            catch (Exception e){
                e.printStackTrace();
                System.out.println("Fel vid inläsning av bild");
            }
        }
        imageView.setFitWidth(60);
        imageView.setFitHeight(70);
        imageView.setImage(image);
    }
}
