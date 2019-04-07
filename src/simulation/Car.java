package simulation;

import java.awt.*;

public class Car extends TrafficParticipant{

    private int acceleration;
    private int downturn;
    private int maxSpeed;
    private int speed = 0;
    private int driverBehavior; // od -10% do 10%
    private int distance = 0;

    public Car(String name, Point startingPoint, Point endingPoint, boolean isSafe, int acceleration,
               int maxSpeed) throws Exception {
        super(name, startingPoint, endingPoint, isSafe);
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

}
