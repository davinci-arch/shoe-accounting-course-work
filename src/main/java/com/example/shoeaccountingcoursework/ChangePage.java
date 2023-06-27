package com.example.shoeaccountingcoursework;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class ChangePage {

    private double x;
    private double y;

    private void moveToPage(String pageName) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageName));

        Parent root = fxmlLoader.load();

        Scene scene = new Scene(root, 1056, 799);

        Stage stage = new Stage();

        root.setOnMousePressed(event -> {
            x = event.getSceneX();
            y = event.getSceneY();
        });

        root.setOnMouseDragged(event -> {
            stage.setX(event.getScreenX() - x);
            stage.setY(event.getScreenY() - y);

            stage.setOpacity(.8);
        });

        root.setOnMouseReleased(event -> {

            stage.setOpacity(1);
        });


        stage.initStyle(StageStyle.TRANSPARENT);

        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();


    }

    public void moveToAddPage() throws IOException {

        moveToPage("add-info.fxml");

    }

    public void moveToMainPage() throws IOException {

        moveToPage("storage-view.fxml");
    }

    public void moveToMoreInfoPage() throws IOException {

        moveToPage("more-info.fxml");

    }
}
