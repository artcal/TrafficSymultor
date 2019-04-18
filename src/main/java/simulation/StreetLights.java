package simulation;

class StreetLights {

    private String[] LIGHTS_LIST = {"R","RY","G","Y","RC","N"}; //R - red
                                                                //RY - red & yellow
                                                                //G - green
                                                                //Y - yellow
                                                                //RC - red & conditioned
                                                                //N - none
    private int light;
    private boolean isCollisionFree;

    StreetLights(int startingLight, boolean isCollisionFree) {
        this.light = startingLight;
        this.isCollisionFree = isCollisionFree;
    }

    void changeLights() {
        light = light < 3 ? light++ : 0;
    }

    void turnConditioned() {
        light = light == 0 ? 4 : 0;
    }

    void turnLightsOff() {
        light = light == 5 ? 0 : 5;
    }

    String getLight() {
        return LIGHTS_LIST[light];
    }

    boolean isCollisionFree() {
        return isCollisionFree;
    }
}
