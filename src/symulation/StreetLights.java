package symulation;

public class StreetLights {

    private String[] LIGHTS_LIST = {"R","RY","G","Y","RC","N"}; //R - red
                                                                //RY - red & yellow
                                                                //G - green
                                                                //Y - yellow
                                                                //RC - red & conditioned
                                                                //N - none
    private int light;
    private boolean isCollisionFree;

    public StreetLights(int startingLight, boolean isCollisionFree) {
        this.light = startingLight;
        this.isCollisionFree = isCollisionFree;
    }

    public void changeLights() {
        light = light < 3 ? light++ : 0;
    }

    public void turnConditioned() {
        light = light == 0 ? 4 : 0;
    }

    public void turnLightsOff() {
        light = light == 5 ? 0 : 5;
    }

    public String getLight() {
        return LIGHTS_LIST[light];
    }

    public boolean isCollisionFree() {
        return isCollisionFree;
    }
}
