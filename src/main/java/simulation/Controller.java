package simulation;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.awt.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    private Image simulationMap;
    static List<ExitStartPoint> exitPoints, startingPoints;
    private Initialize initialize;
    private static List<Car> cars;
    private List<Pedestrian> pedestrians;
    private static boolean isSimulationStopped;
    static boolean isCycleFinished, isNextCycleReady;

    @FXML
    private ImageView imageView;
    @FXML
    private TextArea logs;
    @FXML
    private AnchorPane content;
    @FXML
    private Button bStart;
    @FXML
    private Button bStop;
    @FXML
    private TextField tCarsQuantity;
    @FXML
    private TextField tPedestriansQuantity;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        generateExitSpawnPoints();
        cars = new ArrayList<>();
        pedestrians = new ArrayList<>();
        URI simulationMapURI;
        URL url = getClass().getClassLoader().getResource("SimulatorMap.png");
        if (url != null) {
            try {
                simulationMapURI = url.toURI();
                simulationMap = new Image(simulationMapURI.toString());
                imageView.setImage(simulationMap);
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
        } else {
            try {
                throw new Exception("No background image resource");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void generateExitSpawnPoints() {
        exitPoints = new ArrayList<>();
        startingPoints = new ArrayList<>();

        initialize = new Initialize();
        List<Road> roads = Initialize.getRoads();
        Point point;

        for (Road road : roads) {
            if ((point = road.getExitSpawnPoint()) != null) {
                if (point.equals(road.getStart()) || point.equals(road.getEnd())) {
                    if (road.getType().equals("1way")) {
                        switch (road.getLines().get(0).getTrafficMovement()) {
                            case "N":
                                if (point.y == 0) {
                                    exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                } else if (point.y == 800) {
                                    startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                }
                                break;
                            case "E":
                                if (point.x == 0) {
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                } else if (point.x == 1300) {
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                }
                                break;
                            case "S":
                                if (point.y == 800) {
                                    exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                } else if (point.y == 0) {
                                    startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                                }
                                break;
                            case "W":
                                if (point.x == 1300) {
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                } else if (point.x == 0) {
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                                    exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                                }
                                break;
                        }
                    } else {
                        if (point.x == 0) {
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                        } else if (point.x == 1300) {
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                        } else if (point.y == 0) {
                            startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                        } else if (point.y == 800) {
                            startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                        } else {
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                        }
                    }
                } else {
                    if (road.getLines().get(0).getTrafficMovement().equals("N") || road.getLines().get(0).getTrafficMovement().equals("S")) {
                        if (point.x < road.getEnd().x) {
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                        } else {
                            startingPoints.add(new ExitStartPoint(new Point(point.x, point.y - 5), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x, point.y + 5), road));
                        }
                    } else {
                        if (point.y < road.getEnd().y) {
                            startingPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                        } else {
                            startingPoints.add(new ExitStartPoint(new Point(point.x + 5, point.y), road));
                            exitPoints.add(new ExitStartPoint(new Point(point.x - 5, point.y), road));
                        }
                    }
                }
            }
        }
    }

    public void startSimulation(ActionEvent actionEvent) {
        isSimulationStopped = false;
        isCycleFinished = false;
        isNextCycleReady = true;
        int carsQuantity = 1;
        int pedestriansQuantity = 0;
        try {
            if (!tCarsQuantity.getText().equals(""))
                carsQuantity = Integer.parseInt(tCarsQuantity.getText());
            initializeCars(carsQuantity);
            if(!tPedestriansQuantity.getText().equals(""))
                pedestriansQuantity = Integer.parseInt(tPedestriansQuantity.getText());
            initializePedestrians(pedestriansQuantity);
            addCarsToMap();
            addPedestriansToMap();
            runSimulation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void initializePedestrians(int quantity) throws Exception {
        Random random = new Random();
        for (int i = 0; i < quantity; i++)
            pedestrians.add(new Pedestrian("Pedestrian" + i, Initialize.getRoads().
                    get(random.nextInt(Initialize.getRoads().size() - 1)), true));
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
                    random.nextInt(4) + 2));
        }
    }

    private void addPedestriansToMap() {
        for (Pedestrian pedestrian : pedestrians)
            content.getChildren().add(pedestrian.getTrafficParticipantImageView());
    }

    private void addCarsToMap() {
        for (Car car : cars)
            content.getChildren().add(car.getTrafficParticipantImageView());
    }

    public void stopSimulation(ActionEvent actionEvent) {
        isSimulationStopped = true;
        List<Node> nodes = new ArrayList<>();
        nodes.add(content.getChildren().get(0));
        content.getChildren().removeAll(content.getChildren());
        content.getChildren().add(nodes.get(0));
        cars.removeAll(cars);
        pedestrians.removeAll(pedestrians);
    }

    private void runSimulation() {
        while(isNextCycleReady && !isSimulationStopped){
            isNextCycleReady = false;
            cycle(100);
            cars.stream().forEach(car -> car.correctSpeed());
            cars.stream().forEach(car -> car.move());
            pedestrians.stream().forEach(pedestrian -> pedestrian.walk());
            content.getChildren().removeAll(cars.stream().filter(car -> car.isEndReached()).map(car ->
                    car.getTrafficParticipantImageView()).collect(Collectors.toList()));
            cars.removeAll(cars.stream().filter(car -> car.isEndReached()).collect(Collectors.toList()));
            content.getChildren().removeAll(pedestrians.stream().filter(pedestrian -> pedestrian.isEndReached())
                    .map(pedestrian -> pedestrian.getTrafficParticipantImageView()).collect(Collectors.toList()));
            pedestrians.removeAll(pedestrians.stream().filter(pedestrian -> pedestrian.isEndReached())
                    .collect(Collectors.toList()));
            cars.stream().forEach(car -> car.setImagePosition());
            pedestrians.stream().forEach(pedestrian -> pedestrian.setImagePosition());
            isCycleFinished = true;
        }
    }

    private void cycle(int time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(time), event -> {
            isNextCycleReady = true;
            if(isCycleFinished) {
                runSimulation();
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }
}
