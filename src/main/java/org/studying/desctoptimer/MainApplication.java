package org.studying.desctoptimer;

import io.reactivex.rxjava3.disposables.Disposable;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MainApplication extends Application {
    private final static String NAME = "DesktopTimer";
    private final static String FXML = "main-view.fxml";

    private static Stage primaryStage;
    private static MainController controller;
    private static FXMLLoader fxmlLoader;
    private static javafx.scene.Parent root;


    private static final MainTimer timer = new MainTimer();
    private Disposable subscription;

    public static void main(String[] args) {
        org.studying.desctoptimer.MainApplication.launch();
    }

    @Override
    public void init() throws Exception {
        System.out.println("Application inits");
        super.init();
    }

    @Override
    public void start(Stage stage){
        System.out.println("stage starts");

        initObjects(stage);
        setUI();
        setListeners();

        primaryStage.toFront();
        primaryStage.show();

    }

    @Override
    public void stop(){
        System.out.println("stage stops");

        // Останавливаем подписку, чтобы освободить ресурсы
        if (subscription != null && !subscription.isDisposed()) {
            subscription.dispose();
        }

        timer.stop();

        controller = null;
        fxmlLoader = null;
        primaryStage = null;
        System.exit(0);
        Platform.exit();
    }

    private void initObjects(Stage stage){
        try {
            primaryStage = stage;
            fxmlLoader = new FXMLLoader(MainController.class.getResource(FXML));
            root = fxmlLoader.load();
            controller = fxmlLoader.getController();

        }catch (Exception e){
            System.out.println("error 0 = " + e.getMessage());
        }
    }

    private void setUI(){
        try {
            //name
            primaryStage.setTitle(NAME);

            //scene
            Scene scene = new Scene(root, 284, 128);

            primaryStage.setScene(scene);
            primaryStage.setMinHeight(167);
            primaryStage.setMinWidth(300);

            //set images
            controller.setPauseImage();
            controller.setResetImage();

            //initial data
            controller.timer_value.setText("0.000");

        }catch (Exception e){
            System.out.println("error 1 = " + e.getMessage());
        }
    }

    private void setListeners(){
        try {
            // Подписываемся на Observable и выводим значение времени в консоль
            subscription = timer.getTime().subscribe(time -> {
                    Platform.runLater(()-> {
                        controller.timer_value.setText(time);
                        System.out.println("time is " + time);
                    }
                );
            });

            controller.OnStartPauseButton(() ->{
                if(timer.isRunning()){
                    timer.stop();
                   controller.setPauseImage();
                }else{
                    timer.run();
                    controller.setStartImage();
                }
            });

            controller.OnResetButton(timer::reset);

            primaryStage.setOnCloseRequest(evt -> {
                System.out.println("close app");
            });

        }catch (Exception e){
            System.out.println("error 2 = " + e.getMessage());
        }
    }


}