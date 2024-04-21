package org.example.testgame;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;


public class MarioApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MarioApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), MarioController.BG_WIDTH, 400);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);

        scene.setOnKeyPressed(e -> {
            if (e.getCode() == KeyCode.SPACE ||e.getCode() == KeyCode.W && MarioController.jump == false) {
                System.out.println("Najal space");
                MarioController.jump = true;
            }
            if (e.getCode() == KeyCode.S && MarioController.goDown == false) {
                System.out.println("Najal S");
                MarioController.goDown = true;
            }
            if (e.getCode() == KeyCode.A) {
                MarioController.left = true;
            }
            if (e.getCode() == KeyCode.D) {
                MarioController.right = true;
            }
        });
        scene.setOnKeyReleased(e -> {
            if (e.getCode() == KeyCode.A) {
                MarioController.left = false;
            }
            if (e.getCode() == KeyCode.D) {
                MarioController.right = false;
            }
            if (e.getCode() == KeyCode.ESCAPE) {
                MarioController.isPause = !MarioController.isPause;
            }

        });


        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}