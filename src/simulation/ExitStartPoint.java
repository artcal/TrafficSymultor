package simulation;

import java.awt.*;

public class ExitStartPoint {
    private Point position;
    private Road road;

    public ExitStartPoint(Point position, Road road) {
        this.position = position;
        this.road = road;
    }

    public Point getPosition() {
        return position;
    }

    public Road getRoad() {
        return road;
    }
}
