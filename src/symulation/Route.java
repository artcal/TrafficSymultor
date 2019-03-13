package symulation;

import java.awt.*;
import java.util.List;

public class Route {

    private List<List<Road>> routes;
    private List<Road> route;
    private List<Crossroad> crossroads;

    public Route() {}

    private void findRoutes(Point startingPoint, Point endingPoint, String trafficParticipant) {

    }

    private void chooseRoute() {

    }

    private void addCrossroads() {

    }

    public void generateRoute(Point startingPoint, Point endingPoint, TrafficParticipant trafficParticipant) {
        if(trafficParticipant.getClass() == Pedestrian.class) {
            findRoutes(startingPoint, endingPoint, "pedestrian");
        }else {
            findRoutes(startingPoint, endingPoint, "car");
        }
        chooseRoute();

    }

    public List<Road> getRoute() {
        return route;
    }

    public List<Crossroad> getCrossroads() {
        return crossroads;
    }
}
