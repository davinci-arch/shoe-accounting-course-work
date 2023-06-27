package com.example.shoeaccountingcoursework.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private TextField boots_color_field;

    @FXML
    private ComboBox<?> boots_fastener_choose;

    @FXML
    private TextField boots_material_field;

    @FXML
    private AnchorPane boots_pane;

    @FXML
    private TextField boots_size_field;

    @FXML
    private TextField boots_weight_field;

    @FXML
    private TableColumn<?, ?> brend_column;

    @FXML
    private TextField brend_field;

    @FXML
    private ComboBox<?> category_choose;

    @FXML
    private TableColumn<?, ?> category_column;

    @FXML
    private Button close_btn;

    @FXML
    private TableView<?> data_view;

    @FXML
    private Button delete_btn;

    @FXML
    private ImageView img_block;

    @FXML
    private Button import_img_btn;

    @FXML
    private TableColumn<?, ?> model_column;

    @FXML
    private TextField model_field;

    @FXML
    private Button more_btn;

    @FXML
    private TableColumn<?, ?> price_column;

    @FXML
    private TextField price_field;

    @FXML
    private TextField sandals_appointment_field;

    @FXML
    private TextField sandals_color_field;

    @FXML
    private ComboBox<?> sandals_fastener_choose;

    @FXML
    private AnchorPane sandals_pane;

    @FXML
    private TextField sandals_size_field;

    @FXML
    private Button save_btn;

    @FXML
    private ComboBox<?> season_choose;

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
    private ComboBox<?> type_choose;

    @FXML
    private TableColumn<?, ?> type_column;

    @FXML
    void add_item(MouseEvent event) {

    }

    @FXML
    void importImage(MouseEvent event) {

    }

    @FXML
    void moreInfo(MouseEvent event) {

    }

    @FXML
    void removeItem(MouseEvent event) {

    }

    @FXML
    void saveItem(MouseEvent event) {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
