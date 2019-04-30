package simulation;

import javafx.scene.image.ImageView;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

class Line {

    private List<RoadSign> roadSigns;
    private List<Car> cars;
    private Point start,end;
    private String trafficMovementDirection; // N,E,S,W
    private boolean isClosed;
    private Crossroad nextCrossroad;
    private StreetLights streetLights;
    private ImageView imageView;

    Line(StreetLights streetLights, List<RoadSign> roadSigns, Point start, Point end, String trafficMovementDirection,
         boolean isClosed) {
        this.cars = new ArrayList<>();
        this.streetLights = streetLights;
        this.roadSigns = roadSigns;
        this.start = start;
        this.end = end;
        this.trafficMovementDirection = trafficMovementDirection;
        this.isClosed = isClosed;
    }

    Line(List<RoadSign> roadSigns, Point start, Point end, String trafficMovementDirection, boolean isClosed) {
        this.cars = new ArrayList<>();
        this.roadSigns = roadSigns;
        this.start = start;
        this.end = end;
        this.trafficMovementDirection = trafficMovementDirection;
        this.isClosed = isClosed;
    }

    void setImageView(){
        if(streetLights != null){
            if(imageView != null) imageView.setImage(streetLights.getImage());
            else {
                imageView = new ImageView(streetLights.getImage());
                int shift = getShiftValue();
                switch (trafficMovementDirection) {
                    case "N":
                        imageView.setRotate(0);
                        if (shift < 0) imageView.setX(start.x + shift - 4);
                        else imageView.setX(start.x + shift);
                        imageView.setY(start.y);
                        break;
                    case "E":
                        imageView.setRotate(90);
                        imageView.setX(end.x - 6);
                        if (shift < 0) imageView.setY(end.y + shift - 2);
                        else imageView.setY(end.y + shift - 2);
                        break;
                    case "S":
                        imageView.setRotate(180);
                        if (shift < 0) imageView.setX(end.x - shift);
                        else imageView.setX(end.x - shift - 4);
                        imageView.setY(end.y - 9);
                        break;
                    case "W":
                        imageView.setRotate(270);
                        imageView.setX(start.x + 2);
                        if (shift < 0) imageView.setY(start.y - shift - 2);
                        else imageView.setY(start.y - shift - 6);
                        break;
                }
            }
        }
    }

    private int getShiftValue() {
        if (trafficMovementDirection.equals("N") || trafficMovementDirection.equals("S")) {
            if (Math.abs(start.x - nextCrossroad.getPosition().x) == 7) return -5;
        }
        else
            if (Math.abs(start.y - nextCrossroad.getPosition().y) == 7) return -5;
        return 5;
    }

    Crossroad getNextCrossroad() {
        return nextCrossroad;
    }

    void setNextCrossroad(Crossroad nextCrossroad) {
        this.nextCrossroad = nextCrossroad;
        setImageView();
    }

    List<RoadSign> getRoadSigns() {
        return roadSigns;
    }

    Point getStart() {
        return start;
    }

    Point getEnd() {
        return end;
    }

    String getTrafficMovement() {
        return trafficMovementDirection;
    }

    boolean isClosed() {
        return isClosed;
    }

    void setClosed(boolean closed) {
        isClosed = closed;
    }

    StreetLights getStreetLights() {
        return streetLights;
    }

    ImageView getImageView() {
        return imageView;
    }

    List<Car> getCars() {
        return cars;
    }

    void addCar(Car car) {
        cars.add(car);
    }

    void removeCar(Car car){
        cars.remove(car);
    }

}

