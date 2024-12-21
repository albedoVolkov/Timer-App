package org.studying.desctoptimer;

import java.util.Timer;
import java.util.TimerTask;

public class Main {
    private static Double timeCount = 0.0000;
    public static void main(String[] args) {
        try {

            final Timer myTimer = new Timer();
            final TimerTask taskTimer = new TimerTask() {
                @Override
                public void run() {
                    System.out.println(String.format("%.3f", timeCount + 0.001));
                    timeCount = timeCount + 0.001;
                    if(timeCount >= 0.0100) {
                        myTimer.cancel();
                        myTimer.purge();
                    }
                }
            };

            myTimer.schedule(taskTimer, 0, 1);
        }catch (Exception e) {
            e.printStackTrace();
        }

    }

}
