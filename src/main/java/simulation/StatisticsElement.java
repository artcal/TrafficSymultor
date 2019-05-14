package simulation;

class StatisticsElement {

    private String roadName;
    private String direction;
    private int fullTime;
    private int waitingTime;
    private int waitingTimeOnCollision;
    private int averageCarsQuantity;
    private int averageSpeed;
    private int distance;
    private int redLightTime;
    private int greenLightTime;

    StatisticsElement(String roadName, String direction, int fullTime, int waitingTime, int waitingTimeOnCollision,
                      int averageCarsQuantity, int distance) {
        this.roadName = roadName;
        this.direction = direction;
        this.fullTime = fullTime;
        this.waitingTime = waitingTime;
        this.waitingTimeOnCollision = waitingTimeOnCollision;
        this.averageCarsQuantity = averageCarsQuantity;
        this.distance = distance;
        calculateAverageSpeed();
    }

    StatisticsElement(String roadName, String  direction, int fullTime, int waitingTime, int averageCarsQuantity,
                      int distance) {
        this.roadName = roadName;
        this.direction = direction;
        this.fullTime = fullTime;
        this.waitingTime = waitingTime;
        this.averageCarsQuantity = averageCarsQuantity;
        this.distance = distance;
        calculateAverageSpeed();
    }

    void setStreetLightsTime(int redLightTime, int greenLightTime){
        this.redLightTime = redLightTime;
        this.greenLightTime = greenLightTime;
    }

    private void calculateAverageSpeed() {
        averageSpeed = distance / fullTime * 50;
    }

    @Override
    public String toString() {
        return roadName + "," + direction + "," + fullTime + "," + waitingTime + ","
                + (waitingTimeOnCollision == 0 ? "null" : waitingTimeOnCollision)
                + "," + averageCarsQuantity + "," + averageSpeed + "," + distance + ","
                + (redLightTime == 0 && greenLightTime == 0 ? "null,null" : redLightTime + "," + greenLightTime);
    }
}
