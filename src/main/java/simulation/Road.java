package simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Road {

    private int maxSpeed;
    private List<PedestrianCrossing> pedestrianCrossings;
    private List<Line> lines;
    private Point start,end;
    private String type; //one-way, two-way
    private boolean isClosed;
    private Point exitSpawnPoint; // participants are spawning and deleting in this point

    Road(int maxSpeed, List<PedestrianCrossing> pedestrianCrossings, List<Line> lines,
                Point start, Point end, String type, boolean isClosed, Point exitSpawnPoint) {
        this.maxSpeed = maxSpeed;
        this.pedestrianCrossings = pedestrianCrossings;
        this.lines = lines;
        this.start = start;
        this.end = end;
        this.type = type;
        this.isClosed = isClosed;
        this.exitSpawnPoint = exitSpawnPoint;
    }

    Road(Point point){
        this.start = point;
    }

    Road(Point point, PedestrianCrossing pedestrianCrossing) {
        this.start = point;
        this.pedestrianCrossings = new ArrayList<>();
        this.pedestrianCrossings.add(pedestrianCrossing);
    }

    int getMaxSpeed() {
        return maxSpeed;
    }

    List<PedestrianCrossing> getPedestrianCrossings() {
        return pedestrianCrossings;
    }

    List<Line> getLines() {
        return lines;
    }

    Point getStart() {
        return start;
    }

    Point getEnd() {
        return end;
    }

    String getType() {
        return type;
    }

    boolean isClosed() {
        return isClosed;
    }

    void setClosed(boolean closed) {
        isClosed = closed;
    }

    Point getExitSpawnPoint() {
        return exitSpawnPoint;
    }
}
