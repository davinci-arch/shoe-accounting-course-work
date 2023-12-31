package com.example.shoeaccountingcoursework.controllers;

import com.example.dao.repository.ConvertorEnum;
import com.example.dao.repository.model.BootsRepository;
import com.example.dao.repository.model.SandalsRepository;
import com.example.dao.repository.model.ShoesRepository;
import com.example.dao.repository.model.SlippersRepository;
import com.example.model.*;
import com.example.model.types.BootsType;
import com.example.model.types.SandalsType;
import com.example.model.types.ShoesType;
import com.example.model.types.SlippersType;
import com.example.service.FileImageService;
import com.example.shoeaccountingcoursework.ChangePage;
import com.example.validation.Validation;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
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
import java.util.ResourceBundle;

public class NewItemController implements Initializable {

    @FXML
    private ComboBox<String> boots_fastener_choose;

    @FXML
    private ComboBox<String> kind_choose;

    @FXML
    private Button backward_btn;

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
    private AnchorPane shared_data_pane;

    @FXML
    private AnchorPane other_data_pane;

    @FXML
    private TextField model_field;

    @FXML
    private TextField price_field;

    @FXML
    private TextField sandals_appointment_field;

    @FXML
    private ComboBox<String> sandals_fastener_choose;

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

    private ChangePage changePage = new ChangePage();

    private FileImageService fileImageService = new FileImageService();


    @FXML
    void backward(MouseEvent event) {

        if (shared_data_pane.getStyleClass().contains("changed-item")) {

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

    @FXML
    void clearFields(MouseEvent event) {

        kind_choose.getSelectionModel().clearSelection();
        kind_choose.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(kind_choose.getPromptText());
                } else {
                    setText(item);
                }
            }
        });
        boots_fastener_choose.getSelectionModel().clearSelection();
        boots_fastener_choose.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(boots_fastener_choose.getPromptText());
                } else {
                    setText(item);
                }
            }
        });
        ;
        sandals_fastener_choose.getSelectionModel().clearSelection();
        sandals_fastener_choose.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(sandals_appointment_field.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

        category_choose.getSelectionModel().clearSelection();
        category_choose.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(category_choose.getPromptText());
                } else {
                    setText(item);
                }
            }
        });
        season_choose.getSelectionModel().clearSelection();
        season_choose.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(season_choose.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

        type_choose.getSelectionModel().clearSelection();
        type_choose.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (item == null || empty) {
                    setText(type_choose.getPromptText());
                } else {
                    setText(item);
                }
            }
        });

        model_field.clear();
        price_field.clear();
        brend_field.clear();
        boots_color_field.clear();
        boots_material_field.clear();
        boots_weight_field.clear();
        boots_size_field.clear();
        sandals_color_field.clear();
        sandals_appointment_field.clear();
        sandals_size_field.clear();
        shoes_color_field.clear();
        shoes_size_field.clear();
        slippers_color_field.clear();
        slippers_appointment_field.clear();
        slippers_size_field.clear();


        Image image = new Image(fileImageService.getDefaultImage().getAbsolutePath());
        img_block.setImage(image);

        clear_btn.setDisable(true);
        type_choose.setDisable(true);

        save_btn.setDisable(true);



    }

    private void resetBorderStyle() {
        shared_data_pane.getStyleClass().removeAll("changed-item");
        other_data_pane.getStyleClass().removeAll("changed-item");
    }

    @FXML
    void closeWindow(MouseEvent event) {

        if (shared_data_pane.getStyleClass().contains("changed-item")) {

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

        alert.setTitle("Закрити");
        alert.setHeaderText("Ви впевнені що хочете закрити?");
        alert.setContentText("Всі не збережені дані будуть втрачені!");
        return alert;
    }

    @FXML
    void hideWindow(MouseEvent event) {

        Stage window = (Stage) mainPane.getScene().getWindow();

        window.setIconified(true);
    }

    @FXML
    void importImage(MouseEvent event) {

        FileChooser fileChooser = new FileChooser();

        fileChooser.getExtensionFilters().add(fileImageService.getAllTypesOfExtensionsImage());

        File initialDirectory = fileChooser.showOpenDialog(mainPane.getScene().getWindow());

        if (initialDirectory != null) {

            Image image = new Image(initialDirectory.getAbsolutePath());

            img_block.setImage(image);

        }
    }

    @FXML
    void saveItem(MouseEvent event) {

        if (!validationFields()) {
            throw new RuntimeException("Invalid validation");
        }

        FootwearAbstract footwear = null;

        if (kind_choose.getValue().equals("Тапочки")) {

            footwear = new Slippers(ConvertorEnum.getCategory(category_choose.getValue()),
                    ConvertorEnum.getType(type_choose.getValue(), SlippersType.values()), model_field.getText(),
                    brend_field.getText(), BigDecimal.valueOf(Long.parseLong(price_field.getText())),
                    ConvertorEnum.getSeason(season_choose.getValue()), slippers_color_field.getText(),
                    slippers_appointment_field.getText(), Integer.parseInt(slippers_size_field.getText()));

            footwear.recalculatePrice();
            SlippersRepository slippersRepository = new SlippersRepository();
            slippersRepository.save(footwear);

            footwear = slippersRepository.getLast();


        } else if (kind_choose.getValue().equals("Туфлі")) {

            footwear = new Shoes(ConvertorEnum.getCategory(category_choose.getValue()),
                    ConvertorEnum.getType(type_choose.getValue(), ShoesType.values()), model_field.getText(),
                    brend_field.getText(), BigDecimal.valueOf(Long.parseLong(price_field.getText())),
                    ConvertorEnum.getSeason(season_choose.getValue()), shoes_color_field.getText(),
                    Integer.parseInt(shoes_size_field.getText()));
            footwear.recalculatePrice();

            ShoesRepository shoesRepository = new ShoesRepository();
            shoesRepository.save(footwear);

            footwear = shoesRepository.getLast();

        } else if (kind_choose.getValue().equals("Босоніжки")) {

            footwear = new Sandals(ConvertorEnum.getCategory(category_choose.getValue()),
                    ConvertorEnum.getType(type_choose.getValue(), SandalsType.values()), model_field.getText(),
                    brend_field.getText(), BigDecimal.valueOf(Long.parseLong(price_field.getText())),
                    ConvertorEnum.getSeason(season_choose.getValue()),
                    ConvertorEnum.getFastener(sandals_fastener_choose.getValue()), sandals_color_field.getText(),
                    sandals_appointment_field.getText(), Integer.parseInt(sandals_size_field.getText()));
            footwear.recalculatePrice();

            SandalsRepository sandalsRepository = new SandalsRepository();
            sandalsRepository.save(footwear);

            footwear = sandalsRepository.getLast();


        } else if (kind_choose.getValue().equals("Чоботи")) {

            footwear = new Boots(ConvertorEnum.getCategory(category_choose.getValue()),
                    ConvertorEnum.getType(type_choose.getValue(), BootsType.values()), model_field.getText(),
                    brend_field.getText(), BigDecimal.valueOf(Long.parseLong(price_field.getText())),
                    ConvertorEnum.getSeason(season_choose.getValue()),
                    ConvertorEnum.getFastener(boots_fastener_choose.getValue()), boots_color_field.getText(),
                    boots_material_field.getText(), Double.parseDouble(boots_weight_field.getText()),
                    Integer.parseInt(boots_size_field.getText()));
            footwear.recalculatePrice();

            BootsRepository bootsRepository = new BootsRepository();
            bootsRepository.save(footwear);

            footwear = bootsRepository.getLast();
        }

        saveImg(footwear);

        save_btn.getScene().getWindow().hide();
        try {
            changePage.moveToMainPage();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }

    private void saveImg(FootwearAbstract footwearAbstract) {

        String url = img_block.getImage().getUrl();

        fileImageService.save(url,
                footwearAbstract.getType().toString() + "_" + footwearAbstract.getId());

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        initKinds();

        initCategory();

        initSeason();

        initFastener();

        kind_choose.valueProperty().addListener((observableValue, s, t1) -> {

            type_choose.setDisable(false);

            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();

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

        kind_choose.valueProperty().addListener((observableValue, s, t1) -> {
            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();
            enabledSaveBtn();
        });

        category_choose.valueProperty().addListener((observableValue, s, t1) -> {
            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();
            enabledSaveBtn();
        });

        season_choose.valueProperty().addListener((observableValue, s, t1) -> {
            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();
            enabledSaveBtn();

        });

        model_field.setOnKeyPressed(keyEvent -> {
            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();
            enabledSaveBtn();

        });
        price_field.setOnKeyPressed(keyEvent -> {
            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();
            enabledSaveBtn();

        });
        brend_field.setOnKeyPressed(keyEvent -> {
            shared_data_pane.getStyleClass().add("changed-item");
            other_data_pane.getStyleClass().add("changed-item");
            enabledClearBtn();

        });


    }

    private boolean validationFields() {
        Validation validation = new Validation();

        boolean result = validation.validateIntegerLength(price_field, 6) &&
                validation.validateStringFieldNameLength(brend_field, 20) &&
                validation.validateFieldModel(model_field, 20) &&
                validation.validateCheckBoxSelected(category_choose) &&
                validation.validateCheckBoxSelected(type_choose) &&
                validation.validateCheckBoxSelected(kind_choose) &&
                validation.validateCheckBoxSelected(season_choose);

        if (kind_choose.getValue().equals("Тапочки")) {

            result = result && validation.validateStringFieldNameLength(slippers_color_field, 15) &&
                    validation.validateIntegerLength(slippers_size_field, 2) &&
                    validation.validateStringFieldNameLength(slippers_appointment_field, 20);

        } else if (kind_choose.getValue().equals("Туфлі")) {

            result = result && validation.validateStringFieldNameLength(shoes_color_field, 15) &&
                    validation.validateIntegerLength(shoes_size_field, 2);

        } else if (kind_choose.getValue().equals("Босоніжки")) {

            result = result && validation.validateStringFieldNameLength(sandals_color_field, 15) &&
                    validation.validateIntegerLength(sandals_size_field, 2) &&
                    validation.validateStringFieldNameLength(sandals_appointment_field, 20) &&
                    validation.validateCheckBoxSelected(sandals_fastener_choose);
        } else if (kind_choose.getValue().equals("Чоботи")) {

            result = result && validation.validateStringFieldNameLength(boots_color_field, 15) &&
                    validation.validateIntegerLength(boots_size_field, 2) &&
                    validation.validateIntegerLength(boots_weight_field, 3) &&
                    validation.validateStringFieldNameLength(boots_material_field, 20) &&
                    validation.validateCheckBoxSelected(boots_fastener_choose);
        }

        return result;
    }

    private void enabledSaveBtn() {
        save_btn.setDisable(false);
    }
    private void enabledClearBtn() {
        clear_btn.setDisable(false);
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

    void initFastener() {

        for (Fastener fastener : Fastener.values()) {
            boots_fastener_choose.getItems().add(fastener.getTypeFastener());
            sandals_fastener_choose.getItems().add(fastener.getTypeFastener());
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
