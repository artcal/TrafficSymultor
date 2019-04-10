package simulation;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.*;

// do terstowania co nam potrzeba
public class Test implements PropertyChangeListener{

    int temp;

    public Test(int temp) {
        this.temp = temp;
    }

    public static void main(String[] args) throws Exception {

        Random rand = new Random();
        //Main main = new Main();
        Main.generateExitSpawnPoints();
        Car car = new Car("mk_1",Main.startingPoints.get(0).getPosition(),Main.exitPoints.get(13).getPosition(),true,
                10,50);

//
//        for (RouteElement routeElement : car.getRoute().getRoute()) {
//            System.out.println(routeElement.getDirection());
//        }
        //Test test = new Test(1);
       // test.propertyChange(new PropertyChangeEvent());





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
}
