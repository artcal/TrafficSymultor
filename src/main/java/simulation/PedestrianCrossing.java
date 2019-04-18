package simulation;

import java.awt.*;

class PedestrianCrossing {

    private Point position;
    private StreetLights streetLights;
    private int width;

    PedestrianCrossing(Point position, StreetLights streetLights, int width) {
        this.position = position;
        this.streetLights = streetLights;
        this.width = width;
    }

    Point getPosition() {
        return position;
    }

    String getStreetLights() {
        return streetLights.getLight();
    }

    int getWidth() {
        return width;
    }
}
