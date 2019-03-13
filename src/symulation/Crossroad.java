package symulation;

import java.awt.*;
import java.util.List;

public class Crossroad {

    private List<Road> roads;
    private List<StreetLights> streetLights;
    private String type; // crossroad, roundabout
    private Point position;


    public Crossroad(List<Road> roads, List<StreetLights> streetLights, String type, Point position) {
        this.roads = roads;
        this.streetLights = streetLights;
        this.type = type;
        this.position = position;
    }

    public List<Road> getRoads() {
        return roads;
    }

    public List<StreetLights> getStreetLights() {
        return streetLights;
    }

    public String getType() {
        return type;
    }

    public Point getPosition() {
        return position;
    }
}
