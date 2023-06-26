package com.example.shoeaccountingcoursework.controllers;

import com.example.model.Category;
import com.example.model.Seasons;
import com.example.model.types.BootsType;
import com.example.model.types.SandalsType;
import com.example.model.types.ShoesType;
import com.example.model.types.SlippersType;
import com.example.service.FileImageService;
import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class NewItemController implements Initializable {

    @FXML
    private ComboBox<Category> boots_category_choose;

    @FXML
    private ComboBox<String> kind_choose;

    @FXML
    private TextField boots_color_field;

    @FXML
    private TextField boots_material_field;

    @FXML
    private AnchorPane boots_pane;

    @FXML
    private TextField boots_size_field;

    @FXML
    private TextField boots_weight_field;

    @FXML
    private TextField brend_field;

    @FXML
    private ComboBox<String> category_choose;

    @FXML
    private Button clear_btn;

    @FXML
    private Button close_btn;

    @FXML
    private ImageView img_block;

    @FXML
    private Button import_img_btn;

    @FXML
    private AnchorPane mainPane;

    @FXML
    private TextField model_field;

    @FXML
    private TextField price_field;

    @FXML
    private TextField sandals_appointment_field;

    @FXML
    private ComboBox<String> sandals_category_choose;

    @FXML
    private TextField sandals_color_field;

    @FXML
    private AnchorPane sandals_pane;

    @FXML
    private TextField sandals_size_field;

    @FXML
    private Button save_btn;

    @FXML
    private ComboBox<String> season_choose;

    @FXML
    private TextField shoes_color_field;

    @FXML
    private AnchorPane shoes_pane;

    @FXML
    private TextField shoes_size_field;

    @FXML
    private TextField slippers_appointment_field;

    @FXML
    private TextField slippers_color_field;

    @FXML
    private AnchorPane slippers_pane;

    @FXML
    private TextField slippers_size_field;

    @FXML
    private Button turn_off_btn;

    @FXML
    private ComboBox<String> type_choose;

    @FXML
    void clearFields(MouseEvent event) {

    }

    @FXML
    void closeWindow(MouseEvent event) {
        System.exit(200);
    }

    @FXML
    void hideWindow(MouseEvent event) {

        Stage window = (Stage) mainPane.getScene().getWindow();

        window.setIconified(true);
    }

    @FXML
    void importImage(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();

        File initialDirectory = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if (initialDirectory != null) {

            Image image = new Image(initialDirectory.getAbsolutePath());

            img_block.setImage(image);

        }
    }

    @FXML
    void saveItem(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initKinds();

        initCategory();

        initSeason();

        kind_choose.valueProperty().addListener((observableValue, s, t1) -> {

            type_choose.setDisable(false);

            if (t1 != null) {
                type_choose.getItems().clear();
                type_choose.setPromptText("Тип");
                initTypesByKind(t1);
                openOtherFields(t1);
            }

            if (s != null && !s.equals(t1)) {
                hidePane(s);
            }
        });
    }

    private void hidePane(String s) {

        switch (s) {
            case "Тапочки" -> slippers_pane.setVisible(false);
            case "Туфлі" -> shoes_pane.setVisible(false);
            case "Босоніжки" -> sandals_pane.setVisible(false);
            case "Чоботи" -> boots_pane.setVisible(false);
        }
    }

    private void openOtherFields(String kind) {

        if (kind.equals("Тапочки")) {

            slippers_pane.setVisible(true);

        } else if (kind.equals("Туфлі")) {

            shoes_pane.setVisible(true);

        } else if (kind.equals("Босоніжки")) {

            sandals_pane.setVisible(true);

        } else if (kind.equals("Чоботи")) {

            boots_pane.setVisible(true);

        }

    }

    private void initTypesByKind(String kind) {

        if (kind.equals("Тапочки")) {

            for (SlippersType slippers : SlippersType.values()) {
                type_choose.getItems().add(slippers.getType());
            }

        } else if (kind.equals("Туфлі")) {

            for (ShoesType shoes : ShoesType.values()) {
                type_choose.getItems().add(shoes.getType());
            }

        } else if (kind.equals("Босоніжки")) {

            for (SandalsType sandals : SandalsType.values()) {
                type_choose.getItems().add(sandals.getType());

            }
        } else if (kind.equals("Чоботи")) {

            for (BootsType boots : BootsType.values()) {
                type_choose.getItems().add(boots.getType());

            }
        }
    }

    void initKinds() {

        kind_choose.setItems(FXCollections.observableArrayList("Босоніжки", "Тапочки", "Чоботи", "Туфлі"));

    }

    void initCategory() {

        for (Category category : Category.values()) {
            category_choose.getItems().add(category.getCategory());
        }
    }

    void initSeason() {

        for (Seasons season : Seasons.values()) {
            season_choose.getItems().add(season.getSeasonName());
        }
    }


}
