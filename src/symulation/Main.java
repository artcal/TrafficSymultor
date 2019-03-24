package symulation;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main extends Application {

    Map<Point,Road> exitPoints,startingPoints;


    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("symulator/sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();

        generateExitSpawnPoints();
    }

    private void generateExitSpawnPoints() {
        exitPoints = new HashMap<>();
        startingPoints = new HashMap<>();

        List<Road> roads = Initialize.getRoads();
        Point point;

        for(Road road: roads){
            if((point = road.getExitSpawnPoint()) != null){
                if(point == road.getStart() || point == road.getEnd()){
                    if(road.getType().equals("1way")){
                        switch (road.getLines().get(0).getTrafficMovement()){
                            case "N":
                                if(point.y == 0){
                                    exitPoints.put(new Point(point.x + 5, point.y), road);
                                    exitPoints.put(new Point(point.x - 5, point.y), road);
                                }
                                else if(point.y == 800){
                                    startingPoints.put(new Point(point.x + 5, point.y), road);
                                    startingPoints.put(new Point(point.x - 5, point.y), road);
                                }
                                break;
                            case "E":
                                if(point.x == 0){
                                    startingPoints.put(new Point(point.x, point.y + 5), road);
                                    startingPoints.put(new Point(point.x, point.y - 5), road);
                                }
                                else if(point.x == 1300){
                                    exitPoints.put(new Point(point.x, point.y + 5), road);
                                    exitPoints.put(new Point(point.x, point.y - 5), road);
                                }
                                break;
                            case "S":
                                if(point.y == 800){
                                    exitPoints.put(new Point(point.x + 5, point.y), road);
                                    exitPoints.put(new Point(point.x - 5, point.y), road);
                                }
                                else if(point.y == 0){
                                    startingPoints.put(new Point(point.x + 5, point.y), road);
                                    startingPoints.put(new Point(point.x - 5, point.y), road);
                                }
                                break;
                            case "W":
                                if(point.x == 1300){
                                    startingPoints.put(new Point(point.x, point.y + 5), road);
                                    startingPoints.put(new Point(point.x, point.y - 5), road);
                                }
                                else if(point.x == 0){
                                    exitPoints.put(new Point(point.x, point.y + 5), road);
                                    exitPoints.put(new Point(point.x, point.y - 5), road);
                                }
                                break;
                        }
                    } else{
                        if(point.x == 0){
                            startingPoints.put(new Point(point.x + 5, point.y), road);
                            exitPoints.put(new Point(point.x - 5, point.y), road);
                        }
                        else if(point.x == 1300){
                            startingPoints.put(new Point(point.x - 5, point.y), road);
                            exitPoints.put(new Point(point.x + 5, point.y), road);
                        }
                        else if(point.y == 0){
                            startingPoints.put(new Point(point.x, point.y - 5), road);
                            exitPoints.put(new Point(point.x, point.y + 5), road);
                        }
                        else if(point.y == 800){
                            startingPoints.put(new Point(point.x, point.y + 5), road);
                            exitPoints.put(new Point(point.x, point.y - 5), road);
                        }
                    }
                } else {
                    if(road.getLines().get(0).getTrafficMovement().equals("N") || road.getLines().get(0).getTrafficMovement().equals("S")){
                        if(point.x < road.getEnd().x){
                            startingPoints.put(new Point(point.x, point.y + 5), road);
                            exitPoints.put(new Point(point.x, point.y - 5), road);
                        }else{
                            startingPoints.put(new Point(point.x, point.y - 5), road);
                            exitPoints.put(new Point(point.x, point.y + 5), road);
                        }
                    } else{
                        if(point.y < road.getEnd().y){
                            startingPoints.put(new Point(point.x - 5, point.y), road);
                            exitPoints.put(new Point(point.x + 5, point.y), road);
                        }else {
                            startingPoints.put(new Point(point.x + 5, point.y), road);
                            exitPoints.put(new Point(point.x - 5, point.y), road);
                        }
                    }
                }
            }

        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}


