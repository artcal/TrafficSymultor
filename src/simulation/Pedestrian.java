package simulation;

import java.awt.*;

public class Pedestrian extends TrafficParticipant {

    public Pedestrian(String name, Point startingPoint, Point endingPoint, boolean isSafe) {
        super(name, startingPoint, endingPoint, isSafe);
    }

    @Override
    protected void generateRoute(){
        //route = new Route();
       // route.generateRoute(startingPoint,endingPoint,this,"shortest");
       // road = route.getRoute().get(0);
    }

    private void walkThroughRoad(){

    }
    private void walkThroughPedestrianCrossing(){

    }

    private void waitForIt(){ // nazwa do zmiany

    }
}
