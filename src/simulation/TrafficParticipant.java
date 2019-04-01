package simulation;

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
        road = route.getRoute().get(0).getRoad();
        for (Line line : road.getLines()) {
            if(line.getStart().equals(startingPoint) || line.getEnd().equals(startingPoint)){
                this.line = line;
                break;
            }else{
                String direction = line.getTrafficMovement();
                if(direction.equals("N") || direction.equals("S")){
                    if(this.line == null || Math.pow(this.line.getEnd().x - startingPoint.x,2) == 25){
                        this.line = line;
                        break;
                    }
                }else{
                    if(this.line == null || Math.pow(this.line.getEnd().y - startingPoint.y,2) == 25){
                        this.line = line;
                        break;
                    }
                }
            }

        }
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
