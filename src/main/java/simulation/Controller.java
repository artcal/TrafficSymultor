package simulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Image simulationMap;

    @FXML
    ImageView imageView;
    @FXML
    private TextArea logs;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        URI simulationMapURI;
        try {
            URL url = getClass().getClassLoader().getResource("SimulatorMap.png");
            if(url != null) {
                simulationMapURI = url.toURI();
                simulationMap = new Image(simulationMapURI.toString());
                imageView.setImage(simulationMap);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
