module com.example.shoeaccountingcoursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    requires org.apache.logging.log4j.slf4j;
    requires org.apache.logging.log4j.core;

    
    opens com.example.shoeaccountingcoursework to javafx.fxml;
    exports com.example.shoeaccountingcoursework;
}