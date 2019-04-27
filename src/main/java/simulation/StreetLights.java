package simulation;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.util.Timer;
import java.util.TimerTask;

class StreetLights implements Runnable {

    static int RED = 0;
    static int REDYELLOW = 1;
    static int GREEN = 2;
    static int YELLOW = 3;
    static int REDCONDITIONAL = 4;
    static int NONE = 5;

    private int light;
    private int redLightTime;
    private int greenLightTime;
    private boolean isNonCollision, isRunning;
    private Thread thread;

    StreetLights(int redLightTime, int greenLightTime, boolean isNonCollision, boolean isHorizontal) {
        this.light = isHorizontal ? 0 : 1;
        this.redLightTime = redLightTime;
        this.greenLightTime = greenLightTime;
        this.isNonCollision = isNonCollision;
    }

    public static void main(String[] args) {
        StreetLights streetLights = new StreetLights(8000, 4000, true, true);
        StreetLights streetLights1 = new StreetLights(8000, 4000, true, false);
        streetLights.start();
        streetLights1.start();
    }

    synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    @Override
    public void run() {
        try {
            synchronized (this) {
                if(light == REDYELLOW)
                    wait(1000);
                while (!Thread.currentThread().isInterrupted() && isRunning) {
                    System.out.println(light);
                    switch (light) {
                        case 0:
                            changeLights();
                            wait(redLightTime);
                            break;
                        case 1:
                        case 3:
                            changeLights();
                            wait(1000);
                            break;
                        case 2:
                            changeLights();
                            wait(greenLightTime);
                            break;
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void changeLights() {
        light = light < 3 ? light + 1 : 0;
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

    public boolean isRunning() {
        return isRunning;
    }

    void setRunningFalse() {
        isRunning = false;
    }
}
