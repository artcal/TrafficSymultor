package simulation;

import com.sun.org.apache.xml.internal.security.Init;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.List;
import java.util.stream.Collectors;

public class Controller implements Initializable {

    private List<ExitStartPlace> exitPlaces;
    static List<ExitStartPlace> startingPlaces;
    private List<Car> cars;
    private List<Car> carsOnRoad;
    private static List<Pedestrian> pedestrians;
    private static boolean isSimulationStopped, isAccident = false;
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
    @FXML
    private Slider slider;
    @FXML
    private Label sliderValue;
    @FXML
    private TextField tLightsLength;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        logs.setText("Welcome to our simulator!");
        tLightsLength.setText(Initialize.getLightsLength() / 1000 + "");
        bStop.setDisable(true);
        cars = new ArrayList<>();
        carsOnRoad = new ArrayList<>();
        pedestrians = new ArrayList<>();
        slider.setMin(0);
        slider.setMax(100);
        slider.setBlockIncrement(1);
        int valueOfSlider = (int) slider.getValue();
        sliderValue.setText(valueOfSlider + "%");
        slider.valueProperty().addListener((observableValue, number, t1) -> {
            int valueOfSlider1 = (int) slider.getValue();
            sliderValue.setText(valueOfSlider1 + "%");
        });
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
        logs.textProperty().addListener((observableValue, s, t1) -> logs.setScrollTop(Double.MAX_VALUE));
        JsonHandler statisticsToJson = new JsonHandler();
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
        logs.appendText("\n\n" + "Starting simulation... " + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()));
        Initialize.setLightsLength(Integer.parseInt(tLightsLength.getText()) * 1000);
        try {
            generateExitSpawnPoints();
        } catch(Exception e) {
            e.printStackTrace();
        }
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
            logs.appendText("\nDone!\n");
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
            boolean isSafe = true;
            int startingPointIndex = random.nextInt(startingPlaces.size());
            int exitPointIndex;
            //noinspection StatementWithEmptyBody
            while ((exitPointIndex = random.nextInt(exitPlaces.size())) == startingPointIndex)
                ;
            if(random.nextInt(100) < (int)slider.getValue() && !isSafeMode)
                isSafe = false;
            cars.add(new Car("Car" + i, startingPlaces.get(startingPointIndex).getPosition(),
                    exitPlaces.get(exitPointIndex).getPosition(), isSafe,
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
        logs.appendText("\nStopping simulation... "
                + new SimpleDateFormat("HH:mm:ss").format(Calendar.getInstance().getTime()) + "\nClearing map...");
        StatisticsSaver statisticsSaver = new StatisticsSaver();
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
        updateStatistics();
    }

    private void updateStatistics() {
        StatisticsProcessing statisticsProcessing = new StatisticsProcessing();
        statisticsProcessing.updateStatisticsJson();
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
                if(carsOnRoad.size() < 50) {
                    for (Car car : cars) {
                        if (car.canEnterLine(car.getLine())) {
                            if (carsOnRoad.size() < 50) {
                                car.getLine().addCar(car);
                                carsOnRoad.add(car);
                                carsToRemove.add(car);
                                addCarToMap(car);
                                car.setCycleCount(cycleCounter);
                            }
                        }
                    }
                }
            }
            carsToRemove.forEach(car -> cars.remove(car));
            carsOnRoad.forEach(Car::correctSpeed);
            carsOnRoad.forEach(car -> car.setLettingCarOnCrossroad(false));
            carsOnRoad.forEach(car -> {
                car.move();
                if(car.isInAccident()) {
                    logs.appendText(car.getName() + " is in accident\n");
                    car.setInAccident(false);
                }
                if(car.isEndReached()) {
                    content.getChildren().remove(car.getTrafficParticipantImageView());
                }
            });
            pedestrians.forEach(pedestrian -> {
                pedestrian.walk();
                if(pedestrian.isEndReached()) {
                    content.getChildren().remove(pedestrian.getTrafficParticipantImageView());
                }
            });
            carsOnRoad.removeAll(carsOnRoad.stream().filter(TrafficParticipant::isEndReached).collect(Collectors.toList()));
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

    static boolean isSafeMode() {
        return isSafeMode;
    }

    private static void setIsSafeMode(boolean isSafeMode) {
        Controller.isSafeMode = isSafeMode;
    }

    public static void setIsAccident(boolean isAccident) {
        Controller.isAccident = isAccident;
    }
}
/*TRASH
//            if(isAccident){
//                carsOnRoad.forEach(car -> {
//                    for (int i = 0; i < 3; i++ ) {
//                        if (car.getRoute().size() > i) {
//                            RouteElement routeElement = car.getRoute().get(i);
//                            if (routeElement.getRoad().getLines().get(0).isClosed()) {
//                                if (routeElement.getRoad().getLines().get(0).getTrafficMovement().equals(routeElement.getDirection())) {
//                                    try {
//                                        car.changeRoute();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                            if (routeElement.getRoad().getLines().get(1).isClosed()) {
//                                if (routeElement.getRoad().getLines().get(1).getTrafficMovement().equals(routeElement.getDirection())) {
//                                    try {
//                                        car.changeRoute();
//                                    } catch (Exception e) {
//                                        e.printStackTrace();
//                                    }
//                                }
//                            }
//                        }
//                    }
//                });
//                isAccident = false;
//            }
 */
