module com.example.demorestapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens com.example.demorestapi to javafx.fxml;
    exports com.example.demorestapi;
}