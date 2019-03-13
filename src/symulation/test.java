package symulation;

import java.awt.*;

// do terstowania co nam potrzeba
public class test {

    public static void main(String[] args){
        Pedestrian pedestrian = new Pedestrian("s", new Point(0,0),new Point(0,0),true);
        aod(pedestrian);
    }


    public static void aod(TrafficParticipant trafficParticipant){
        System.out.println(trafficParticipant.getClass());
    }
}
