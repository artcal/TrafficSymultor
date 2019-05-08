package simulation;

import java.awt.*;
import java.awt.event.PaintEvent;
import java.util.ArrayList;
import java.util.List;

class PedestrianCrossing {

    private Point position;
    private StreetLights streetLights;
    private int width;
    private List<Pedestrian> pedestrians;

    PedestrianCrossing(Point position, StreetLights streetLights, int width) {
        pedestrians = new ArrayList<>();
        this.position = position;
        this.streetLights = streetLights;
        this.width = width;
    }

    void addPedestrian(Pedestrian pedestrian){
        pedestrians.add(pedestrian);
    }

    void removePedestrian(Pedestrian pedestrian){
        pedestrians.remove(pedestrian);
    }

    Point getPosition() {
        return position;
    }

    int getStreetLights() {
        return streetLights.getLight();
    }

    int getWidth() {
        return width;
    }

    public List<Pedestrian> getPedestrians() {
        return pedestrians;
    }
}
