package com.jsonfixer;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class PrimaryController {
    @FXML
    private TextField fileInput;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void openFileChooser(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile != null) {
            // Handle the selected file
            fileInput.setText(selectedFile.getAbsolutePath());
        }
    }

    @FXML
    private void convertFile(ActionEvent event) {
        String filePath = fileInput.getText();
        if (filePath == "") {
            System.out.println("Insert some text.");
        } else {
            System.out.println("Converting file " + filePath + "...");
        }
    }

}
