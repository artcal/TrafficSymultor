package simulation;

public class StatisticsElement {

    private Road road;
    private int fullTime;
    private int waitingTime;
    private int waitingTimeOnCollision;
    private int averageCarsQuantity;
    private int averageSpeed;
    private int distance;

    public StatisticsElement(Road road, int fullTime, int waitingTime, int waitingTimeOnCollision, int averageCarsQuantity, int distance) {
        this.road = road;
        this.fullTime = fullTime;
        this.waitingTime = waitingTime;
        this.waitingTimeOnCollision = waitingTimeOnCollision;
        this.averageCarsQuantity = averageCarsQuantity;
        this.distance = distance;
    }

    public StatisticsElement(Road road, int fullTime, int waitingTime, int averageCarsQuantity, int distance) {
        this.road = road;
        this.fullTime = fullTime;
        this.waitingTime = waitingTime;
        this.averageCarsQuantity = averageCarsQuantity;
        this.distance = distance;
    }

    private void calculateAverageSpeed() {
        averageSpeed = distance / fullTime * 50;
    }


}
