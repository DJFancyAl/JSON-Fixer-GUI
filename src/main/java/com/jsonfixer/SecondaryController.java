package com.jsonfixer;

import java.io.File;
import javafx.scene.Node;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

public class SecondaryController implements Initializable {
    @FXML
    private TextField locationInput;

    private Preferences preferences;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        preferences = App.getPreferences();
        String saveLocation = preferences.get("fileLocation", "No file found.");
        locationInput.setText(saveLocation);
    }

    @FXML
    private void openDirectoryChooser(ActionEvent event) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedDirectory = directoryChooser.showDialog(stage);
        if (selectedDirectory != null) {
            // Handle the selected directory
            locationInput.setText(selectedDirectory.getAbsolutePath());
        }
    }

    @FXML
    private void savePreferences() throws IOException {
        preferences.put("fileLocation", locationInput.getText());
        switchToPrimary();
    }

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    public void closeApplication() {
        Stage stage = (Stage) locationInput.getScene().getWindow();
        stage.close();
    }
}
