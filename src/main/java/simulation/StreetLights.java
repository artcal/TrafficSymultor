package simulation;


import javafx.scene.image.Image;

import java.net.URI;
import java.net.URL;

class StreetLights implements Runnable {

    static final int RED = 0;
    static final int RED_YELLOW = 1;
    static final int GREEN = 2;
    static final int YELLOW = 3;
    static final int REDCONDITIONAL = 4;
    static final int NONE = 5;

    private int light;
    private int greenLightTime, redLightTime, delay, startingLight;
    private boolean isNonCollision, isRunning, isPedestrianLight;
    private Thread thread;
    private Image image;


    StreetLights(int redLightTime, int greenLightTime, boolean isNonCollision, int startingLight, int delay ) throws Exception {
        isPedestrianLight = false;
        this.light = startingLight;
        this.redLightTime = redLightTime;
        this.greenLightTime = greenLightTime;
        this.isNonCollision = isNonCollision;
        this.delay = delay;
        this.startingLight = startingLight;
        setImage();
    }

    StreetLights(int redLightTime, int greenLightTime, int startingLight, int delay){
        isPedestrianLight = true;
        this.light = startingLight;
        this.redLightTime = redLightTime;
        this.greenLightTime = greenLightTime;
        this.delay = delay;
    }

    synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    @Override
    public void run() {
        try {
            synchronized (thread) {
                if(delay != 0)
                    thread.wait(delay);
                while (!Thread.currentThread().isInterrupted() && isRunning) {
                    if (isPedestrianLight) {
                        switch (light){
                            case RED:
                                changeLights();
                                thread.wait(greenLightTime);
                                break;
                            case GREEN:
                                changeLights();
                                thread.wait(redLightTime);
                                break;
                        }
                    } else {
                        switch (light) {
                            case RED:
                            case GREEN:
                                changeLights();
                                thread.wait(1000);
                                break;
                            case RED_YELLOW:
                                changeLights();
                                thread.wait(greenLightTime);
                                break;
                            case YELLOW:
                                changeLights();
                                thread.wait(redLightTime);
                                break;
                        }
                    }
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void changeLights() throws Exception {
        if(isPedestrianLight)
            light = light == 0 ? GREEN : RED;
        else
            light = light < 3 ? light + 1 : 0;
        setImage();
        Initialize.changeStreetLightsImageView(this);
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

    void setRunningFalse() throws Exception {
        if(thread != null) {
            synchronized (thread) {
                isRunning = false;
                light = startingLight;
                thread.notify();
                setImage();
            }
        }
    }
    Image getImage() {
        return image;
    }

    void setImage() throws Exception {
        String imageString = "";
        if(isPedestrianLight){
            if(light == RED)
                imageString = "PedestrianLightRED.png";
            else
                imageString = "PedestrianLightGREEN.png";
        }else
            switch(light) {
                case RED:
                    imageString = "StreetLightsRED.png";
                    break;
                case RED_YELLOW:
                    imageString = "StreetLightsREDYELLOW.png";
                    break;
                case GREEN:
                    imageString = "StreetLightsGREEN.png";
                    break;
                case YELLOW:
                    imageString = "StreetLightsYELLOW.png";
                    break;
            }
        URL url = getClass().getClassLoader().getResource(imageString);
        if(url != null) {
            URI uri = url.toURI();
            image = new Image(uri.toString());
        }
    }

    public boolean isPedestrianLight() {
        return isPedestrianLight;
    }
}
