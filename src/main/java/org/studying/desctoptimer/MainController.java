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
    public Button resetButton;
    private Runnable onResetButtonClicked;
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

        resetButton.setOnAction(event -> {
            if (onResetButtonClicked != null) {
                onResetButtonClicked.run();
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

    public void OnResetButton(Runnable callback) {
        this.onResetButtonClicked = callback;
    }


}