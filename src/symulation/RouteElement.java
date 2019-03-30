package symulation;

public class RouteElement {
    private Road road;
    private String direction;

    public RouteElement(Road road, String direction) {
        this.road = road;
        this.direction = direction;
    }

    public Road getRoad() {
        return road;
    }

    public String getDirection() {
        return direction;
    }
}
