package simulation;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Crossroad {

    private List<Road> roads;
    private List<StreetLights> streetLights;
    private String type; // crossroad, roundabout
    private Point position;
    private Crossroad N,E,S,W; // next crossroad in 4 directions, null is end of map
    private Map<Line,Line[]> howToGo; // all possible routes to go through crossroad TODO
    private List<Line> entrances, exits;
    private int size;

    public Crossroad(List<Road> roads, List<StreetLights> streetLights, Point position, int size) {
        this.roads = roads;
        this.streetLights = streetLights;
        this.position = position;
        this.size = size;
        this.entrances = new ArrayList<>();
        this.exits = new ArrayList<>();
        this.howToGo = new HashMap<>();
    }

    public void addHowToGo(Line from, Line[] to){
        howToGo.put(from, to);
    }

    public Line[] getHowToGo(Line line) {
        return howToGo.get(line);
    }

    public List<Line> getEntrances() {
        return entrances;
    }

    public void setEntrances(Line[] entrances) {

        for(Line line: entrances){
            this.entrances.add(line);
        }
    }

    public List<Line> getExits() {
        return exits;
    }

    public void setExits(Line[] exits) {

        for(Line line: exits){
            this.exits.add(line);
        }
    }

    public void addNextCrossroads(Crossroad[] crossroads){
        this.N = crossroads[0];
        this.E = crossroads[1];
        this.S = crossroads[2];
        this.W = crossroads[3];
    }

    public Crossroad getN() {
        return N;
    }

    public Crossroad getE() {
        return E;
    }

    public Crossroad getS() {
        return S;
    }

    public Crossroad getW() {
        return W;
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
