package simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

class Route {

    private List<RouteElement> route;
    private List<List<RouteElement>> routes;
    private List<List<RouteElement>> unfinishedRoutes;
    private TrafficParticipant trafficParticipant;
    private Point startingPoint, endingPoint;

    Route(Point startingPoint, Point endingPoint, TrafficParticipant trafficParticipant,
          String priority, Road currentRoad) throws Exception {
        route = new ArrayList<>();
        routes = new ArrayList<>();
        unfinishedRoutes = new ArrayList<>();
        this.trafficParticipant = trafficParticipant;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        generateRoute(priority, currentRoad);
    }

    private void generateRoute(String priority, Road currentRoad) throws Exception {
        findRoutes(currentRoad);
        if(trafficParticipant.getClass().equals(Car.class))
            chooseRoute(priority);
    }

    private void findRoutes(Road currentRoad) throws Exception {
        if (isTrafficParticipantACarClass()) {
            Road startingRoad = currentRoad;
            if (startingRoad == null)
                startingRoad = getStartingRoad();
            addFirstRouteElement(startingRoad);

            while (unfinishedRoutes.size() > 0)
                finishRoutes();
        } else {
            makePedestrianRoute(currentRoad);
        }
    }

    private boolean isTrafficParticipantACarClass() {
        return trafficParticipant.getClass().equals(Car.class);
    }

    private Road getStartingRoad() throws Exception {
        for (ExitStartPlace exitStartPlace : Controller.startingPlaces) {
            if (exitStartPlace.getPosition().equals(startingPoint))
                return exitStartPlace.getRoad();
        }
        throw new Exception("No road for this startingPoint");
    }

    private void addFirstRouteElement(Road startingRoad) throws Exception {
        for (Line line : startingRoad.getLines()) {
            if (line.getNextCrossroad() != null) {
                List<RouteElement> newRoute = new ArrayList<>();
                newRoute.add(new RouteElement(startingRoad, line.getTrafficMovement()));
                unfinishedRoutes.add(newRoute);
                return;
            }
        }
        throw new Exception("First road not added to unfinishedRoutes");
    }

    private void finishRoutes() throws Exception {
        List<RouteElement> tempRoute;
        tempRoute = unfinishedRoutes.get(0);
        boolean isOneWay = false;
        for (Line line : getLastRouteElement(tempRoute).getRoad().getLines()) {
            if (isLineAndRouteDirectionEqual(line, getLastRouteElement(tempRoute)) && !isOneWay) {
                addNextRoad(line.getNextCrossroad(), tempRoute);
                isOneWay = getLastRouteElement(tempRoute).getRoad().getType().equals("1way");
            }
        }
        unfinishedRoutes.remove(0);
    }

    private boolean isLineAndRouteDirectionEqual(Line line, RouteElement routeElement) {
        return line.getTrafficMovement().equals(routeElement.getDirection());
    }

    private void addNextRoad(Crossroad currentCrossroad, List<RouteElement> tempRoute) throws Exception {
        if (!isLastRoadAdded(currentCrossroad, tempRoute))
            for (Road road : currentCrossroad.getRoads())
                tryAddingNewRoadToUnfinishedRoutes(currentCrossroad, tempRoute, road);
    }

    private boolean isLastRoadAdded(Crossroad currentCrossroad, List<RouteElement> tempRoute) throws Exception {
        return tryAddingLastRoad(currentCrossroad, tempRoute);
    }

    private boolean tryAddingLastRoad(Crossroad currentCrossroad, List<RouteElement> tempRoute) throws Exception {
        if (currentCrossroad != null)
            for (Road road : currentCrossroad.getRoads())
                if (isExitSpawnPointAnEndingPoint(road.getExitSpawnPoint())
                        && isNewRoadNotTurningBack(getLastRouteElement(tempRoute), road))
                    return addLastRoad(currentCrossroad, tempRoute, road);
        return false;
    }

    private boolean isExitSpawnPointAnEndingPoint(Point exitSpawnPoint) {
        if (exitSpawnPoint != null) {
            if (endingPoint.x == exitSpawnPoint.x)
                return endingPoint.y == exitSpawnPoint.y + 5 || endingPoint.y == exitSpawnPoint.y - 5;
            else if (endingPoint.y == exitSpawnPoint.y)
                return endingPoint.x == exitSpawnPoint.x + 5 || endingPoint.x == exitSpawnPoint.x - 5;
        }
        return false;
    }

    private boolean addLastRoad(Crossroad currentCrossroad, List<RouteElement> tempRoute, Road road) throws Exception {
        String direction = getDirection(currentCrossroad, road);
        tempRoute.add(new RouteElement(road, direction));
        routes.add(tempRoute);
        return true;
    }

    private String getDirection(Crossroad currentCrossroad, Road road) throws Exception {
        for (Line line : road.getLines())
            if (isLineDirectionCorrect(currentCrossroad, line))
                return line.getTrafficMovement();
        throw new Exception("None of the lines is in correct direction, wrong road");
    }

    private boolean isLineDirectionCorrect(Crossroad currentCrossroad, Line line) {
        if (line.getNextCrossroad() != null)
            return !line.getNextCrossroad().equals(currentCrossroad);
        else
            return true;
    }

    private void tryAddingNewRoadToUnfinishedRoutes(Crossroad currentCrossroad, List<RouteElement> tempRoute,
                                                    Road road) throws Exception {
        boolean isNewRoad = isNewRoad(tempRoute, road);
        if (isNewRoad)
            if (isNewRoadNotTurningBack(getLastRouteElement(tempRoute), road)) {
                boolean isCorrectRoad = false;
                String direction = "";
                for (Line line : road.getLines())
                    if (line.getNextCrossroad() != null)
                        if (!line.getNextCrossroad().equals(currentCrossroad)) {
                            isCorrectRoad = true;
                            direction = line.getTrafficMovement();
                        }
                if (isCorrectRoad) {
                    List<RouteElement> newRoute = new ArrayList<>(tempRoute);
                    newRoute.add(new RouteElement(road, direction));
                    unfinishedRoutes.add(newRoute);
                }
            }
    }

    private boolean isNewRoad(List<RouteElement> tempRoute, Road road) {
        for (RouteElement routeElement : tempRoute)
            if (isRoadsEquals(routeElement.getRoad(), road))
                return false;
        return true;
    }

    private boolean isRoadsEquals(Road roadFromRoute, Road road) {
        return roadFromRoute.equals(road);
    }

    private boolean isNewRoadNotTurningBack(RouteElement routeElement, Road road) throws Exception {
        for (Line line : road.getLines())
            if (!isLineTurningBack(routeElement, line))
                return true;
        return false;
    }

    private boolean isLineTurningBack(RouteElement routeElement, Line line) throws Exception {
        return line.getTrafficMovement().equals(getOppositeDirection(routeElement.getDirection()));
    }

    private String getOppositeDirection(String direction) throws Exception {
        switch (direction) {
            case "N":
                return "S";
            case "E":
                return "W";
            case "S":
                return "N";
            case "W":
                return "E";
            default:
                throw new Exception("Wrong direction, need [N,E,S,W]");
        }
    }

    private RouteElement getLastRouteElement(List<RouteElement> routeElementList) {
        return routeElementList.get(routeElementList.size() - 1);
    }

    private void makePedestrianRoute(Road currentRoad) {
        if(isRoad2way(currentRoad))
            generatePedestrianRouteFor2wayRoad(isRoadVertical(currentRoad), currentRoad);
        else
            generatePedestrianRouteFor1wayRoad(isRoadVertical(currentRoad), currentRoad);
    }

    private void generatePedestrianRouteFor1wayRoad(boolean isRoadVertical, Road currentRoad) {
        Random random = new Random();
        boolean isLeftOrUpSide;
        String startingDirection;
        int startingPointComponent = getRandomPointComponent(currentRoad, isRoadVertical);
        int endingPointComponent = getRandomPointComponent(currentRoad, isRoadVertical);
        PedestrianCrossing pedestrianCrossing = currentRoad.getPedestrianCrossings().
                get(random.nextInt(currentRoad.getPedestrianCrossings().size()));
        if(isRoadVertical) {
            isLeftOrUpSide = currentRoad.getEnd().x
                    < currentRoad.getPedestrianCrossings().get(0).getPosition().x;
            startingDirection = pedestrianCrossing.getPosition().y
                    < startingPointComponent ? "N" : "S";
        } else {
            isLeftOrUpSide = currentRoad.getEnd().y
                    < currentRoad.getPedestrianCrossings().get(0).getPosition().y;
            startingDirection = pedestrianCrossing.getPosition().x
                    < startingPointComponent ? "W" : "E";
        }
        setStartingAndEndingPoint(currentRoad, startingPointComponent, endingPointComponent, isLeftOrUpSide);
        generatePedestrianRoute(startingDirection, pedestrianCrossing, isLeftOrUpSide, isRoadVertical);
    }

    private void generatePedestrianRouteFor2wayRoad(boolean isRoadVertical, Road currentRoad) {
        Random random = new Random();
        boolean isLeftOrUpSide = random.nextBoolean();
        String startingDirection;
        int startingPointComponent = getRandomPointComponent(currentRoad, isRoadVertical);
        int endingPointComponent = getRandomPointComponent(currentRoad, isRoadVertical);
        PedestrianCrossing pedestrianCrossing = currentRoad.getPedestrianCrossings().
                get(random.nextInt(currentRoad.getPedestrianCrossings().size()));
        if(isRoadVertical) {
            startingDirection = pedestrianCrossing.getPosition().y
                    < startingPointComponent ? "N" : "S";
        } else {
            startingDirection = pedestrianCrossing.getPosition().x
                    < startingPointComponent ? "W" : "E";
        }
        setStartingAndEndingPoint(currentRoad, startingPointComponent, endingPointComponent, isLeftOrUpSide);
        generatePedestrianRoute(startingDirection, pedestrianCrossing, isLeftOrUpSide, isRoadVertical);
    }

    private int getRandomPointComponent(Road road, boolean isRoadVertical) {
        Random random = new Random();
        if(isRoadVertical)
            return random.nextInt(road.getEnd().y - road.getStart().y - 60)
                    + road.getStart().y + 30;
        else
            return random.nextInt(road.getEnd().x - road.getStart().x - 60)
                    + road.getStart().x + 30;
    }

    private void setStartingAndEndingPoint(Road currentRoad, int startingPointComponent, int endingPointComponent,
                                           boolean isLeftOrUpSide) {
        int startingPointComponentShift = isLeftOrUpSide ? -13 : 13;
        int endingPointComponentShift;
        if (currentRoad.getType().equals("1way"))
            endingPointComponentShift = isLeftOrUpSide ? 37 : -37;
        else
            endingPointComponentShift = -startingPointComponentShift;
        if (isRoadVertical(currentRoad)) {
            startingPoint = new Point(currentRoad.getEnd().x + startingPointComponentShift, startingPointComponent);
            endingPoint = new Point(currentRoad.getEnd().x + endingPointComponentShift, endingPointComponent);
        } else {
            startingPoint = new Point(startingPointComponent, currentRoad.getEnd().y + startingPointComponentShift);
            endingPoint = new Point(startingPointComponent, currentRoad.getEnd().y + endingPointComponentShift);
        }
    }

    private void generatePedestrianRoute(String startingDirection, PedestrianCrossing pedestrianCrossing,
                                         boolean isLeftOrUpSide, boolean isRoadVertical) {
        if (isRoadVertical) {
            route.add(new RouteElement(new Road(startingPoint), startingDirection));
            route.add(new RouteElement(new Road(new Point(startingPoint.x, pedestrianCrossing.getPosition().y),
                    pedestrianCrossing),isLeftOrUpSide ? "E" : "W"));
            route.add(new RouteElement(new Road(new Point(startingPoint.x
                    + (isLeftOrUpSide ? 1 : -1) * (pedestrianCrossing.getWidth() + 6),
                    pedestrianCrossing.getPosition().y)), startingDirection.equals("N") ? "S" : "N"));
        } else {
            route.add(new RouteElement(new Road(startingPoint), startingDirection));
            route.add(new RouteElement(new Road(new Point(pedestrianCrossing.getPosition().x, startingPoint.y),
                    pedestrianCrossing),isLeftOrUpSide ? "S" : "N"));
            route.add(new RouteElement(new Road(new Point(pedestrianCrossing.getPosition().x,
                    startingPoint.y + (isLeftOrUpSide ? 1 : -1) * (pedestrianCrossing.getWidth() + 6))),
                    startingDirection.equals("W") ? "E" : "W"));
        }
    }

    private boolean isRoad2way(Road road) {
        return road.getType().equals("2way");
    }

    private boolean isRoadVertical(Road road) {
        return road.getLines().get(0).getTrafficMovement().equals("N")
                || road.getLines().get(0).getTrafficMovement().equals("S");
    }

    private void chooseRoute(String priority) {
        if (priority == null) {
            Random random = new Random();
            this.route = routes.get(random.nextInt(routes.size()));
        }
    }

    List<RouteElement> getRoute() {
        return route;
    }

    Point getStartingPoint() {
        return startingPoint;
    }

    Point getEndingPoint() {
        return endingPoint;
    }
}
