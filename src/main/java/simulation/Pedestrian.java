package simulation;

import java.awt.*;

class Pedestrian extends TrafficParticipant {

    private String currentDirection;
    private int distance;

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
        switch (currentDirection) {
            case "N":
                position = new Point(position.x, position.y - distance/50);
                break;
            case "E":
                position = new Point(position.x + distance/50, position.y);
                break;
            case "S":
                position = new Point(position.x, position.y + distance/50);
                break;
            case "W":
                position = new Point(position.x - distance/50, position.y);
                break;
        }
        distance %= 50;
        if(route.size() > 0) {
            if (isPointReached(route.get(0).getRoad().getStart())) {
                currentDirection = route.get(0).getDirection();
                route.remove(0);
            }
        } else if(isPointReached(endingPoint)) {
            isEndReached = true;
        }
    }

    private boolean isPointReached(Point point) {
        return position.equals(point);
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
