package simulation;

import java.util.Timer;
import java.util.TimerTask;

import static simulation.StreetLights.RED;

// do terstowania co nam potrzeba
public class Test implements Runnable{

    boolean isRunning;
    final Thread thread;
    private Timer timer;

    public Test() {

        thread = new Thread(this);
        isRunning = true;
    }

    public static void main(String[] args) throws Exception {
        Test test = new Test();
        test.start();

    }
    void start(){
        thread.start();
        synchronized (thread){
            try {
                System.out.println("Przed fredem");
                thread.wait(6000);
                System.out.println("Po fredzie");
                setRunningFalse();
            } catch (InterruptedException e) {
                thread.interrupt();
            }
        }
    }
    void setRunningFalse() {
        synchronized (thread) {
            isRunning = false;
            thread.notify();
        }
    }

    @Override
    public void run() {
        try {
            synchronized (thread) {
               while (!Thread.currentThread().isInterrupted() && isRunning) {
                   System.out.println("Przed wait()");
//                   cycle(5000);
                   thread.wait();
                   System.out.println("Po wait()");
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
    private void cycle(int time) {
        timer = new Timer();
        timer.schedule(new Test.RemindTask(), time);
    }

    class RemindTask extends TimerTask {
        public void run() {
            synchronized (thread) {
                //timer.purge();
                System.out.println("DUPA");
                thread.notify();
                //timer.cancel();
            }
        }
    }
}
