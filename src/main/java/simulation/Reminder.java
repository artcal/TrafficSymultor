package simulation;

import java.util.Timer;
import java.util.TimerTask;

//class for changing street lights TODO

public class Reminder {
    private Timer timer;

    private Reminder(int time) {
        timer = new Timer();
        timer.schedule(new RemindTask(), time);
    }

    class RemindTask extends TimerTask {
        public void run() {
            timer.purge();
            timer.cancel();
            Controller.isNextCycleReady = true;
            if(Controller.isCycleFinished){
                Controller.runSimulation();
            }
        }
    }

    public static void main(String[] args) {
        new Reminder(Integer.parseInt(args[0]));
    }
}