module com.example.shoeaccountingcoursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires org.apache.logging.log4j.slf4j;
    requires org.apache.logging.log4j.core;
    requires org.apache.logging.log4j;
    requires tornadofx;
    requires java.sql;
    requires org.slf4j;
    requires mysql.connector.j;
    requires java.naming;

    opens com.example.shoeaccountingcoursework to javafx.fxml;
    exports com.example.shoeaccountingcoursework;
    exports com.example.shoeaccountingcoursework.controllers;
    opens com.example.shoeaccountingcoursework.controllers to javafx.fxml;

    exports com.example.logs.layouts;
}