package com.example.shoeaccountingcoursework.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Button btn1;

    @FXML
    private Button btn2;

    @FXML
    private AnchorPane first_pane;

    @FXML
    private TextField id_text;

    @FXML
    private AnchorPane second_pane;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        btn1.setOnMouseClicked(event -> {
            first_pane.setVisible(true);
            id_text.setText("Hello my dear");
        });

        second_pane.setVisible(true);

    }
}
