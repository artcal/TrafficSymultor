package simulation;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StatisticsSaver implements Runnable{

    private Thread thread;
    private static List<StatisticsElement> statisticsElements = new ArrayList<>();
    private List<StatisticsElement> statisticsElementsToSave;

    StatisticsSaver(StatisticsElement statisticsElement) {
        statisticsElements.add(statisticsElement);
        if(statisticsElements.size() >= 100){
            thread = new Thread(this);
            start();
        }
    }

    private void start() {
        statisticsElementsToSave = new ArrayList<>(statisticsElements);
        statisticsElements.removeAll(statisticsElements);
        thread.start();
    }

    @Override
    public void run() {
        try {
            File file = new File("newStatistics.txt");
            BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true));
            for (StatisticsElement statisticsElement : statisticsElementsToSave) {
                bufferedWriter.append(statisticsElement.toString()).append("\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
