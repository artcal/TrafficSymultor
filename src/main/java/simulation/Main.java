package simulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main extends Application {

    static List<ExitStartPoint> exitPoints,startingPoints;
    private static Initialize intialize;
    private List<Car> cars;
    private List<Pedestrian> pedestrians;

    @Override
    public void start(Stage primaryStage) throws IOException {
        URL resource = getClass().getClassLoader().getResource("symulation_window.fxml"); // FIXME handle null
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        generateExitSpawnPoints();
        cars = new ArrayList<>();
        pedestrians = new ArrayList<>();
    }

    static void generateExitSpawnPoints() {
        exitPoints = new ArrayList<>();
        startingPoints = new ArrayList<>();

        intialize = new Initialize();
        List<Road> roads = intialize.getRoads();
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

    private void initializeCars(int quantity) throws Exception{
        Random random = new Random();

        for (int i = 0; i < quantity; i++)
            cars.add(new Car("Car"+ i, startingPoints.get(random.nextInt(startingPoints.size())).getPosition(),
                    exitPoints.get(random.nextInt(exitPoints.size())).getPosition(), true,
                    random.nextInt(5)+2, 50));

    }


    public static void main(String[] args) {
       launch(args);
    }
}


