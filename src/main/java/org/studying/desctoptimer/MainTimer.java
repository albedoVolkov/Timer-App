package org.studying.desctoptimer;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Timer;
import java.util.TimerTask;

public class MainTimer {

    private boolean IsGoing = false;

    private final PublishSubject<String> subject = PublishSubject.create();
    private final Observable<String> timeObserver = subject.hide();
    private final Timer myTimer = new Timer();

    private Double timeCount = 0.000;
    private Double fixedCounter = -1.0;
    private Double differenceCounter = 0.000;

    public MainTimer() {}

    public void run(){
        if(!IsGoing) {
            IsGoing = true;
            TimerTask taskTimer = new TimerTask() {
                public void run() {
                    if (IsGoing) {
                        if (fixedCounter == -1.0) {fixedCounter = this.scheduledExecutionTime() * 0.001;}
                        differenceCounter = this.scheduledExecutionTime() * 0.001 - fixedCounter;
                        updateTime(timeCount + differenceCounter);
                    }
                }
            };
            myTimer.schedule(taskTimer, 0, 1);
        }
    }

    public void stop(){
        IsGoing = false;
        timeCount = timeCount + differenceCounter;
        fixedCounter = -1.0;
        differenceCounter = 0.000;
    }

    public void reset(){
        timeCount = 0.000;
        fixedCounter = -1.0;
        differenceCounter = 0.000;
        updateTime(timeCount);
    }

    private void updateTime(Double time){
        subject.onNext(String.format("%.3f", time));
    }

    public Boolean isRunning(){
        return IsGoing;
    }

    public Observable<String> getTime(){
        return timeObserver;
    }
}