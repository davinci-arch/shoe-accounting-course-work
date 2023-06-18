package com.example.shoeaccountingcoursework;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("storage-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1056, 799);
        stage.setTitle("Hello!");
        stage.initStyle(StageStyle.TRANSPARENT); // disabled top panel
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}