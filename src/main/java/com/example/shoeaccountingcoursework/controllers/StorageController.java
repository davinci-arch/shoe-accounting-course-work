package com.example.shoeaccountingcoursework.controllers;

import com.example.dao.repository.generalrepo.ModelTablesRepository;
import com.example.model.Category;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.RangeSlider;

import java.net.URL;
import java.util.ResourceBundle;

public class StorageController implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private TextField brend_field;

    @FXML
    private ComboBox<?> category_choose;

    @FXML
    private CheckBox check_category_1;

    @FXML
    private CheckBox check_category_2;

    @FXML
    private CheckBox check_category_3;

    @FXML
    private CheckBox check_category_4;

    @FXML
    private CheckBox check_category_5;

    @FXML
    private CheckBox check_category_6;

    @FXML
    private Label check_category_count_1;

    @FXML
    private Label check_category_count_2;

    @FXML
    private Label check_category_count_3;

    @FXML
    private Label check_category_count_4;

    @FXML
    private Label check_category_count_5;

    @FXML
    private Label check_category_count_6;

    @FXML
    private Button close_btn;

    @FXML
    private Button confirm_slider_btn;

    @FXML
    private TableView<?> data_view;

    @FXML
    private Button delete_btn;

    @FXML
    private TextField high_bound_slider;

    @FXML
    private ImageView img_block;

    @FXML
    private Button import_img_btn;

    @FXML
    private TextField low_bound_slider;

    @FXML
    private TextField model_field;

    @FXML
    private Button more_btn;

    @FXML
    private TextField price_field;

    @FXML
    private Button save_btn;

    @FXML
    private TextField search_field;

    @FXML
    private RangeSlider slider;

    @FXML
    private Button turn_off_btn;

    @FXML
    private TextField type_field;

    private ModelTablesRepository model = new ModelTablesRepository();

    @FXML
    void changeRangeSlider(MouseEvent event) {
        low_bound_slider.setText(String.valueOf((int)slider.getLowValue()));
        high_bound_slider.setText(String.valueOf((int)slider.getHighValue()));
    }

    @FXML
    void confirmRangePrice(MouseEvent event) {

    }

    @FXML
    void filterDataInTable(KeyEvent event) {

    }

    @FXML
    void setNewHighBoundSlider(KeyEvent event) {
        if (!high_bound_slider.getText().isEmpty()) {
            slider.setHighValue(Double.parseDouble(high_bound_slider.getText()));
        }
    }

    @FXML
    void setNewLowBoundSlider(KeyEvent event) {
        if (!low_bound_slider.getText().isEmpty()) {
            slider.setLowValue(Double.parseDouble(low_bound_slider.getText()));
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDefaultSliderBounds();

        initCountOfCategory();
    }

    private void initCountOfCategory() {

        String patternSet = "(%s)";

        int child = model.getCountOfCategory(Category.CHILD);
        int male = model.getCountOfCategory(Category.MALE);
        int female = model.getCountOfCategory(Category.FEMALE);
        int unisex = model.getCountOfCategory(Category.UNISEX);
        int orthopedic = model.getCountOfCategory(Category.ORTHOPEDIC);
        int homely = model.getCountOfCategory(Category.HOMELY);


        check_category_count_1.setText(String.format(patternSet, male));
        check_category_count_2.setText(String.format(patternSet, female));
        check_category_count_3.setText(String.format(patternSet, unisex));
        check_category_count_4.setText(String.format(patternSet, orthopedic));
        check_category_count_5.setText(String.format(patternSet, homely));
        check_category_count_6.setText(String.format(patternSet, child));
    }

    private void setDefaultSliderBounds() {
        slider.setLowValue(slider.getMin());
        slider.setHighValue(slider.getMax());

        high_bound_slider.setText(String.valueOf((int)slider.getMax()));
        low_bound_slider.setText(String.valueOf((int)slider.getMin()));
    }
}
