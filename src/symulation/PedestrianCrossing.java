package symulation;

import java.awt.*;

public class PedestrianCrossing {

    private Point position;
    private StreetLights streetLights;

    public PedestrianCrossing(Point position, StreetLights streetLights) {
        this.position = position;
        this.streetLights = streetLights;
    }

    public Point getPosition() {
        return position;
    }

    public String getStreetLights() {
        return streetLights.getLight();
    }
}
