module com.example.simplecodeeditor {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;

    opens com.main.simplecodeeditor to javafx.fxml;
    exports com.main.simplecodeeditor;
}