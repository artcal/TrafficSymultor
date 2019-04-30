package simulation;

import java.awt.*;
import java.util.Random;

class Car extends TrafficParticipant {

    private int acceleration;
    private int downturn;
    private int maxSpeed;
    private int speed = 0;
    private int driverBehavior; // od -10% do 10%
    private int distance = 0;
    private Point turningPoint;

    Car(String name, Point startingPoint, Point endingPoint, boolean isSafe, int acceleration) throws Exception {
        super(name, isSafe,"car.png");
        this.acceleration = acceleration;
        this.downturn = 2 * acceleration;
        Random random = new Random();
        this.driverBehavior = random.nextInt(20) - 10;
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        generateRoute();
        this.road = route.get(0).getRoad();
        this.line = getStartingLine(road);
        this.maxSpeed = this.road.getMaxSpeed() + (this.road.getMaxSpeed() * this.driverBehavior / 100);
        route.remove(0);
        this.position = setStartingPosition();
        onRoadChange();
        imageOrientation();
        setImagePosition();
    }
//FIXME cars may go 3 point!!!!(ex. 77 speed)
    private Line getStartingLine(Road road) throws Exception {
        for (Line line : road.getLines()) {
            if (line.getStart().equals(startingPoint) || line.getEnd().equals(startingPoint))
                return line;
            else if (line.getTrafficMovement().equals(route.get(0).getDirection()))
                return line;
        }
        throw new Exception("Cannot generate starting road");
    }

    private Point setStartingPosition() {
        if(startingPoint.equals(line.getEnd()) || startingPoint.equals(line.getStart()))
            return startingPoint;
        else if(line.getTrafficMovement().equals("N") || line.getTrafficMovement().equals("S"))
            return new Point(line.getEnd().x, startingPoint.y);
        else
            return new Point(startingPoint.x, line.getEnd().y);
    }

    void setImagePosition() {
        if((int)trafficParticipantImageView.getRotate() == 0 || (int)trafficParticipantImageView.getRotate() == 180){
            trafficParticipantImageView.setX(position.x - 5);
            trafficParticipantImageView.setY(position.y - 3);
        }else{
            trafficParticipantImageView.setX(position.x - 3);
            trafficParticipantImageView.setY(position.y - 5);
        }
    }

    void correctSpeed() {
        try {
            if(speed < maxSpeed && !isTooCloseToCar() && !isStoppingOnRedLight())
                accelerate();
            else
                slowDown();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isStoppingOnRedLight() {
        if(line.getStreetLights() != null) {
            if (line.getStreetLights().getLight() == StreetLights.RED || line.getStreetLights().getLight() == StreetLights.YELLOW) {
                return (checkDistanceToCrossRoad() < 35 && checkDistanceToCrossRoad() > 22)
                        || (checkDistanceToCrossRoad() < 10 && checkDistanceToCrossRoad() > 0);
            }
        }
        return false;
    }

    private boolean isTooCloseToCar() throws Exception {
        switch (line.getTrafficMovement()){
            case "N":
            case "S":
                return isCarInRange(true);
            case "E":
            case "W":
                return isCarInRange(false);
        }
        throw new Exception("Wrong direction");
    }

    private boolean isCarInRange(boolean isVertical) throws Exception {
        for (Car car : line.getCars()) {
            if(!car.equals(this)) {
                if (isCarInFront(car))
                    if (isVertical) {
                        if (isInRange(car.position.y, isVertical))
                            return true;
                    } else if (isInRange(car.position.x, isVertical))
                        return true;
            }
        }
        return false;
    }

    private boolean isCarInFront(Car car) throws Exception {
        switch (line.getTrafficMovement()){
            case "N":
                return car.position.y < this.position.y;
            case "E":
                return car.position.x > this.position.x;
            case "S":
                return car.position.y > this.position.y;
            case "W":
                return car.position.x < this.position.x;
        }
        throw new Exception("Wrong direction");
    }

    private void accelerate(){ // 0-20 -> x3, 20-40 -> x2, 40+ -> x1
        if(speed < 20) speed += 3*acceleration;
        else if(speed < 40) speed += 2*acceleration;
        else speed += acceleration;
    }

    private void slowDown(){
        if(speed != 0) {
            speed -= downturn;
            if (speed < 0) speed = 0;
        }
    }

    void move() {
        distance += speed;
        switch(line.getTrafficMovement()) {
            case "N":
                position = new Point(position.x, position.y - distance/50);
                break;
            case "E":
                position = new Point(position.x + distance/50, position.y);
                break;
            case "S":
                position = new Point(position.x, position.y + distance/50);
                break;
            case "W":
                position = new Point(position.x - distance/50, position.y);
                break;
        }
        distance %= 50;
        if(route.size() > 0) {
            if (isPointReached(turningPoint)) {
                Road road = route.get(0).getRoad();
                route.remove(0);
                setRoadAndLine(road, nextLine);
                imageOrientation();
                correctPositionPoint();
            }
        } else if(isPointReached(endingPoint)) {
            isEndReached = true;
        }
    }

    private void correctPositionPoint() {
        if(line.getTrafficMovement().equals("N") || line.getTrafficMovement().equals("S")) {
            if(position.x != line.getEnd().x)
                position.x = line.getEnd().x;
        } else
            if(position.y != line.getEnd().y)
                position.y = line.getEnd().y;
    }

    private boolean isPointReached(Point point) {
        if(position.equals(point))
            return true;
        else
            switch (line.getTrafficMovement()){
                case "N":
                    return position.y == point.y - 1;
                case "E":
                    return position.x == point.x + 1;
                case "S":
                    return position.y == point.y + 1;
                case "W":
                    return position.x == point.x - 1;
                default:
                    return false;
            }
    }

    private void changeLine(){
        if(road.getType().equals("1way")){
            change();
        }
    }

    private void change() {
        boolean isVertical = this.position.x == line.getEnd().x;
        line.removeCar(this);
        line = (road.getLines().get(0).equals(line)) ? road.getLines().get(1) : road.getLines().get(0);
        line.addCar(this);
        if(isVertical){
            position.x = line.getEnd().x;
        }else{
            position.y = line.getEnd().y;
        }
    }

    private void turnBack(){
        if(road.getType().equals("2way")){
            change();
        }
    }

    private void checkDistanceToCar(){

    }

    private int checkDistanceToCrossRoad(){
        if(line.getNextCrossroad() != null) {
            if (line.getTrafficMovement().equals("N") || line.getTrafficMovement().equals("S")) {
                int start = position.y;
                int ret = start - (line.getTrafficMovement().equals("N") ? road.getStart().y : road.getEnd().y);
                if(line.getTrafficMovement().equals("N"))
                    return ret;
                else
                    return -ret;
            } else {
                int start = position.x;
                int ret = start - (line.getTrafficMovement().equals("W") ? road.getStart().x : road.getEnd().x);
                if(line.getTrafficMovement().equals("W"))
                    return ret;
                else
                    return -ret;
            }
        }
        return -1000;
    }

    private void onRoadChange() {
        maxSpeed = road.getMaxSpeed() + (road.getMaxSpeed() * driverBehavior / 100);
        if (route.size() > 0)
            if (!isLineOk()) {
                changeLine();
                isLineOk();
            }
        else {
            int correctLineId = getCorrectLineId();
            if (correctLineId >= 0) {
                if (!road.getLines().get(correctLineId).equals(line))
                    changeLine();
            } else if (!road.getLines().get(0).equals(line)) {
                changeLine();
            }
        }
    }

    private int getCorrectLineId() {
        for (Line line :road.getLines()) {
            if(line.getEnd().equals(endingPoint) || line.getStart().equals(endingPoint)) {
                return road.getLines().indexOf(line);
            }
        }
        return -1;
    }

    private boolean isLineOk() {
        for (Line line : line.getNextCrossroad().getHowToGo(line)) {
            if(route.get(0).getRoad().getLines().get(0).equals(line)) {
                setNextLine(line);
                return true;
            }
            if(route.get(0).getRoad().getLines().get(1).equals(line)) {
                setNextLine(line);
                return true;
            }
        }
        return false;
    }

    private void setRoadAndLine(Road road, Line line){
        if(line != null){
            this.line.removeCar(this);
        }
        this.road = road;
        this.line = line;
        assert this.line != null;
        this.line.addCar(this);
        onRoadChange();
    }

    private void setNextLine(Line nextLine) {
        this.nextLine = nextLine;
        setTurningPoint();
    }

    private void setTurningPoint() {
        if(line.getTrafficMovement().equals("N") || line.getTrafficMovement().equals("S")) {
            if(route.get(0).getDirection().equals("W") || route.get(0).getDirection().equals("E")){
                turningPoint = new Point(line.getEnd().x,nextLine.getEnd().y);
            }else{
                turningPoint = new Point(line.getEnd().x, line.getNextCrossroad().getPosition().y);
            }
        }else{
            if(nextLine.getTrafficMovement().equals("N") || nextLine.getTrafficMovement().equals("S")){
                turningPoint = new Point(nextLine.getEnd().x, line.getEnd().y);
            }else{
                turningPoint = new Point(line.getNextCrossroad().getPosition().x, line.getEnd().y);
            }
        }
    }

    boolean canEnterRoad() {
        if(line.getCars().size() != 0) {
            boolean canEnterRoad = true;
            for(Car car : line.getCars()) {
                if(canEnterRoad) {
                    if (line.getTrafficMovement().equals("N") || line.getTrafficMovement().equals("S")) {
                        if(isInRange(car.getPosition().y, true))
                            canEnterRoad = false;
                    } else {
                        if(isInRange(car.position.x, false))
                            canEnterRoad = false;
                    }
                }
            }
            if(canEnterRoad) {
                line.addCar(this);
            }
            return canEnterRoad;
        }
        line.addCar(this);
        return true;
    }

    private boolean isInRange(int position, boolean isVertical) {
        if(isVertical) {
            return Math.abs(this.position.y - position) < 20;
        } else {
            return Math.abs(this.position.x - position) < 20;
        }
    }
}
