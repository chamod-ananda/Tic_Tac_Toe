package com.assignment.tictactoe.service.tic_tac_toe;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AppInitializer extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        stage.setScene(new Scene(FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/view/game.fxml")))));
        stage.setResizable(false);
        stage.setTitle("Tic-Tac-Toe");
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/images/icon_img.png")));
        stage.show();
        stage.centerOnScreen();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
