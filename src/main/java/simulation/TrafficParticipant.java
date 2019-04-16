package simulation;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.awt.Point;
import java.net.URI;
import java.net.URL;
import java.util.List;


public class TrafficParticipant {

    protected String name;
    protected Point startingPoint;
    protected Point endingPoint;
    protected Point position;
    protected List<RouteElement> route;
    protected boolean isSafe;
    protected boolean isEndReached;
    protected Road road;
    protected Line line, nextLine;
    protected int ignoreTraffic;
    protected ImageView trafficParticipantImageView;

    public TrafficParticipant(String name, boolean isSafe, String imageString) throws Exception {
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

    public void changeParameters(String name, Point startingPoint, Point endingPoint, boolean isSafe){
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

    protected void changeRoute(){}

    protected void checkTraffic(List<TrafficParticipant> trafficParticipants){
        for (TrafficParticipant trafficParticipant : trafficParticipants)
            if(!trafficParticipant.equals(this))
                if(trafficParticipant.line.equals(this.line)){

                }
    }

    protected boolean checkTrafficLights(){return true;}

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public List<RouteElement> getRoute() {
        return route;
    }

    public boolean isSafe() {
        return isSafe;
    }

    public ImageView getTrafficParticipantImageView() {
        return trafficParticipantImageView;
    }

    public Line getLine() {
        return line;
    }

    public Point getStartingPoint() {
        return startingPoint;
    }

    public boolean isEndReached() {
        return isEndReached;
    }
}
