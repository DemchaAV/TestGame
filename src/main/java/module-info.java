module org.example.testgame {
    requires javafx.controls;
    requires javafx.fxml;


    opens org.example.testgame to javafx.fxml;
    exports org.example.testgame;
}