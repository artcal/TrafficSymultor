package simulation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

// do terstowania co nam potrzeba
public class Test implements PropertyChangeListener, Runnable{

    int temp;

    public Test(int temp) {
        this.temp = temp;
    }

    public static void main(String[] args) throws Exception {
//        StreetLights streetLights = new StreetLights(5, 7, true);
//        streetLights.run();

    }


    public static void aod(TrafficParticipant trafficParticipant){
        System.out.println(trafficParticipant.getClass());
    }

    void  test_1(Map<String,String> hs){
        hs.put("dz","ieki");

    }

    void  test_12(Map<String,String> hs){
        hs.put("dud","ek");

    }

    @Override
    public void propertyChange(PropertyChangeEvent propertyChangeEvent) {

    }

    @Override
    public void run() {

    }
}
