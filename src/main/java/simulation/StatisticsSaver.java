package simulation;

import java.util.ArrayList;
import java.util.List;

public class StatisticsSaver implements Runnable{

    private Thread thread;
    private static List<StatisticsElement> statisticsElements = new ArrayList<>();
    private List<StatisticsElement> statisticsElementsToSave;

    public StatisticsSaver(StatisticsElement statisticsElement) {
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

    }
}
