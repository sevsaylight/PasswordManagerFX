module com.example.sqlfx {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.sqlfx to javafx.fxml;
    exports com.example.sqlfx;
}