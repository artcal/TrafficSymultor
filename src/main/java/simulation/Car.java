package simulation;

import javax.swing.text.html.ImageView;
import java.awt.*;

public class Car extends TrafficParticipant implements RoadChange{

    private int acceleration;
    private int downturn;
    private int maxSpeed;
    private int speed = 0;
    private int driverBehavior; // od -10% do 10%
    private int distance = 0;
    private Point turningPoint;

    public Car(String name, Point startingPoint, Point endingPoint, boolean isSafe, int acceleration,
               int maxSpeed) throws Exception {
        super(name, startingPoint, endingPoint, isSafe,"car.png");
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.downturn = 2 * acceleration;
        generateRoute();
    }

    private void accelerate(){ // 0-20 -> x3, 20-40 -> x2, 40+ -> x1
        if(speed < 20) speed += 3*acceleration;
        else if(speed < 40) speed += 2*acceleration;
        else if(speed < maxSpeed + maxSpeed*driverBehavior/100)
            speed += acceleration;
    }

    private void slowDown(){
        if(speed != 0) {
            speed -= downturn;
            if (speed < 0) speed = 0;
        }
    }

    private void changeDirection(){


    }

    public void move() {
        distance += speed;
        switch(line.getTrafficMovement()) {
            case "N":
                position = new Point(position.x, position.y + distance/5);
                break;
            case "E":
                position = new Point(position.x + distance/5, position.y);
                break;
            case "S":
                position = new Point(position.x, position.y - distance/5);
                break;
            case "W":
                position = new Point(position.x - distance/5, position.y);
                break;
        }
        distance %= 5;
    }



    private void changeLine(){
        if(road.getType().equals("1way")){
            change();
        }

    }

    private void change() {
        boolean isVertical = this.position.x == line.getEnd().x;
        line = (road.getLines().get(0).equals(line)) ? road.getLines().get(1) : road.getLines().get(0);
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

    private int chcekDistanceToCrossRoad(){
        if(line.getNextCrossroad() != null) {
            if (line.getTrafficMovement().equals("N") || line.getTrafficMovement().equals("S")) {
                int start = position.y;
                return Math.abs(start - (line.getTrafficMovement().equals("N") ? road.getStart().y : road.getEnd().y));
            } else {
                int start = position.x;
                return Math.abs(start - (line.getTrafficMovement().equals("W") ? road.getStart().x : road.getEnd().x));
            }
        }
        return -1;
    }

    @Override
    public void onRoadChange() {
        if (road.getType().equals("1way")) {
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
                } else
                    if (!road.getLines().get(0).equals(line)) {
                        changeLine();
                }
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
        this.road = road;
        this.line = line;
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
}
