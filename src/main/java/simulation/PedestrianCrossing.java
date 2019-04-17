package simulation;

import java.awt.*;

public class PedestrianCrossing {

    private Point position;
    private StreetLights streetLights;
    private int width;

    public PedestrianCrossing(Point position, StreetLights streetLights, int width) {
        this.position = position;
        this.streetLights = streetLights;
        this.width = width;
    }

    public Point getPosition() {
        return position;
    }

    public String getStreetLights() {
        return streetLights.getLight();
    }

    public int getWidth() {
        return width;
    }
}
