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
import com.example.validation.Validation;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

public class MoreInfoController implements Initializable {

    @FXML
    private AnchorPane mainWindow;

    @FXML
    private AnchorPane infoPane;

    @FXML
    private Button add_btn;

    @FXML
    private Button backward_btn;

    @FXML
    private TextField boots_color_field;

    @FXML
    private ComboBox<String> boots_fastener_choose;

    @FXML
    private TextField boots_material_field;

    @FXML
    private AnchorPane boots_pane;

    @FXML
    private TextField boots_size_field;

    @FXML
    private TextField boots_weight_field;

    @FXML
    private TableColumn<FootwearAbstract, String> brend_column;

    @FXML
    private TextField brend_field;

    @FXML
    private ComboBox<String> category_choose;

    @FXML
    private TableColumn<FootwearAbstract, String> category_column;

    @FXML
    private Button close_btn;

    @FXML
    private TableView<FootwearAbstract> data_view;

    @FXML
    private Button delete_btn;

    @FXML
    private ImageView img_block;

    @FXML
    private Button import_img_btn;

    @FXML
    private TableColumn<FootwearAbstract, String> model_column;

    @FXML
    private TextField model_field;

    @FXML
    private Button more_btn;

    @FXML
    private TableColumn<FootwearAbstract, String> price_column;

    @FXML
    private TextField price_field;

    @FXML
    private TextField sandals_appointment_field;

    @FXML
    private TextField sandals_color_field;

    @FXML
    private ComboBox<String> sandals_fastener_choose;

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
    private TableColumn<FootwearAbstract, String> type_column;

    private SelectedItemSingleton globalItem = SelectedItemSingleton.getInstance();

    private FootwearAbstract choseItem = globalItem.getChoseFootwear();

    private SlippersRepository slippersRepository = new SlippersRepository();
    private BootsRepository bootsRepository = new BootsRepository();
    private SandalsRepository sandalsRepository = new SandalsRepository();
    private ShoesRepository shoesRepository = new ShoesRepository();

    private FileImageService fileImageService = new FileImageService();

    private ChangePage changePage = new ChangePage();

    private ModelTablesRepository modelTablesRepository = new ModelTablesRepository();

    private ObservableList<FootwearAbstract> data;

    @FXML
    void add_item(MouseEvent event) {

        if (infoPane.getStyleClass().contains("changed-item")) {

            Alert alert = getAlert();

            if (alert.showAndWait().get() == ButtonType.OK) {
                try {
                    add_btn.getScene().getWindow().hide();
                    changePage.moveToAddPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {
            try {
                add_btn.getScene().getWindow().hide();
                changePage.moveToAddPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    @FXML
    void backward(MouseEvent event) {

        if (infoPane.getStyleClass().contains("changed-item")) {

            Alert alert = getAlert();

            if (alert.showAndWait().get() == ButtonType.OK) {

                backward_btn.getScene().getWindow().hide();

                try {
                    changePage.moveToMainPage();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        } else {

            backward_btn.getScene().getWindow().hide();
            try {
                changePage.moveToMainPage();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Alert getAlert() {

        Alert changedItems = new Alert(Alert.AlertType.CONFIRMATION);

        changedItems.setTitle("Закрти");
        changedItems.setHeaderText("Ви впевнені що хочете закрити?");
        changedItems.setContentText("Всі не збережені дані будуть втрачені!");

        return changedItems;
    }

    @FXML
    void closeWindow(MouseEvent event) {

        if (infoPane.getStyleClass().contains("changed-item")) {

            Alert alert = getAlert();

            if (alert.showAndWait().get() == ButtonType.OK) {
                System.exit(200);
            }
        } else {

            System.exit(200);
        }

    }

    @FXML
    void hideWindow(MouseEvent event) {

        Stage window = (Stage) mainWindow.getScene().getWindow();
        window.setIconified(true);
    }

    @FXML
    void importImage(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(fileImageService.getAllTypesOfExtensionsImage());

        File choseFile = fileChooser.showOpenDialog(mainWindow.getScene().getWindow());

        if (choseFile != null) {

            Image image = new Image(choseFile.getAbsolutePath());

            img_block.setImage(image);
        }

    }

    @FXML
    void moreInfo(MouseEvent event) {

        FootwearAbstract footwearAbstract = data.get(data_view.getSelectionModel().getSelectedIndex());

        more_btn.getScene().getWindow().hide();

        globalItem.setChoseFootwear(footwearAbstract);

        try {
            changePage.moveToMoreInfoPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void removeItem(MouseEvent event) {

        if (choseItem instanceof Slippers) {

            slippersRepository.remove(choseItem.getId());

        } else if (choseItem instanceof Sandals) {

            sandalsRepository.remove(choseItem.getId());

        } else if (choseItem instanceof Shoes) {

            shoesRepository.remove(choseItem.getId());

        } else if (choseItem instanceof Boots) {

            bootsRepository.remove(choseItem.getId());
        }

        fileImageService.removeFile(choseItem.getType()+"_"+choseItem.getId());

        delete_btn.getScene().getWindow().hide();

        try {
            changePage.moveToMainPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @FXML
    void saveItem(MouseEvent event) {

        if (!validateFields()) {
            throw new RuntimeException("Invalid fields");
        }
        choseItem.setCategory(ConvertorEnum.getCategory(category_choose.getValue()));
        choseItem.setPrice(BigDecimal.valueOf(Long.parseLong(price_field.getText())));
        choseItem.setBrand(brend_field.getText());
        choseItem.setModel(model_field.getText());
        choseItem.setSeason(ConvertorEnum.getSeason(season_choose.getValue()));


        if (choseItem instanceof Slippers) {

            choseItem.setType(ConvertorEnum.getType(type_choose.getValue(), SlippersType.values()));
            ((Slippers) choseItem).setColor(slippers_color_field.getText());
            ((Slippers) choseItem).setAppointment(slippers_appointment_field.getText());
            ((Slippers) choseItem).setSize(Integer.parseInt(slippers_size_field.getText()));

            slippersRepository.update(choseItem);

        } else if (choseItem instanceof Sandals) {

            choseItem.setType(ConvertorEnum.getType(type_choose.getValue(), SandalsType.values()));
            ((Sandals) choseItem).setColor(sandals_color_field.getText());
            ((Sandals) choseItem).setFastener(ConvertorEnum.getFastener(sandals_fastener_choose.getValue()));
            ((Sandals) choseItem).setSize(Integer.parseInt(sandals_size_field.getText()));
            ((Sandals) choseItem).setAppointment(sandals_appointment_field.getText());

            sandalsRepository.update(choseItem);

        } else if (choseItem instanceof Shoes) {

            choseItem.setType(ConvertorEnum.getType(type_choose.getValue(), ShoesType.values()));
            ((Shoes) choseItem).setColor(shoes_color_field.getText());
            ((Shoes) choseItem).setSize(Integer.parseInt(shoes_size_field.getText()));

            shoesRepository.update(choseItem);

        } else if (choseItem instanceof Boots) {


            choseItem.setType(ConvertorEnum.getType(type_choose.getValue(), BootsType.values()));
            ((Boots) choseItem).setColor(boots_color_field.getText());
            ((Boots) choseItem).setWeight(Double.parseDouble(boots_weight_field.getText()));
            ((Boots) choseItem).setSize(Integer.parseInt(boots_size_field.getText()));
            ((Boots) choseItem).setMaterial(boots_material_field.getText());
            ((Boots) choseItem).setFaster(ConvertorEnum.getFastener(boots_fastener_choose.getValue()));

            bootsRepository.update(choseItem);
        }

        fileImageService.save(img_block.getImage().getUrl(),
                choseItem.getType().getType() + "_" + choseItem.getId());

        save_btn.getScene().getWindow().hide();

        try {
            changePage.moveToMainPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private boolean validateFields() {

        Validation validation = new Validation();

        boolean result = validation.validateIntegerLength(price_field, 5) &&
                validation.validateStringFieldNameLength(brend_field, 20) &&
                validation.validateFieldModel(model_field, 20) &&
                validation.validateCheckBoxSelected(category_choose) &&
                validation.validateCheckBoxSelected(season_choose) &&
                validation.validateCheckBoxSelected(type_choose);

        if (choseItem instanceof Slippers) {
            result = result && validation.validateStringFieldNameLength(slippers_color_field, 15) &&
                    validation.validateIntegerLength(slippers_size_field, 2) &&
                    validation.validateStringFieldNameLength(slippers_appointment_field, 20);
        } else if (choseItem instanceof Shoes) {
            result = result && validation.validateStringFieldNameLength(shoes_color_field, 15) &&
                    validation.validateIntegerLength(shoes_size_field, 2);
        } else if (choseItem instanceof Sandals) {
            result = result && validation.validateStringFieldNameLength(sandals_color_field, 15) &&
                    validation.validateIntegerLength(sandals_size_field, 2) &&
                    validation.validateStringFieldNameLength(sandals_appointment_field, 20) &&
                    validation.validateCheckBoxSelected(sandals_fastener_choose);
        } else if (choseItem instanceof Boots) {
            result = result && validation.validateStringFieldNameLength(boots_color_field, 15) &&
                    validation.validateIntegerLength(boots_size_field, 2) &&
                    validation.validateDoubleValue(boots_weight_field) &&
                    validation.validateStringFieldNameLength(boots_material_field, 20) &&
                    validation.validateCheckBoxSelected(boots_fastener_choose);
        }

        return result;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initDataView();

        initChoseItem();

        price_field.setOnKeyPressed(event -> {
            isChanged();
            enabledSaveBtn();
        });

        brend_field.setOnKeyPressed(event -> {
            isChanged();
            enabledSaveBtn();
        });

        model_field.setOnKeyPressed(event -> {
            isChanged();
            enabledSaveBtn();

        });

        category_choose.valueProperty().addListener((observableValue, s, t1) -> {
            if (s != null && t1 != null && !s.equals(t1)) {
                isChanged();
                enabledSaveBtn();
            }
        });

        type_choose.valueProperty().addListener((observableValue, s, t1) -> {
            if (s != null && t1 != null && !s.equals(t1)) {
                isChanged();
                enabledSaveBtn();
            }
        });

        season_choose.valueProperty().addListener((observableValue, s, t1) -> {
            if (s != null && t1 != null && !s.equals(t1)) {
                isChanged();
                enabledSaveBtn();
            }
        });

        data_view.getSelectionModel().selectedIndexProperty().addListener((observableValue, previous, t1) -> {

            enabledMoreInfo();

        });
    }

    private void enabledMoreInfo() {
        more_btn.setDisable(false);
    }

    private void initDataView() {

        data = FXCollections.observableArrayList(modelTablesRepository.getListFamiliarItems(choseItem));

        category_column.setCellValueFactory(new PropertyValueFactory<>("category"));
        type_column.setCellValueFactory(new PropertyValueFactory<>("type"));
        model_column.setCellValueFactory(new PropertyValueFactory<>("model"));
        brend_column.setCellValueFactory(new PropertyValueFactory<>("brand"));
        price_column.setCellValueFactory(new PropertyValueFactory<>("price"));

        data_view.setItems(data);
    }

    private void initChoseItem() {

        initCategory();

        initSeasons();

        initDataByType();
        initFasteners();

        initType();

        initImage();


    }


    private void enabledSaveBtn() {
        save_btn.setDisable(false);
    }


    private void isChanged() {
        infoPane.getStyleClass().add("changed-item");
    }


    private void initCategory() {

        for (Category category : Category.values()) {
            category_choose.getItems().add(category.getCategory());
        }

        category_choose.getSelectionModel().select(choseItem.getCategory().getCategory());

    }

    private void initSeasons() {

        for (Seasons season : Seasons.values()) {
            season_choose.getItems().add(season.getSeasonName());
        }

        season_choose.getSelectionModel().select(choseItem.getSeason().getSeasonName());
    }

    private void initDataByType() {

        model_field.setText(choseItem.getModel());
        brend_field.setText(choseItem.getBrand());
        price_field.setText(String.valueOf(choseItem.getPrice().intValue()));

        if (choseItem instanceof Slippers) {

            slippers_pane.setVisible(true);
            slippers_appointment_field.setText(((Slippers) choseItem).getAppointment());
            slippers_color_field.setText(((Slippers) choseItem).getColor());
            slippers_size_field.setText(String.valueOf(((Slippers) choseItem).getSize()));

        } else if (choseItem instanceof Sandals) {

            sandals_pane.setVisible(true);
            sandals_appointment_field.setText(((Sandals) choseItem).getAppointment());
            sandals_color_field.setText(((Sandals) choseItem).getColor());
            sandals_size_field.setText(String.valueOf(((Sandals) choseItem).getSize()));
            sandals_fastener_choose.getSelectionModel().select(((Sandals) choseItem).getFastener().getTypeFastener());

        } else if (choseItem instanceof Shoes) {

            shoes_pane.setVisible(true);
            shoes_color_field.setText(((Shoes) choseItem).getColor());
            shoes_size_field.setText(String.valueOf(((Shoes) choseItem).getSize()));

        } else if (choseItem instanceof Boots) {

            boots_pane.setVisible(true);
            boots_color_field.setText(((Boots) choseItem).getColor());
            boots_size_field.setText(String.valueOf(((Boots) choseItem).getSize()));
            boots_material_field.setText(((Boots) choseItem).getMaterial());
            boots_weight_field.setText(String.valueOf(((Boots) choseItem).getWeight()));
            boots_fastener_choose.getSelectionModel().select(((Boots) choseItem).getFaster().getTypeFastener());

        }

    }

    private void initFasteners() {
        for (Fastener fastener : Fastener.values()) {
            boots_fastener_choose.getItems().add(fastener.getTypeFastener());
            sandals_fastener_choose.getItems().add(fastener.getTypeFastener());
        }



    }

    private void initType() {

        if (choseItem.getType() instanceof SlippersType) {

            type_choose.getItems().addAll(Arrays.stream(SlippersType.values()).map(TypeFootwear::getType).toList());

        } else if (choseItem.getType() instanceof SandalsType) {

            type_choose.getItems().addAll(Arrays.stream(SandalsType.values()).map(TypeFootwear::getType).toList());

        } else if (choseItem.getType() instanceof ShoesType) {

            type_choose.getItems().addAll(Arrays.stream(ShoesType.values()).map(TypeFootwear::getType).toList());

        } else if (choseItem.getType() instanceof BootsType) {

            type_choose.getItems().addAll(Arrays.stream(BootsType.values()).map(TypeFootwear::getType).toList());

        }

        type_choose.getSelectionModel().select(choseItem.getType().getType());

    }

    void initImage() {

        Image image = new Image(fileImageService.getImageByName(choseItem.getType()
                + "_" + choseItem.getId()).getAbsolutePath());

        img_block.setImage(image);
    }


}
