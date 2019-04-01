package simulation;

import java.awt.*;
import java.util.List;

public class Road {

    private int maxSpeed;
    private List<PedestrianCrossing> pedestrianCrossings;
    private List<Line> lines;
    private Point start,end;
    private String type; //one-way, two-way
    private boolean isClosed;
    private Point exitSpawnPoint; // participants are spawning and deleting in this point

    public Road(int maxSpeed, List<PedestrianCrossing> pedestrianCrossings, List<Line> lines,
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

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public List<PedestrianCrossing> getPedestrianCrossings() {
        return pedestrianCrossings;
    }

    public List<Line> getLines() {
        return lines;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public String getType() {
        return type;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

    public Point getExitSpawnPoint() {
        return exitSpawnPoint;
    }
}
