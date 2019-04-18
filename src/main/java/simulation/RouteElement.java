package simulation;

class RouteElement {
    private Road road;
    private String direction;

    RouteElement(Road road, String direction) {
        this.road = road;
        this.direction = direction;
    }

    Road getRoad() {
        return road;
    }

    String getDirection() {
        return direction;
    }
}
