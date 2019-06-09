package simulation;
import org.json.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import java.io.*;

public class JsonHandler {

    private JSONObject jsonObject;
    private File file;

    JsonHandler(){
        if(Controller.isSafeMode())
            file = new File("safeModeStatistics.json");
        else
            file = new File("unsafeModeStatistics.json");
        createJsonObject();
    }

    boolean saveToJson() {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(jsonObject.toString());
            fileWriter.flush();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createJsonObject() {
        if(file.exists()) {

            StringBuilder stringBuilder = new StringBuilder();
            try {
                BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            jsonObject = new JSONObject(stringBuilder.toString());

        }
        else{
            jsonObject = new JSONObject();
            for (Road road : Initialize.getRoads()) {
                if(!(road.getStart().x == 0 || road.getStart().y == 0 || road.getEnd().x == 1300 || road.getEnd().y == 800 || road.getName().equals("roadES"))) {
                    JSONObject jsonRoadObject = new JSONObject();
                    jsonRoadObject.put("length", road.getLines().get(0).getLineLenght());
                    for(Line line: road.getLines()){
                        if(!jsonRoadObject.has(line.getTrafficMovement())){
                            JSONObject jsonLineObject = new JSONObject();
                            jsonLineObject.put("hasLights", line.getStreetLights() != null);
                            jsonRoadObject.put(line.getTrafficMovement(), jsonLineObject);
                        }
                    }
                    jsonObject.put(road.getName(),jsonRoadObject);
                }

            }
        }
    }

    void addStatistics(String statistics) {
        List<String> list = new ArrayList<>(Arrays.asList(statistics.split(",")));
        if (jsonObject.has(list.get(0))) {
            JSONObject jsonLineObject = jsonObject.getJSONObject(list.get(0)).getJSONObject(list.get(1));
            if (!jsonLineObject.has(String.valueOf(Integer.parseInt(list.get(list.size() - 1)) / 1000))) {
                JSONObject jsonTimeObject = new JSONObject();
                jsonTimeObject.put("number", 0);
                jsonTimeObject.put("averageSpeed", 0);
                jsonTimeObject.put("averageCarsQuantity", 0);
                jsonTimeObject.put("fullTime", 0);
                jsonTimeObject.put("waitingTime", 0);
                jsonTimeObject.put("collisionTime", 0);
                jsonTimeObject.put("collisionsNumber", 0);

                jsonLineObject.put(String.valueOf(Integer.parseInt(list.get(list.size() - 1)) / 1000), jsonTimeObject);
            }
            JSONObject jsonTimeObject = jsonLineObject.getJSONObject(String.valueOf(Integer.parseInt(list.get(list.size() - 1)) / 1000));
            if (list.size() == 4) {
                jsonTimeObject.put("collisionsNumber", jsonTimeObject.getInt("collisionsNumber") + 1);
            } else {
                jsonTimeObject.put("number", jsonTimeObject.getInt("number") + 1);
                jsonTimeObject.put("averageSpeed", jsonTimeObject.getInt("averageSpeed") + Integer.parseInt(list.get(6)));
                jsonTimeObject.put("averageCarsQuantity", jsonTimeObject.getInt("averageCarsQuantity") + Integer.parseInt(list.get(5)));
                jsonTimeObject.put("fullTime", jsonTimeObject.getInt("fullTime") + Integer.parseInt(list.get(2)));
                jsonTimeObject.put("waitingTime", jsonTimeObject.getInt("waitingTime") + Integer.parseInt(list.get(3)));
                jsonTimeObject.put("collisionTime", jsonTimeObject.getInt("collisionTime") + Integer.parseInt(list.get(4)));
            }
        }
    }
}
