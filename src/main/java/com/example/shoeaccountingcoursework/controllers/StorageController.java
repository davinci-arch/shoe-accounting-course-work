package com.example.shoeaccountingcoursework.controllers;

import com.example.dao.repository.ConvertorEnum;
import com.example.dao.repository.generalrepo.ModelTablesRepository;
import com.example.dao.repository.model.BootsRepository;
import com.example.dao.repository.model.SandalsRepository;
import com.example.dao.repository.model.ShoesRepository;
import com.example.dao.repository.model.SlippersRepository;
import com.example.global.SelectedItemSingleton;
import com.example.model.*;
import com.example.model.types.*;
import com.example.service.FileImageService;
import com.example.shoeaccountingcoursework.ChangePage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.controlsfx.control.RangeSlider;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Predicate;

public class StorageController implements Initializable {

    // TODO: кнопка Save не відкривається якщо змінюються лише вони
    @FXML
    private Button add_btn;

    @FXML
    private AnchorPane pane_selected_item;

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
    private AnchorPane main_form;

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
    private ComboBox<String> type_field;

    private ModelTablesRepository model = new ModelTablesRepository();

    private ObservableList<FootwearAbstract> data = FXCollections.observableArrayList();

    private FilterBuilder filterBuilder = new FilterBuilder();

    private BootsRepository bootsRepository = new BootsRepository();
    private ShoesRepository shoesRepository = new ShoesRepository();
    private SlippersRepository slippersRepository = new SlippersRepository();
    private SandalsRepository sandalsRepository = new SandalsRepository();

    private ChangePage nextPage = new ChangePage();

    private FileImageService fileImageService = new FileImageService();

    @FXML
    void add_item(MouseEvent event) {
        try {
            if (pane_selected_item.getStyleClass().contains("change-item")) {


                Alert alert = getAlert();

                if (alert.showAndWait().get() == ButtonType.OK) {

                    add_btn.getScene().getWindow().hide();

                    nextPage.moveToAddPage();
                }

            } else {

                add_btn.getScene().getWindow().hide();

                nextPage.moveToAddPage();
            }


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void changeRangeSlider(MouseEvent event) {
        low_bound_slider.setText(String.valueOf((int) slider.getLowValue()));
        high_bound_slider.setText(String.valueOf((int) slider.getHighValue()));
    }

    @FXML
    void confirmRangePrice(MouseEvent event) {

        filterBuilder.addBoundsPrice((i) -> i >= (int) slider.getLowValue() && i <= (int) slider.getHighValue());

        resetField();

        filterDataTableView();
    }

    @FXML
    void searchByModel(KeyEvent event) {

        filterBuilder.addSearchText(search_field.getText());

        filterDataTableView();
    }

    private void filterDataTableView() {

        FilteredList<FootwearAbstract> filteredList = new FilteredList<>(data, (foot) -> filterBuilder.getPredicate(
                foot.getPrice().intValue(),
                foot.getModel(),
                foot.getCategory().getCategory()
        ));

        SortedList<FootwearAbstract> sortedList = new SortedList<>(filteredList);

        sortedList.comparatorProperty().bind(data_view.comparatorProperty());
        data_view.setItems(sortedList);
    }


    @FXML
    private void resetField() {
        FileImageService fileImageService = new FileImageService();

        data_view.getSelectionModel().clearSelection();

        type_field.setValue("Тип");
        type_field.setPromptText("Тип");
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

        Image image = new Image(fileImageService.getDefaultImage().getAbsolutePath());
        img_block.setImage(image);
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
        more_btn.setDisable(false);
        delete_btn.setDisable(false);
        brend_field.setDisable(false);
        model_field.setDisable(false);
        price_field.setDisable(false);
        type_field.setDisable(false);
        category_choose.setDisable(false);

    }


    @FXML
    void removeSelectedItem(MouseEvent event) {

        int selectedItem = data_view.getSelectionModel().getFocusedIndex();

        FootwearAbstract footwearAbstract = data.get(selectedItem);

        if (footwearAbstract instanceof Slippers) {

            slippersRepository.remove(footwearAbstract.getId());

        } else if (footwearAbstract instanceof Sandals) {

            sandalsRepository.remove(footwearAbstract.getId());

        } else if (footwearAbstract instanceof Shoes) {

            shoesRepository.remove(footwearAbstract.getId());

        } else if (footwearAbstract instanceof Boots) {

            bootsRepository.remove(footwearAbstract.getId());
        }

        fileImageService.removeFile(footwearAbstract.getType().getType() + "_" + footwearAbstract.getId());

        initDataTable();

        resetField();

        initCountOfCategory();

    }


    @FXML
    void saveSelectedItem(MouseEvent event) {

        int selectedItem = data_view.getSelectionModel().getFocusedIndex();


        FootwearAbstract footwearAbstract = data.get(selectedItem);

        footwearAbstract.setBrand(brend_field.getText());
        footwearAbstract.setCategory(ConvertorEnum.getCategory(category_choose.getValue()));
        footwearAbstract.setModel(model_field.getText());
        footwearAbstract.setPrice(BigDecimal.valueOf(Long.parseLong(price_field.getText())));

        if (footwearAbstract instanceof Slippers) {

            footwearAbstract.setType(ConvertorEnum.getType(type_field.getValue(), SlippersType.values()));
            slippersRepository.update(footwearAbstract);

        } else if (footwearAbstract instanceof Sandals) {

            footwearAbstract.setType(ConvertorEnum.getType(type_field.getValue(), SandalsType.values()));
            sandalsRepository.update(footwearAbstract);

        } else if (footwearAbstract instanceof Shoes) {

            footwearAbstract.setType(ConvertorEnum.getType(type_field.getValue(), ShoesType.values()));
            shoesRepository.update(footwearAbstract);

        } else if (footwearAbstract instanceof Boots) {

            footwearAbstract.setType(ConvertorEnum.getType(type_field.getValue(), BootsType.values()));
            bootsRepository.update(footwearAbstract);
        }

        fileImageService.save(img_block.getImage().getUrl(),
                footwearAbstract.getType().toString() + "_" + footwearAbstract.getId());

        resetStylePane();

        initDataTable();

        resetField();

        initCountOfCategory();

        resetImgSelected();

    }

    @FXML
    void isChangeField(KeyEvent event) {
        isChanged();
    }

    @FXML
    void more_info(MouseEvent event) {
        try {

            int selectedIndex = data_view.getSelectionModel().getSelectedIndex();
            FootwearAbstract footwearAbstract = data.get(selectedIndex);

            SelectedItemSingleton instance = SelectedItemSingleton.getInstance();
            instance.setChoseFootwear(footwearAbstract);

            if (pane_selected_item.getStyleClass().contains("change-item")) {


                Alert alert = getAlert();

                if (alert.showAndWait().get() == ButtonType.OK) {

                    more_btn.getScene().getWindow().hide();

                    nextPage.moveToMoreInfoPage();
                }

            } else {

                more_btn.getScene().getWindow().hide();

                nextPage.moveToMoreInfoPage();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void importImage(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().addAll(fileImageService.getAllTypesOfExtensionsImage());

        File choosedFile = fileChooser.showOpenDialog(main_form.getScene().getWindow());

        if (choosedFile != null) {

            Image image = new Image(choosedFile.getAbsolutePath());

            img_block.setImage(image);

            isChanged();

        }
    }

    @FXML
    void hide_window(MouseEvent event) {

        Stage window = (Stage) main_form.getScene().getWindow();
        window.setIconified(true);

    }

    @FXML
    void close_window(MouseEvent event) {

        if (pane_selected_item.getStyleClass().contains("change-item")) {

            Alert alert = getAlert();

            if (alert.showAndWait().get() == ButtonType.OK) {
                System.exit(200);
            }

        } else {

            System.exit(200);
        }

    }

    private static Alert getAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        alert.setTitle("Закрти");
        alert.setHeaderText("Ви впевнені що хочете закрити?");
        alert.setContentText("Всі не збережені дані будуть втрачені!");
        return alert;
    }


    @FXML
    void comboBoxIsChanged(MouseEvent event) {
        isChanged();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        setDefaultSliderBounds();

        initCountOfCategory();

        initDataTable();

        initCategory();

        data_view.getSelectionModel().selectedIndexProperty().addListener((observableValue, number, t1) -> {

            int selectedIndex = data_view.getSelectionModel().getSelectedIndex();

            if (selectedIndex >= 0) {

                setSelectedData(selectedIndex);
            }
        });



    }

    private void isChanged() {
        pane_selected_item.getStyleClass().add("change-item");
        save_btn.setDisable(false);
    }

    private void resetStylePane() {

        pane_selected_item.getStyleClass().removeAll("change-item");

        pane_selected_item.getStyleClass().add("save-change");

        save_btn.setDisable(true);
    }

    private void resetImgSelected() {

        FileImageService imageService = new FileImageService();

        File defaultImage = imageService.getDefaultImage();

        if (defaultImage != null) {

            Image image = new Image(defaultImage.getAbsolutePath());


            img_block.setImage(image);

        }

    }

    @FXML
    void checkChange1(MouseEvent event) {
        resetField();
        if (check_category_1.isSelected()) {

            filterBuilder.addCategoryPredicate(Category.MALE);
            filterDataTableView();

        } else {

            filterBuilder.removeCategoryPredicate(Category.MALE);
            filterDataTableView();

        }
    }

    @FXML
    void checkChange2(MouseEvent event) {
        resetField();
        if (check_category_2.isSelected()) {

            filterBuilder.addCategoryPredicate(Category.FEMALE);
            filterDataTableView();

        } else {

            filterBuilder.removeCategoryPredicate(Category.FEMALE);
            filterDataTableView();

        }
    }

    @FXML
    void checkChange3(MouseEvent event) {
        resetField();
        if (check_category_3.isSelected()) {

            filterBuilder.addCategoryPredicate(Category.UNISEX);
            filterDataTableView();

        } else {

            filterBuilder.removeCategoryPredicate(Category.UNISEX);
            filterDataTableView();

        }
    }

    @FXML
    void checkChange4(MouseEvent event) {
        resetField();
        if (check_category_4.isSelected()) {

            filterBuilder.addCategoryPredicate(Category.ORTHOPEDIC);
            filterDataTableView();

        } else {

            filterBuilder.removeCategoryPredicate(Category.ORTHOPEDIC);
            filterDataTableView();

        }
    }

    @FXML
    void checkChange5(MouseEvent event) {
        resetField();
        if (check_category_5.isSelected()) {

            filterBuilder.addCategoryPredicate(Category.CHILD);
            filterDataTableView();

        } else {

            filterBuilder.removeCategoryPredicate(Category.CHILD);
            filterDataTableView();

        }
    }

    private void setSelectedData(int currentIndex) {
        FootwearAbstract footwearAbstract = data.get(currentIndex);

        initTypeBySelectionItem(footwearAbstract.getType());
        model_field.setText(footwearAbstract.getModel());
        brend_field.setText(footwearAbstract.getBrand());
        price_field.setText(footwearAbstract.getPrice().toString());
        category_choose.setValue(footwearAbstract.getCategory().getCategory());

        img_block.setImage(getImage(footwearAbstract.getType().toString() + "_" + footwearAbstract.getId()));

    }

    private Image getImage(String fileName) {

        FileImageService fileImageService = new FileImageService();

        File imageByName = fileImageService.getImageByName(fileName);

        return new Image(imageByName.getAbsolutePath());
    }

    private void initTypeBySelectionItem(TypeFootwear type) {

        if (type instanceof ShoesType) {

            type_field.getItems().setAll(Arrays.stream(ShoesType.values()).map(ShoesType::getType).toList());

        } else if (type instanceof BootsType) {

            type_field.getItems().setAll(Arrays.stream(BootsType.values()).map(BootsType::getType).toList());

        } else if (type instanceof SandalsType) {

            type_field.getItems().setAll(Arrays.stream(SandalsType.values()).map(SandalsType::getType).toList());

        } else if (type instanceof SlippersType) {

            type_field.getItems().setAll(Arrays.stream(SlippersType.values()).map(SlippersType::getType).toList());

        }

        type_field.setValue(type.getType());

    }

    private void initDataTable() {

        data.setAll(model.getAllItems());

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


        check_category_count_1.setText(String.format(patternSet, male));
        check_category_count_2.setText(String.format(patternSet, female));
        check_category_count_3.setText(String.format(patternSet, unisex));
        check_category_count_4.setText(String.format(patternSet, orthopedic));
        check_category_count_5.setText(String.format(patternSet, child));
    }

    private void setDefaultSliderBounds() {
        slider.setLowValue(slider.getMin());
        slider.setHighValue(slider.getMax());

        high_bound_slider.setText(String.valueOf((int) slider.getMax()));
        low_bound_slider.setText(String.valueOf((int) slider.getMin()));
    }


    private final static class FilterBuilder {


        private Predicate<Integer> price = (i) -> true;
        private Predicate<String> stringPredicate = (i) -> true;

        private Map<String, Predicate<String>> checkbox = new HashMap<>();

        public void addBoundsPrice(Predicate<Integer> bounds) {

            price = bounds;

        }

        public void addSearchText(String searchField) {

            stringPredicate = (item) -> {
                int i = 0;

                while (i < item.length() && i < searchField.length()) {

                    if (!String.valueOf(item.charAt(i)).equalsIgnoreCase(String.valueOf(searchField.charAt(i)))) {
                        return false;
                    }
                    i++;
                }

                return true;
            };

        }

        public void addCategoryPredicate(Category category) {

            Predicate<String> checkBox = (i) -> i.equals(category.getCategory());

            checkbox.put(category.getCategory(), checkBox);

        }

        public void removeCategoryPredicate(Category category) {

            checkbox.remove(category.getCategory());

        }

        public boolean getPredicate(Integer priceBounds, String text, String category) {

            Predicate<String> checkBoxPredicates = checkbox.values().stream().reduce(Predicate::or).orElse((i) -> true);

            return price.test(priceBounds) && stringPredicate.test(text) && checkBoxPredicates.test(category);
        }


    }
}
