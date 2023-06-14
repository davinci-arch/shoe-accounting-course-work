module com.example.shoeaccountingcoursework {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;


    opens com.example.shoeaccountingcoursework to javafx.fxml;
    exports com.example.shoeaccountingcoursework;
}