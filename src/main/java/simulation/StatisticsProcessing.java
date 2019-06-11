package simulation;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StatisticsProcessing {
    private List<String> statisticsList;
    private File file;
    private JsonHandler jsonHandler;

    StatisticsProcessing(){
        jsonHandler = new JsonHandler();
        if(Controller.isSafeMode())
            file = new File("newStatisticsSafe.txt");
        else
            file = new File("newStatisticsNotSafe.txt");

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
            String line;
            statisticsList = new ArrayList<>();
            while((line = bufferedReader.readLine()) != null){
                statisticsList.add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void updateStatisticsJson(){
        for(String statistcs : statisticsList)
            jsonHandler.addStatistics(statistcs);
        if(jsonHandler.saveToJson())
            clearStatisticsFile();
    }

    private void clearStatisticsFile(){
        try(FileWriter fileWriter = new FileWriter(file)){
            fileWriter.write("");
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
