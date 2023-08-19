package com.example.validation;

import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {

    public boolean validateIntegerField(TextField field) {

        Pattern pattern = Pattern.compile("^[1-9]+[0-9]+");

        Matcher matcher = pattern.matcher(field.getText());
        if (field.getText().isEmpty() || !matcher.find()) {
            field.setStyle("-fx-border-color:red;");
            return false;
        }
        field.setStyle("-fx-border-color:green;");
        return true;
    }

    public boolean validateIntegerLength(TextField field, int length) {
        Pattern pattern = Pattern.compile("^[1-9]+[0-9]+");

        Matcher matcher = pattern.matcher(field.getText());
        if (field.getText().isEmpty() || !matcher.find() || field.getText().length() > length) {
            field.setStyle("-fx-border-color:red;");
            return false;
        }
        field.setStyle("-fx-border-color:green;");
        return true;
    }

    public boolean validateDoubleValue(TextField field) {

        Pattern pattern = Pattern.compile("^[1-9]+[0-9]+.[0-9]+$");

        Matcher matcher = pattern.matcher(field.getText());
        if (field.getText().isEmpty() || !matcher.find()) {
            field.setStyle("-fx-border-color:red;");
            return false;
        }
        field.setStyle("-fx-border-color:green;");
        return true;
    }

    public boolean validateMaxDigit(TextField field, int start, int end) {

        Pattern pattern = Pattern.compile(String.format("^[%s-%s]$", start, end));

        Matcher matcher = pattern.matcher(field.getText());
        if (field.getText().isEmpty() || !matcher.find()) {
            field.setStyle("-fx-border-color:red;");
            return false;
        }
        field.setStyle("-fx-border-color:green;");
        return true;
    }
    public boolean validateStringFieldNameLength(TextField field, int length) {

        Pattern pattern = Pattern.compile("[A-Z][a-z]+|[А-Я][а-я]+");

        Matcher matcher = pattern.matcher(field.getText());
        if (field.getText().isEmpty() || field.getText().equals("") || !matcher.find() || field.getText().length() > length) {

            field.setStyle("-fx-border-color:red;");
            return false;
        }
        field.setStyle("-fx-border-color:green;");
        return true;
    }

    public boolean validateStringField(TextField field, int length) {
        Pattern pattern = Pattern.compile("^[A-Za-z]+$|^[А-Яа-я]+$");

        Matcher matcher = pattern.matcher(field.getText());
        if (field.getText().isEmpty() || !matcher.find() || field.getText().length() > length) {
            field.setStyle("-fx-border-color:red;");
            return false;
        }
        field.setStyle("-fx-border-color:green;");
        return true;
    }

    public boolean validateCheckBoxSelected(ComboBox<String> checkBox) {

        if (checkBox.getSelectionModel().isEmpty()) {
            checkBox.setStyle("-fx-border-color:red;");
            return false;
        }
        checkBox.setStyle("-fx-border-color:green;");

        return true;
    }

    public boolean validateFieldModel(TextField modelField, int length) {
        Pattern pattern = Pattern.compile("([A-Za-z]+[0-9]+|[A-Za-z]+)|([А-Яа-я]+[0-9]+|[А-Яа-я]+)");

        Matcher matcher = pattern.matcher(modelField.getText());
        if (modelField.getText().isEmpty() || !matcher.find() || modelField.getText().length() > length) {
            modelField.setStyle("-fx-border-color:red;");
            return false;
        }
        modelField.setStyle("-fx-border-color:green;");
        return true;
    }
}
