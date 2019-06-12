package simulation;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Timer;
import java.util.TimerTask;
import org.json.*;


// do terstowania co nam potrzeba
public class Test {

    public Test(){
        JSONObject jsonObject = new JSONObject(cos());
        System.out.println("\t\t\t\tnr\tft\twt\tas\tacq\tcn\tct");
        for (String key: jsonObject.keySet()){
            if(key.contains("road")) {
                JSONObject newJsonObject = jsonObject.getJSONObject(key);
                for (String anotherKey : newJsonObject.keySet()) {
                    if(anotherKey.length() == 1){
                        JSONObject jsonObject2 = newJsonObject.getJSONObject(anotherKey);
                        for (String thirdKey : jsonObject2.keySet()) {
                            if (!thirdKey.equals("hasLights")) {
                                JSONObject jsonObject3 = jsonObject2.getJSONObject(thirdKey);
                                if (jsonObject3.has("fullTime"))
                                    System.out.println(key + "\t" + anotherKey + "\t" + thirdKey + "\t" +
                                            jsonObject3.getInt("number") + "\t" +
                                            jsonObject3.getInt("fullTime") / jsonObject3.getInt("number") + "\t" +
                                            jsonObject3.getInt("waitingTime") / jsonObject3.getInt("number") + "\t" +
                                            jsonObject3.getInt("averageSpeed") / jsonObject3.getInt("number") + "\t" +
                                            jsonObject3.getInt("averageCarsQuantity") / jsonObject3.getInt("number") + "\t" +
                                            jsonObject3.getInt("collisionsNumber") + "\t" +
                                            jsonObject3.getInt("collisionTime")/ jsonObject3.getInt("number"));
                            }
                        }
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        new Test();
    }

    String cos(){
        try{
            BufferedReader bufferedReader = new BufferedReader(new FileReader(new File("safeModeStatistics.json")));
            return bufferedReader.readLine();
        }catch (Exception e){
            return "";
        }
    }
}
