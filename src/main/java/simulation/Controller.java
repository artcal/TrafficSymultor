package simulation;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;

import java.awt.*;
import java.io.File;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    private Image simulationMap;
    static List<ExitStartPoint> exitPoints,startingPoints;
    private Initialize initialize;
    private List<Car> cars;
    private List<Pedestrian> pedestrians;

    @FXML
    private ImageView imageView;
    @FXML
    private TextArea logs;
    @FXML
    private AnchorPane content;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateExitSpawnPoints();
        cars = new ArrayList<>();
        pedestrians = new ArrayList<>();
        try {
            initializeCars(14);
            URI simulationMapURI;
            URL url = getClass().getClassLoader().getResource("SimulatorMap.png");
            if (url != null) {
                simulationMapURI = url.toURI();
                simulationMap = new Image(simulationMapURI.toString());
                imageView.setImage(simulationMap);
            }
            addCarsToMap();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addCarsToMap() {
        for (Car car : cars) {
            System.out.println(car.getStartingPoint().x + "\t" + car.getLine().getStart().x);
            content.getChildren().add(car.getTrafficParticipantImageView());
        }
    }

    private void generateExitSpawnPoints() {
        exitPoints = new ArrayList<>();
        startingPoints = new ArrayList<>();

        initialize = new Initialize();
        List<Road> roads = Initialize.getRoads();
        Point point;

        for(Road road: roads){
            if((point = road.getExitSpawnPoint()) != null){
                if(point.equals(road.getStart()) || point.equals(road.getEnd())){
                    if(road.getType().equals("1way")){
                        switch (road.getLines().get(0).getTrafficMovement()){
                            case "N":
                                if(point.y == 0){
                                    exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                }
                                else if(point.y == 800){
                                    startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                }
                                break;
                            case "E":
                                if(point.x == 0){
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                }
                                else if(point.x == 1300){
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                }
                                break;
                            case "S":
                                if(point.y == 800){
                                    exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                }
                                else if(point.y == 0){
                                    startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                }
                                break;
                            case "W":
                                if(point.x == 1300){
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                }
                                else if(point.x == 0){
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                }
                                break;
                        }
                    } else{
                        if(point.x == 0){
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                        }
                        else if(point.x == 1300){
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                        }
                        else if(point.y == 0){
                            startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                        }
                        else if(point.y == 800){
                            startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                        } else {
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                        }
                    }
                } else {
                    if(road.getLines().get(0).getTrafficMovement().equals("N") || road.getLines().get(0).getTrafficMovement().equals("S")){
                        if(point.x < road.getEnd().x){
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                        }else{
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                        }
                    } else{
                        if(point.y < road.getEnd().y){
                            startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                        }else {
                            startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                        }
                    }
                }
            }

        }
    }

    private void initializeCars(int quantity) throws Exception {
        Random random = new Random();

        for (int i = 0; i < quantity; i++) {
            int startingPointIndex = random.nextInt(startingPoints.size());
            int exitPointIndex;
            //noinspection StatementWithEmptyBody
            while ((exitPointIndex = random.nextInt(exitPoints.size())) == startingPointIndex)
                ;
            cars.add(new Car("Car" + i, startingPoints.get(startingPointIndex).getPosition(),
                    exitPoints.get(exitPointIndex).getPosition(), true,
                    random.nextInt(5) + 2, 50));
        }
    }


}
