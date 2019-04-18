package simulation;

import java.awt.Point;
import java.util.*;
import java.util.List;

public class Crossroad {

    private List<Road> roads;
    private List<StreetLights> streetLights;
    private Point position;
    private Map<Line,Line[]> howToGo;
    private List<Line> entrances, exits;
    private int size;

    Crossroad(List<Road> roads, List<StreetLights> streetLights, Point position, int size) {
        this.roads = roads;
        this.streetLights = streetLights;
        this.position = position;
        this.size = size;
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.howToGo = new HashMap<>();
    }

    void addHowToGo(Line from, Line[] to){
        howToGo.put(from, to);
    }

    Line[] getHowToGo(Line line) {
        return howToGo.get(line);
    }

    void setEntrances(Line[] entrances) {
        this.entrances.addAll(Arrays.asList(entrances));
    }

    void setExits(Line[] exits) {
        this.exits.addAll(Arrays.asList(exits));
    }

    List<Road> getRoads() {
        return roads;
    }

    public List<StreetLights> getStreetLights() {
        return streetLights;
    }

    Point getPosition() {
        return position;
    }
}
