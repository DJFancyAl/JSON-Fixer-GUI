package com.jsonfixer;

import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.ScrollPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import com.jsonfixer.Files;

public class PrimaryController {
    Files fileManager = new Files();
    TextArea resultText = new TextArea();

    @FXML
    private TextField fileInput;

    @FXML
    private VBox mainLayout;

    private ScrollPane createResultPane() {
        // Create a scroll pane.
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setId("resultsPane");
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        scrollPane.setPrefHeight(200.0);
        scrollPane.setPrefWidth(800.0);

        // Create a text area.
        resultText.setId("resultsText");
        resultText.setEditable(false);

        // Add the text area to the scroll pane.
        AnchorPane anchorPane = new AnchorPane();
        anchorPane.getChildren().add(resultText);
        AnchorPane.setTopAnchor(resultText, 0.0);
        AnchorPane.setBottomAnchor(resultText, 0.0);
        AnchorPane.setLeftAnchor(resultText, 0.0);
        AnchorPane.setRightAnchor(resultText, 0.0);
        scrollPane.setContent(anchorPane);

        // Anchor the scroll pane to the edges of the main layout.
        AnchorPane.setTopAnchor(scrollPane, 0.0);
        AnchorPane.setBottomAnchor(scrollPane, 0.0);
        AnchorPane.setLeftAnchor(scrollPane, 0.0);
        AnchorPane.setRightAnchor(scrollPane, 0.0);

        // Create a new AnchorPane to hold the scroll pane.
        AnchorPane resultPane = new AnchorPane();
        resultPane.getChildren().add(scrollPane);

        return scrollPane;
    }

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
            String updatedJSON = fileManager.changeOne(filePath);
            ScrollPane resultPane = createResultPane();
            mainLayout.getChildren().add(resultPane);
            resultText.setText(updatedJSON);
            fileInput.setText("");
        }
    }

}
