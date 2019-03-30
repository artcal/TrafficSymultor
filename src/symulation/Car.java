package symulation;

import java.awt.*;

public class Car extends TrafficParticipant{

    private int acceleration;
    private int downturn;
    private int maxSpeed;
    private int speed = 0;
    private int weight;
    private int driverBehavior; // od -10% do 10%

    public Car(String name, Point startingPoint, Point endingPoint, boolean isSafe, int acceleration,
               int downturn,int maxSpeed, int weight) {
        super(name, startingPoint, endingPoint, isSafe);
        this.acceleration = acceleration;
        this.maxSpeed = maxSpeed;
        this.weight = weight;
        this.downturn = downturn;
    }

    private void accelerate(){ // 0-20 -> x3, 20-40 -> x2, 40+ -> x1
        if(speed < 20) speed += 3*acceleration;
        else if(speed < 40) speed += 2*acceleration;
        else if(speed < maxSpeed + maxSpeed*driverBehavior/100)
            speed += acceleration;
    }

    private void slowDown(){
        speed -= downturn;
        if(speed < 0) speed = 0;
    }

    private void changeDirection(){

    }

    public void move() {
        switch(line.getTrafficMovement()) {
            case "N":
                position = new Point(position.x, position.y + speed);
                break;
            case "E":
                position = new Point(position.x + speed, position.y);
                break;
            case "S":
                position = new Point(position.x, position.y - speed);
                break;
            case "W":
                position = new Point(position.x - speed, position.y);
                break;
        }
    }



    private void changeLine(){

    }

    private void turnBack(){

    }

    private void checkDistanceToCar(){

    }

    private void chcekDistanceToCrossRoad(){

    }
}
