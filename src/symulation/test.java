package symulation;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

// do terstowania co nam potrzeba
public class test {

    public static void main(String[] args){

        test tes = new test();
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("dupa","zapisana");

        //System.out.println(hashMap.toString());
        tes.test_1(hashMap);
        hashMap.put("A","dam");
        tes.test_12(hashMap);
        System.out.println(hashMap.keySet());



    }


    public static void aod(TrafficParticipant trafficParticipant){
        System.out.println(trafficParticipant.getClass());
    }

    void  test_1(Map<String,String> hs){
        hs.put("du","pa");

    }

    void  test_12(Map<String,String> hs){
        hs.put("dud","paa");

    }

}
