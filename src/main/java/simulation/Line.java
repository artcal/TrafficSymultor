package simulation;

import java.awt.*;
import java.util.List;

class Line {

    private List<RoadSign> roadSigns;
    private Point start,end;
    private String trafficMovementDirection; // N,E,S,W
    private boolean isClosed;
    private Crossroad nextCrossroad;


    Line(List<RoadSign> roadSigns, Point start, Point end, String trafficMovementDirection, boolean isClosed) {
        this.roadSigns = roadSigns;
        this.start = start;
        this.end = end;
        this.trafficMovementDirection = trafficMovementDirection;
        this.isClosed = isClosed;
    }

    Crossroad getNextCrossroad() {
        return nextCrossroad;
    }

    void setNextCrossroad(Crossroad nextCrossroad) {
        this.nextCrossroad = nextCrossroad;
    }

    List<RoadSign> getRoadSigns() {
        return roadSigns;
    }

    Point getStart() {
        return start;
    }

    Point getEnd() {
        return end;
    }

    String getTrafficMovement() {
        return trafficMovementDirection;
    }

    boolean isClosed() {
        return isClosed;
    }

    void setClosed(boolean closed) {
        isClosed = closed;
    }
}

