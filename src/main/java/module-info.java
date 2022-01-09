module com.example.demorestapi {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;
    requires java.net.http;
    requires org.json;


    opens com.example.demorestapi to javafx.fxml;
    exports com.example.demorestapi;
}