package org.studying.desctoptimer;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.subjects.PublishSubject;
import java.util.Timer;
import java.util.TimerTask;

public class MainTimer {

    private boolean IsGoing = false;

    private final PublishSubject<String> subject = PublishSubject.create();
    private final Observable<String> timeObserver = subject.hide();
    private Double timeCount = 0.0000;

    private final Timer myTimer = new Timer();

    public MainTimer() {
        subject.onNext("0.000");
    }

    public void run(){
        if(!IsGoing) {
            IsGoing = true;
            TimerTask taskTimer = new TimerTask() {
                public void run() {
                    if (!IsGoing) {this.cancel();}
                    timeCount = timeCount + 0.001;
                    updateTime();
                }
            };
            myTimer.schedule(taskTimer, 0, 1);
        }
    }

    public void stop(){
        IsGoing = false;
    }

    public void reset(){
        timeCount = 0.0000;
        updateTime();
    }

    private void updateTime(){
        subject.onNext(String.format("%.3f", timeCount));
    }

    public Observable<String> getTime(){
        return timeObserver;
    }
}