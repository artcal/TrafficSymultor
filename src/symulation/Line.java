package symulation;

import java.awt.*;
import java.util.List;

public class Line {

    private List<Roadsign> roadsigns;
    private Point start,end;
    private String trafficMovementDirection; // N,E,S,W
    private boolean isClosed;

    public Line(List<Roadsign> roadsigns, Point start, Point end, String trafficMovementDirection, boolean isClosed) {
        this.roadsigns = roadsigns;
        this.start = start;
        this.end = end;
        this.trafficMovementDirection = trafficMovementDirection;
        this.isClosed = isClosed;
    }

    public List<Roadsign> getRoadsigns() {
        return roadsigns;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public String getTrafficMovement() {
        return trafficMovementDirection;
    }

    public boolean isClosed() {
        return isClosed;
    }

    public void setClosed(boolean closed) {
        isClosed = closed;
    }

}

