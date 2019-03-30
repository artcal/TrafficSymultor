package symulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Route {

    private List<RouteElement> route;
    private List<List<RouteElement>> routes;
    private List<Crossroad> crossroads;

    public Route(Point startingPoint, Point endingPoint, TrafficParticipant trafficParticipant, String priority) {
        generateRoute(startingPoint,endingPoint,trafficParticipant,priority);
    }

    //TODO dodać do parametrów drogę, jeśli nie będzie nullem to startingRoad będzię tą drogą
    //TODO dodanie trasy dla pieszego
    private void findRoutes(Point startingPoint, Point endingPoint, String trafficParticipant) {

        List<RouteElement> newRoute = new ArrayList<>();
        List<List<RouteElement> > unfinishedRoutes = new ArrayList<>();
        Road startingRoad = Main.startingPoints.get(startingPoint);

        for (Line line : startingRoad.getLines()) {
            if(line.getNextCrossroad() != null){
                newRoute.add(new RouteElement(startingRoad, line.getTrafficMovement()));
                unfinishedRoutes.add(newRoute);
            }
        }

        while (unfinishedRoutes != null){
            List<RouteElement> tempRoute;
            tempRoute = unfinishedRoutes.get(0);

            for (Line line : tempRoute.get(tempRoute.size() -1).getRoad().getLines() ) {
                if(line.getTrafficMovement().equals(tempRoute.get(tempRoute.size() - 1).getDirection())){
                    addNextRoad(line.getNextCrossroad(),tempRoute,unfinishedRoutes,endingPoint);
                }
            }

        }

    }

    private void addNextRoad(Crossroad currentCrossroad, List<RouteElement> tempRoute, List<List<RouteElement>> unfinishedRoutes, Point endingPoint) {
        if(!addLastRoad(currentCrossroad,tempRoute,endingPoint)){
            for (Road road :currentCrossroad.getRoads()) {
                if(!road.equals(tempRoute.get(tempRoute.size() - 1).getRoad())){
                    if(!road.getLines().get(0).getTrafficMovement().equals(getOppositeDirection(tempRoute.get(tempRoute.size() -1).getDirection()))){
                        boolean isCorrectRoad = false;
                        String direction = "";
                        for (Line line : road.getLines()) {
                            if(line.getNextCrossroad() != null || !line.getNextCrossroad().equals(currentCrossroad)){
                                isCorrectRoad = true;
                                direction = line.getTrafficMovement();
                            }
                        }
                        if(isCorrectRoad){
                            List<RouteElement> newRoute = tempRoute;
                            newRoute.add(new RouteElement(road,direction));
                            unfinishedRoutes.add(newRoute);
                        }
                    }
                }
            }
        }
        unfinishedRoutes.remove(0);
    }

    private String getOppositeDirection(String direction) {
        switch(direction){
            case "N": return "S";
            case "E": return "W";
            case "S": return "N";
            case "W": return "E";
            default: return "";
        }

    }

    private boolean addLastRoad(Crossroad nextCrossroad, List<RouteElement> tempRoute, Point endingPoint) {
        for (Road road :nextCrossroad.getRoads()) {
            if(comparePoints(road.getExitSpawnPoint(), endingPoint)){
                String direction = "";
                for (Line line : road.getLines()) {
                    if(!line.getNextCrossroad().equals(nextCrossroad)){
                        direction = line.getTrafficMovement();
                    }
                }
                tempRoute.add(new RouteElement(road, direction));
                routes.add(tempRoute);
                return true;
            }
        }
        return false;
    }

    private boolean comparePoints(Point exitSpawnPoint, Point endingPoint) {
        if(endingPoint.x == exitSpawnPoint.x){
            if (endingPoint.y == exitSpawnPoint.y+5 || endingPoint.y == exitSpawnPoint.y-5) return true;
        }else if(endingPoint.y == exitSpawnPoint.y) {
            if (endingPoint.x == exitSpawnPoint.x+5 || endingPoint.x == exitSpawnPoint.x-5) return true;
        }
        return false;
    }


    private void chooseRoute(String priority) {
        for (List<RouteElement> route: routes) {
            if(this.route == null || this.route.size() > route.size()){
                this.route = route;
            }
        }

    }

    public void generateRoute(Point startingPoint, Point endingPoint, TrafficParticipant trafficParticipant, String priority) {
        if(trafficParticipant.getClass() == Pedestrian.class) {
            findRoutes(startingPoint, endingPoint, "pedestrian");
        }else {
            findRoutes(startingPoint, endingPoint, "car");
        }
        chooseRoute(priority);

    }

    public List<RouteElement> getRoute() {
        return route;
    }

    public List<Crossroad> getCrossroads() {
        return crossroads;
    }
}
