package com.jsonfixer;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.util.prefs.Preferences;
import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {
    private static Scene scene;
    private static Preferences preferences;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("JSON Editor");
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static Preferences getPreferences() {
        if (preferences == null) {
            preferences = Preferences.userNodeForPackage(App.class);
        }
        return preferences;
    }

    public static void main(String[] args) {
        launch();
    }

}