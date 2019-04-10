package simulation;

import java.util.Timer;
import java.util.TimerTask;

//class for changing street lights TODO

public class Reminder {
    private Timer timer;

    private Reminder(int seconds) {
        timer = new Timer();
        timer.schedule(new RemindTask(), seconds*1000);
    }

    class RemindTask extends TimerTask {
        public void run() {
            System.out.format("Time's up!%n");
            timer.cancel();
        }
    }

    public static void main(String[] args) {
        new Reminder(5);
        System.out.format("Task scheduled.%n");
    }
}