module org.studying.desctoptimer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;

    opens org.studying.desctoptimer to javafx.fxml;
    exports org.studying.desctoptimer;
}