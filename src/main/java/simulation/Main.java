package simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource = getClass().getClassLoader().getResource("simulation_window.fxml");
        if (resource != null) {
            Parent root = FXMLLoader.load(resource);
            primaryStage.setTitle("Traffic simulator");
            primaryStage.setScene(new Scene(root, 1000, 600));
            primaryStage.show();
        } else {
            throw new Exception("No FXML resource");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}


