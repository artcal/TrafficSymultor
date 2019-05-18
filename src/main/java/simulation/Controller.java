package simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Button;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private List<ExitStartPlace> exitPlaces;
    static List<ExitStartPlace> startingPlaces;
    private List<Car> cars;
    private List<Car> carsOnRoad;
    private static List<Pedestrian> pedestrians;
    private static boolean isSimulationStopped;
    private boolean isCycleFinished, isNextCycleReady;
    private int pedestriansQuantity;
    private static int cycleCounter = 0;
    private static boolean isSafeMode;

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
    @FXML
    private CheckBox cSafeMode;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logs.setText("Welcome to our simulator!");
        bStop.setDisable(true);
        try {
            generateExitSpawnPoints();
        } catch (Exception e) {
            e.printStackTrace();
        }
        cars = new ArrayList<>();
        carsOnRoad = new ArrayList<>();
        pedestrians = new ArrayList<>();
        URI simulationMapURI;
        URL url = getClass().getClassLoader().getResource("SimulatorMap.png");
        if (url != null) {
            try {
                simulationMapURI = url.toURI();
                Image simulationMap = new Image(simulationMapURI.toString());
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
        logs.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observableValue, String s, String t1) {
                logs.setScrollTop(Double.MAX_VALUE);
            }
        });
    }

    private void generateExitSpawnPoints() throws Exception {
        exitPlaces = new ArrayList<>();
        startingPlaces = new ArrayList<>();

        Initialize.main(null);
        List<Road> roads = Initialize.getRoads();
        Point point;

        for (Road road : roads) {
            if ((point = road.getExitSpawnPoint()) != null) {
                if (point.equals(road.getStart()) || point.equals(road.getEnd())) {
                    if (road.getType().equals("1way")) {
                        switch (road.getLines().get(0).getTrafficMovement()) {
                            case "N":
                                if (point.y == 0) {
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                                } else if (point.y == 800) {
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                                }
                                break;
                            case "E":
                                if (point.x == 0) {
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                                } else if (point.x == 1300) {
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                                }
                                break;
                            case "S":
                                if (point.y == 800) {
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                                } else if (point.y == 0) {
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                                }
                                break;
                            case "W":
                                if (point.x == 1300) {
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                                    startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                                } else if (point.x == 0) {
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                                    exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                                }
                                break;
                        }
                    } else {
                        if (point.x == 0) {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                        } else if (point.x == 1300) {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                        } else if (point.y == 0) {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                        } else if (point.y == 800) {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                        } else {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                        }
                    }
                } else {
                    if (road.getLines().get(0).getTrafficMovement().equals("N") || road.getLines().get(0).getTrafficMovement().equals("S")) {
                        if (point.x < road.getEnd().x) {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                        } else {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x, point.y - 5), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x, point.y + 5), road));
                        }
                    } else {
                        if (point.y < road.getEnd().y) {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                        } else {
                            startingPlaces.add(new ExitStartPlace(new Point(point.x + 5, point.y), road));
                            exitPlaces.add(new ExitStartPlace(new Point(point.x - 5, point.y), road));
                        }
                    }
                }
            }
        }
    }

    public void startSimulation() {
        logs.appendText("\n\n" + "Starting simulation...");
        bStart.setDisable(true);
        bStop.setDisable(false);
        setIsSafeMode(cSafeMode.isSelected());
        isSimulationStopped = false;
        isCycleFinished = false;
        isNextCycleReady = true;
        int carsQuantity = 1;
        pedestriansQuantity = 0;
        try {
            if (!tCarsQuantity.getText().equals(""))
                carsQuantity = Integer.parseInt(tCarsQuantity.getText());
            initializeCars(carsQuantity);
            if(!tPedestriansQuantity.getText().equals(""))
                pedestriansQuantity = Integer.parseInt(tPedestriansQuantity.getText());
            initializePedestrians(pedestriansQuantity);
            addPedestriansToMap();
            addStreetLightsToMap();
            Initialize.getStreetLights().forEach(StreetLights::start);
            logs.appendText("\nDone!");
            runSimulation();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private void addStreetLightsToMap() {
        Initialize.getLines().stream().filter(line -> line.getStreetLights() != null)
                .forEach(line -> content.getChildren().add(line.getImageView()));
        Initialize.getPedestrianCrossings().stream().filter(pedestrianCrossing -> pedestrianCrossing.getStreetLights() != null)
                .forEach(pedestrianCrossing -> content.getChildren().add(pedestrianCrossing.getImageView()));
    }

    private void initializePedestrians(int quantity) throws Exception {
        Random random = new Random();
        for (int i = 0; i < quantity; i++)
            pedestrians.add(new Pedestrian("Pedestrian" + i, Initialize.getRoads().
                    get(random.nextInt(Initialize.getRoads().size() - 1)), true));
    }

    private void initializeCars(int quantity) throws Exception {
        logs.appendText("\nInitializing cars...");
        Random random = new Random();
        for (int i = 0; i < quantity; i++) {
            int startingPointIndex = random.nextInt(startingPlaces.size());
            int exitPointIndex;
            //noinspection StatementWithEmptyBody
            while ((exitPointIndex = random.nextInt(exitPlaces.size())) == startingPointIndex)
                ;
            cars.add(new Car("Car" + i, startingPlaces.get(startingPointIndex).getPosition(),
                    exitPlaces.get(exitPointIndex).getPosition(), true,
                    random.nextInt(4) + 2));
        }
    }

    private void addPedestriansToMap() {
        for (Pedestrian pedestrian : pedestrians){
            if(!content.getChildren().contains(pedestrian.getTrafficParticipantImageView()))
                content.getChildren().add(pedestrian.getTrafficParticipantImageView());
        }
    }

    private void addCarToMap(Car car) {
        content.getChildren().add(car.getTrafficParticipantImageView());
    }

    public void stopSimulation() throws Exception {
        logs.appendText("\nStopping simulation...\nClearing map...");
        //StatisticsSaver statisticsSaver = new StatisticsSaver();
        bStop.setDisable(true);
        bStart.setDisable(false);
        isSimulationStopped = true;
        List<Node> nodes = new ArrayList<>();
        nodes.add(content.getChildren().get(0));
        content.getChildren().removeAll(content.getChildren());
        content.getChildren().add(nodes.get(0));
        List<Car> carsToDelete = cars;
        cars.removeAll(carsToDelete);
        carsToDelete = carsOnRoad;
        carsOnRoad.removeAll(carsToDelete);
        Initialize.getLines().forEach(line -> line.getCars().removeAll(line.getCars()));
        Initialize.getCrossroads().forEach(crossroad ->  crossroad.getCars().removeAll(crossroad.getCars()));
        List<Pedestrian> pedestriansToDelete = pedestrians;
        pedestrians.removeAll(pedestriansToDelete);
        Initialize.getPedestrianCrossings().forEach(pedestrianCrossing -> pedestrianCrossing.getPedestrians()
                .removeAll(pedestrianCrossing.getPedestrians()));
        for (StreetLights streetLights : Initialize.getStreetLights()) {
            streetLights.setRunningFalse();
        }
        logs.appendText("\nDone!");
    }

    private void runSimulation() throws Exception {
        if(cars.size() == 0 && carsOnRoad.size() == 0 && !isSimulationStopped)
            stopSimulation();
        while(isNextCycleReady && !isSimulationStopped){
            isNextCycleReady = false;
            cycle();
            cycleCounter++;
            while(pedestrians.size() < pedestriansQuantity){
                initializePedestrians(pedestriansQuantity - pedestrians.size());
                addPedestriansToMap();
            }
            List<Car> carsToRemove = new ArrayList<>();
            synchronized (this) {
                if(carsOnRoad.size() < 50)
                    for (Car car : cars) {
                        if (car.canEnterLine(car.getLine())) {
                            if(carsOnRoad.size() < 50) {
                                car.getLine().addCar(car);
                                carsOnRoad.add(car);
                                carsToRemove.add(car);
                                addCarToMap(car);
                                car.setCycleCount(cycleCounter);
                            }
                        }
                    }
            }
            carsToRemove.forEach(car -> cars.remove(car));
            carsOnRoad.forEach(Car::correctSpeed);
            carsOnRoad.forEach(car -> car.setLettingCarOnCrossroad(false));
            carsOnRoad.forEach(Car::move);
            carsOnRoad.stream().filter(Car::isInTrafficAccident).forEach(car -> logs.appendText(car.getName() + " is in accident\n"));
            pedestrians.forEach(Pedestrian::walk);
            content.getChildren().removeAll(carsOnRoad.stream().filter(TrafficParticipant::isEndReached)
                    .map(TrafficParticipant::getTrafficParticipantImageView).collect(Collectors.toList()));
            carsOnRoad.stream().filter(Car::isEndReached).forEach(car -> car.getLine().removeCar(car));
            carsOnRoad.removeAll(carsOnRoad.stream().filter(TrafficParticipant::isEndReached).collect(Collectors.toList()));
            content.getChildren().removeAll(pedestrians.stream().filter(TrafficParticipant::isEndReached)
                    .map(TrafficParticipant::getTrafficParticipantImageView).collect(Collectors.toList()));
            pedestrians.removeAll(pedestrians.stream().filter(TrafficParticipant::isEndReached)
                    .collect(Collectors.toList()));
            carsOnRoad.forEach(Car::setImagePosition);
            pedestrians.forEach(Pedestrian::setImagePosition);
            isCycleFinished = true;
        }
    }

    private void cycle() {
        int time = 100;
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(time), event -> {
            isNextCycleReady = true;
            if(isCycleFinished) {
                try {
                    runSimulation();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }));
        timeline.setCycleCount(1);
        timeline.play();
    }

    static int getCycleCounter() {
        return cycleCounter;
    }

    static boolean isIsSafeMode() {
        return isSafeMode;
    }

    private static void setIsSafeMode(boolean isSafeMode) {
        Controller.isSafeMode = isSafeMode;
    }
}
