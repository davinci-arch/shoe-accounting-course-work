package com.example.shoeaccountingcoursework.controllers;

import com.example.dao.repository.ConvertorEnum;
import com.example.dao.repository.generalrepo.ModelTablesRepository;
import com.example.model.Category;
import com.example.model.FootwearAbstract;
import com.example.model.types.TypeFootwear;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import org.controlsfx.control.RangeSlider;

import java.math.BigDecimal;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class StorageController implements Initializable {

    @FXML
    private Button add_btn;

    @FXML
    private TableColumn<FootwearAbstract, String> brend_column;

    @FXML
    private TextField brend_field;

    @FXML
    private ComboBox<String> category_choose;

    @FXML
    private TableColumn<FootwearAbstract, String> category_column;

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
    private TableView<FootwearAbstract> data_view;

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
    private TableColumn<FootwearAbstract, String> model_column;

    @FXML
    private TextField model_field;

    @FXML
    private Button more_btn;

    @FXML
    private TableColumn<FootwearAbstract, Integer> price_column;

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
    private TableColumn<FootwearAbstract, String> type_column;

    @FXML
    private TextField type_field;

    private ModelTablesRepository model = new ModelTablesRepository();

    private ObservableList<FootwearAbstract> data = FXCollections.observableArrayList();

    @FXML
    void changeRangeSlider(MouseEvent event) {
        low_bound_slider.setText(String.valueOf((int) slider.getLowValue()));
        high_bound_slider.setText(String.valueOf((int) slider.getHighValue()));
    }

    @FXML
    void confirmRangePrice(MouseEvent event) {
        FilteredList<FootwearAbstract> filteredList = new FilteredList<>(data, (foot) -> betweenPrice(foot.getPrice()));

        SortedList<FootwearAbstract> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(data_view.comparatorProperty());
        data_view.setItems(sortedList);
    }

    private boolean betweenPrice(BigDecimal price) {

        if (price.doubleValue() >= slider.getLowValue() && price.doubleValue() <= slider.getHighValue()) {
            return true;
        }

        return false;
    }

    @FXML
    void filterDataInTable(KeyEvent event) {


        FilteredList<FootwearAbstract> filteredList = new FilteredList<>(data, (foot) -> containsKeys(foot.getModel(),
                search_field.getText()));

        SortedList<FootwearAbstract> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(data_view.comparatorProperty());
        data_view.setItems(sortedList);
    }

    private boolean containsKeys(String model, String text) {

        int i = 0;

        while (i < model.length() && i < text.length()) {

            if (!String.valueOf(model.charAt(i)).equalsIgnoreCase(String.valueOf(text.charAt(i)))) {
                return false;
            }
            i++;
        }
        return true;
    }

    @FXML
    private void resetField() {
        type_field.setText("");
        type_field.setDisable(true);
        model_field.setText("");
        model_field.setDisable(true);
        brend_field.setText("");
        brend_field.setDisable(true);
        price_field.setText("");
        price_field.setDisable(true);
        category_choose.setValue("Категрія");
        category_choose.setPromptText("Категорія");
        category_choose.setDisable(true);
        import_img_btn.setDisable(true);
        save_btn.setDisable(true);
        more_btn.setDisable(true);
        delete_btn.setDisable(true);
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

    @FXML
    void undisabledSelect(MouseEvent event) {

        import_img_btn.setDisable(false);
        save_btn.setDisable(false);
        more_btn.setDisable(false);
        delete_btn.setDisable(false);
        brend_field.setDisable(false);
        model_field.setDisable(false);
        price_field.setDisable(false);
        type_field.setDisable(false);
        category_choose.setDisable(false);

    }

    private void setSelectedData(int currentIndex) {
        FootwearAbstract footwearAbstract = data.get(currentIndex);

        type_field.setText(footwearAbstract.getType().getType());
        model_field.setText(footwearAbstract.getModel());
        brend_field.setText(footwearAbstract.getBrand());
        price_field.setText(footwearAbstract.getPrice().toString());
        category_choose.setValue(footwearAbstract.getCategory().getCategory());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDefaultSliderBounds();

        initCountOfCategory();

        initDataTable();

        initCategory();

        data_view.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {
            int selectedIndex = data_view.getSelectionModel().getSelectedIndex();

            setSelectedData(selectedIndex);
        });

    }

    private void initDataTable() {

        data.addAll(model.getAllItems());

        brend_column.setCellValueFactory(new PropertyValueFactory<>("brand"));
        category_column.setCellValueFactory(new PropertyValueFactory<>("category"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("model"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));

        data_view.setItems(data);
    }

    private void initCategory() {
        for (Category s : Category.values()) {
            category_choose.getItems().add(s.getCategory());
        }
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

        high_bound_slider.setText(String.valueOf((int) slider.getMax()));
        low_bound_slider.setText(String.valueOf((int) slider.getMin()));
    }

}
