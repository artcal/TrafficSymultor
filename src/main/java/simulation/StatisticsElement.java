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
    private boolean isNewAccident = false;


    StatisticsElement(String roadName, String direction, int fullTime, int waitingTime, int waitingTimeOnCollision,
                      int averageCarsQuantity, int distance) throws Exception {
        this.roadName = roadName;
        this.direction = direction;
        this.fullTime = fullTime;
        this.waitingTime = waitingTime;
        this.waitingTimeOnCollision = waitingTimeOnCollision;
        this.averageCarsQuantity = averageCarsQuantity;
        this.distance = distance;
        calculateAverageSpeed();
    }

    public StatisticsElement(String roadName, String direction) {
        this.roadName = roadName;
        this.direction = direction;
        isNewAccident = true;
    }

    private void calculateAverageSpeed() throws Exception {
        if(fullTime != 0)
            averageSpeed = distance / fullTime * 50;
        else
            throw new Exception("fullTime = 0 !");
    }

    @Override
    public String toString() {
        if(!isNewAccident)
            return roadName + "," + direction + "," + fullTime + "," + waitingTime + ","
                + waitingTimeOnCollision + "," + averageCarsQuantity + ","
                + averageSpeed + "," + Initialize.getLightsLength();
        else
            return roadName + "," + direction + "," + "true";
    }
}
