module com.jsonfixer {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.gson;
    requires java.prefs;

    opens com.jsonfixer to javafx.fxml;

    exports com.jsonfixer;
}
