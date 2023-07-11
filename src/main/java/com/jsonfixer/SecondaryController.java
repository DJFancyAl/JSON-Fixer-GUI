package com.jsonfixer;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class SecondaryController {

    @FXML
    private TextField fileLocation;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }
}