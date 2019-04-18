package simulation;

import java.util.Timer;
import java.util.TimerTask;

public class Cycle {
    private Timer timer;

    private Cycle(int time) {
        timer = new Timer();
        timer.schedule(new RemindTask(), time);
    }

    class RemindTask extends TimerTask {
        public void run() {
            timer.purge();
            timer.cancel();
        }
    }

    public static void main(String[] args) {
        new Cycle(Integer.parseInt(args[0]));
    }
}