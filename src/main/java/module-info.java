module org.studying.desctoptimer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.desktop;
    requires io.reactivex.rxjava3;

    opens org.studying.desctoptimer to javafx.fxml;
    exports org.studying.desctoptimer;
}