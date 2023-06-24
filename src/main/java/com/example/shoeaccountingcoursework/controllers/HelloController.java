package com.example.shoeaccountingcoursework.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import org.controlsfx.control.RangeSlider;
import tornadofx.MaskPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class HelloController implements Initializable {

    @FXML
    private AnchorPane content;

    @FXML
    private TextField high_bound_slider_field;

    @FXML
    private MaskPane mask_pane;

    @FXML
    private RangeSlider id_slider;

    @FXML
    private TextField low_bound_slider_field;

    @FXML
    private Label welcomeText;

    @FXML
    void onHelloButtonClick(ActionEvent event) throws InterruptedException {
        ExecutorService threads = Executors.newFixedThreadPool(2);
        content.setVisible(false);
        threads.submit(() ->{
            mask_pane.setVisible(true);

            try {
                TimeUnit.SECONDS.sleep(7);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            mask_pane.setVisible(false);

            content.setVisible(true);
        });

        if (!threads.isShutdown()) {
            threads.shutdown();
        }

    }


    @FXML
    void changeSliderBounds(MouseEvent event) {
        low_bound_slider_field.setText(String.valueOf((int)id_slider.getLowValue()));
        high_bound_slider_field.setText(String.valueOf((int)id_slider.getHighValue()));
    }

    @FXML
    void changeLowBound(KeyEvent event) {
        if (!low_bound_slider_field.getText().isEmpty()) {

            id_slider.setLowValue(Double.parseDouble(low_bound_slider_field.getText()));
        }

        if (!high_bound_slider_field.getText().isEmpty()) {

            id_slider.setHighValue(Double.parseDouble(high_bound_slider_field.getText()));
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        mask_pane.setVisible(false);
        initSlider();
    }

    private void initSlider() {
        id_slider.setHighValue((int)id_slider.getMax());
        id_slider.setLowValue((int)id_slider.getMin());

        low_bound_slider_field.setText(String.valueOf((int)id_slider.getMin()));
        high_bound_slider_field.setText(String.valueOf((int)id_slider.getMax()));
    }
}
