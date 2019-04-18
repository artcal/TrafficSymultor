package simulation;

import java.awt.*;

class ExitStartPlace {
    private Point position;
    private Road road;

    ExitStartPlace(Point position, Road road) {
        this.position = position;
        this.road = road;
    }

    Point getPosition() {
        return position;
    }

    Road getRoad() {
        return road;
    }
}
