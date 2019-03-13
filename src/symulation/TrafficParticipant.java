package symulation;

import java.awt.*;

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

    protected void generateRoute(){}

    protected void changeRoute(){}

    protected void checkTraffic(){}

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
