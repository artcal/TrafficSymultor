package symulation;

import java.awt.Point;
import java.util.List;


public class TrafficParticipant {

    protected String name;
    protected Point startingPoint;
    protected Point endingPoint;
    protected Point position;
    protected Route route;
    protected boolean isSafe;
    protected Road road;
    protected Line line;
    protected int ignoreTraffic;

    public TrafficParticipant(String name, Point startingPoint, Point endingPoint, boolean isSafe) {
        this.name = name;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.position = startingPoint;
        this.isSafe = isSafe;
    }

    public void changeParameters(String name, Point startingPoint, Point endingPoint, boolean isSafe){
        this.name = name;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.isSafe = isSafe;
    }

    protected void generateRoute(){
        route = new Route(startingPoint,endingPoint,this,null);
    }

    protected void changeRoute(){}

    protected void checkTraffic(List<TrafficParticipant> trafficParticipants){
        for (TrafficParticipant trafficParticipant : trafficParticipants) {
            if(!trafficParticipant.equals(this)){
                if(trafficParticipant.line.equals(this.line)){

                }
            }

        }
    }

    protected boolean checkTrafficLights(){return true;}

    public String getName() {
        return name;
    }

    public Point getPosition() {
        return position;
    }

    public Route getRoute() {
        return route;
    }

    public boolean isSafe() {
        return isSafe;
    }



}
