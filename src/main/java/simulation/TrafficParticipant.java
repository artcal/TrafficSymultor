package simulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Point;
import java.net.URI;
import java.net.URL;
import java.util.List;


public class TrafficParticipant {

    String name;
    Point startingPoint;
    Point endingPoint;
    Point position;
    List<RouteElement> route;
    boolean isSafe;
    boolean isEndReached;
    protected Road road;
    Line line, nextLine;
    protected int ignoreTraffic;
    ImageView trafficParticipantImageView;

    TrafficParticipant(String name, boolean isSafe, String imageString) throws Exception {
        this.name = name;
        this.isSafe = isSafe;
        this.isEndReached = false;
        URL carUrl = getClass().getClassLoader().getResource(imageString);
        if(carUrl != null) {
            URI carURI = carUrl.toURI();
            Image carImage = new Image(carURI.toString());
            this.trafficParticipantImageView = new ImageView(carImage);
        } else
            throw new Exception("Wrong Traffic Participant image name");
    }

    void changeParameters(String name, Point startingPoint, Point endingPoint, boolean isSafe){
        this.name = name;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.isSafe = isSafe;
    }

    void generateRoute() throws Exception {
        Route route = new Route(startingPoint,endingPoint,this,null, road);
        this.route = route.getRoute();
        if(this.getClass().equals(Pedestrian.class)) {
            this.startingPoint = route.getStartingPoint();
            this.endingPoint = route.getEndingPoint();
        }
    }

    void imageOrientation() {
        switch (line.getTrafficMovement()){
            case "N":
                trafficParticipantImageView.setRotate(270);break;
            case "E":
                trafficParticipantImageView.setRotate(0);break;
            case "S":
                trafficParticipantImageView.setRotate(90);break;
            case "W":
                trafficParticipantImageView.setRotate(180);break;
        }
    }

    void changeRoute(){}

    //TODO check if traffic participant is on same road/line and distance in front
    void checkTraffic(List<TrafficParticipant> trafficParticipants){

    }

    //TODO check if one can go through traffic lights
    boolean checkTrafficLights(){
        return true;
    }

    String getName() {
        return name;
    }

    Point getPosition() {
        return position;
    }

    List<RouteElement> getRoute() {
        return route;
    }

    boolean isSafe() {
        return isSafe;
    }

    ImageView getTrafficParticipantImageView() {
        return trafficParticipantImageView;
    }

    Line getLine() {
        return line;
    }

    boolean isEndReached() {
        return isEndReached;
    }
}
