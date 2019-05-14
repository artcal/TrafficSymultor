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
    private int lightsLength;
    private boolean areThereLights = false;

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

    public void setLightsLength(int lightsLength) {
        this.lightsLength = lightsLength;
        areThereLights = true;
    }

    private void calculateAverageSpeed() {
        averageSpeed = distance / fullTime * 50;
    }

    @Override
    public String toString() {
        return roadName + "," + direction + "," + fullTime + "," + waitingTime + ","
                + (waitingTimeOnCollision == 0 ? "null" : waitingTimeOnCollision)
                + "," + averageCarsQuantity + "," + averageSpeed + ","
                + (areThereLights ? lightsLength : "null");
    }
}
