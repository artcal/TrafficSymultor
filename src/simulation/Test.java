package simulation;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

// do terstowania co nam potrzeba
public class Test {

    public static void main(String[] args){

        Random rand = new Random();
        //Main main = new Main();
        Main.generateExitSpawnPoints();
        Car car = new Car("mk_1",Main.startingPoints.get(12).getPosition(),Main.exitPoints.get(13).getPosition(),true,
                10,5,50,10);

        for (RouteElement routeElement : car.getRoute().getRoute()) {
            System.out.println(routeElement.getDirection());
        }

//        Car car = new Car("mk_1",Main.startingPoints.get(rand.nextInt(16)).getPosition(),Main.exitPoints.get(rand.nextInt(16)).getPosition(),true,
//                10,5,50,10);

//        for (ExitStartPoint exitStartPoint :Main.exitPoints) {
//            System.out.println(exitStartPoint.getPosition().x+"\t" + exitStartPoint.getPosition().y);
//
//        }
//        System.out.println(Main.startingPoints);
//        System.out.println("--------");
//        System.out.println(Main.exitPoints);




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

}
