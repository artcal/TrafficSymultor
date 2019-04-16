package simulation;

import java.awt.*;

class Pedestrian extends TrafficParticipant {

    Pedestrian(String name, Road road, boolean isSafe) throws Exception {
        super(name, isSafe, "car.png");
        this.road = road;
        generateRoute();
    }

    private void walkThroughRoad(){

    }
    private void walkThroughPedestrianCrossing(){

    }

    private void waitForIt(){ // nazwa do zmiany

    }
}
