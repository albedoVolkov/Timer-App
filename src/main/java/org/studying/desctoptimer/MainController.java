package org.studying.desctoptimer;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    public Label timer_value;

    private final static String stopImagePath =  "pause100.jpg";
    private final static String startImagePath =  "start100.jpg";
    private final static String resetImagePath =  "reset100.jpg";

    @FXML
    private ImageView resetImageView;
    @FXML
    private VBox resetImageContainer;
    private Runnable onResetButtonClicked;

    @FXML
    private ImageView startPauseImageView;
    @FXML
    private VBox startPauseImageContainer;
    private Runnable onStartPauseButtonClicked;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        //listeners
        startPauseImageContainer.setOnMouseClicked(event -> {
            if (onStartPauseButtonClicked != null) {
                onStartPauseButtonClicked.run();
            }
        });

        resetImageContainer.setOnMouseClicked(event -> {
            if (onResetButtonClicked != null) {
                onResetButtonClicked.run();
            }
        });

    }

    public void setStartImage() {
        startPauseImageView.setImage(createImage(this,stopImagePath));
    }

    public void setPauseImage() {
        startPauseImageView.setImage(createImage(this,startImagePath));
    }

    public void setResetImage() {
      resetImageView.setImage(createImage(this,resetImagePath));
    }



    public void OnStartPauseButton(Runnable callback) {
        this.onStartPauseButtonClicked = callback;
    }

    public void OnResetButton(Runnable callback) {
        this.onResetButtonClicked = callback;
    }


    public static Image createImage(Object context, String resourceName) {
        URL _url = context.getClass().getResource(resourceName);
        assert _url != null;
        return new Image(_url.toExternalForm());
    }


}