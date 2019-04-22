package simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

class StreetLights extends Thread {

    static int RED = 0;
    static int REDYELLOW = 1;
    static int GREEN = 2;
    static int YELLOW = 3;
    static int REDCONDITIONAL = 4;
    static int NONE = 5;

    private int light;
    private int redLightTime;
    private int greenLightTime;
    private boolean isNonCollision;

    StreetLights(int redLightTime, int greenLightTime, boolean isNonCollision) {
        this.light = 0;
        this.redLightTime = redLightTime;
        this.greenLightTime = greenLightTime;
        this.isNonCollision = isNonCollision;
    }

    public static void main(String[] args) {
        StreetLights streetLights = new StreetLights(5000, 7000, true);
        streetLights.start();
    }

    //FIXME IllegalMonitorStateException, synchronize thread
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            try {
                System.out.println(light);
                switch (light) {
                    case 0:
                        waitForChange(redLightTime);
                        wait(redLightTime);
                        break;
                    case 1:
                    case 3:
                        waitForChange(1000);
                        wait(1000);
                        break;
                    case 2:
                        waitForChange(greenLightTime);
                        wait(greenLightTime);
                        break;
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void waitForChange(int time) {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(time), event -> changeLights()));
        timeline.setCycleCount(1);
        timeline.play();
    }

    private void changeLights() {
        light = light < 3 ? light++ : 0;
    }

    private void turnConditioned() {
        light = light == 0 ? 4 : 0;
    }

    void turnLightsOnOff() {
        light = light == 5 ? 0 : 5;
    }

    int getLight() {
        return light;
    }

    boolean isNonCollision() {
        return isNonCollision;
    }
}
