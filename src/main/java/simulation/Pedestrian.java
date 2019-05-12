package simulation;

import java.awt.*;

class Pedestrian extends TrafficParticipant {

    private String currentDirection;
    private int distance;
    private PedestrianCrossing pedestrianCrossing;

    Pedestrian(String name, Road road, boolean isSafe) throws Exception {
        super(name, isSafe, "pedestrian.png");
        this.road = road;
        generateRoute();
        currentDirection = route.get(0).getDirection();
        route.remove(0);
        position = startingPoint;
        setImagePosition();
    }

    void walk() {
        int SPEED = 20;
        distance += SPEED;
        //TODO check if car is on pedestrian crossing, this.road is not actual road
        //if(isCarOnPedestrianCrossing())
        if(setIsWalkig())
            switch (currentDirection) {
                case "N":
                    position = new Point(position.x, position.y - distance / 50);
                    break;
                case "E":
                    position = new Point(position.x + distance / 50, position.y);
                    break;
                case "S":
                    position = new Point(position.x, position.y + distance / 50);
                    break;
                case "W":
                    position = new Point(position.x - distance / 50, position.y);
                    break;
            }
        distance %= 50;
        if(route.size() > 0) {
            if (isPointReached(route.get(0).getRoad().getStart())) {
                if(pedestrianCrossing != null){
                    pedestrianCrossing.removePedestrian(this);
                    pedestrianCrossing = null;
                }
                currentDirection = route.get(0).getDirection();
                if(route.get(0).getRoad().getPedestrianCrossings() != null) {
                    pedestrianCrossing = route.get(0).getRoad().getPedestrianCrossings().get(0);
                    pedestrianCrossing.addPedestrian(this);
                }
                route.remove(0);
            }
        } else if(isPointReached(endingPoint)) {
            isEndReached = true;
        }
    }

    private boolean setIsWalkig() {
        if(pedestrianCrossing != null && pedestrianCrossing.getStreetLights() != null) {
            return isOnPedestrianCrossing() || pedestrianCrossing.getStreetLights().getLight() != StreetLights.RED;
        }
        return true;
    }

    private boolean isOnPedestrianCrossing() {
        return distanceToMiddleOfPedestrianCrossing() < pedestrianCrossing.getWidth() / 2;
    }

    private int distanceToMiddleOfPedestrianCrossing() {
        switch(currentDirection){
            case "N":
                return position.y - pedestrianCrossing.getPosition().y;
            case "E":
                return pedestrianCrossing.getPosition().x - position.x;
            case "S":
                return pedestrianCrossing.getPosition().y - position.y;
            case "W":
                return position.x - pedestrianCrossing.getPosition().x;
            default:
                return 0;
        }
    }

    private boolean isPointReached(Point point) {
        switch (currentDirection) {
            case "N":
                return position.y - point.y <= 0;
            case "E":
                return point.x - position.x <= 0;
            case "S":
                return point.y - position.y <= 0;
            case "W":
                return position.x - point.x <= 0;
            default:
                return false;
        }
    }

    void setImagePosition() {
        trafficParticipantImageView.setX(position.x - 2);
        trafficParticipantImageView.setY(position.y - 2);
    }

    private void walkThroughRoad() {

    }

    private void walkThroughPedestrianCrossing() {

    }

    private void waitBeforeCrossingTheRoad() {

    }
}
