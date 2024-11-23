package org.studying.desctoptimer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

//    final static String ICON = "images/icon_main.png";

    @FXML
    public Button startButton;
    private Runnable onStartButtonClicked;
    @FXML
    public Button stopButton;
    private Runnable onStopButtonClicked;
    @FXML
    public Label timer_value;

//    @FXML
//    private ImageView imageView;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
//        InputStream iconStream = getClass().getResourceAsStream(ICON);
//        Image image = new Image(iconStream);
//        imageView.setImage(image);

        startButton.setOnAction(event -> {
            if (onStartButtonClicked != null) {
                onStartButtonClicked.run();
            }
        });

        stopButton.setOnAction(event -> {
            if (onStopButtonClicked != null) {
                onStopButtonClicked.run();
            }
        });
    }


    public void OnStartButton(Runnable callback) {
        this.onStartButtonClicked = callback;
    }

    public void OnStopButton(Runnable callback) {
        this.onStopButtonClicked = callback;
    }


}