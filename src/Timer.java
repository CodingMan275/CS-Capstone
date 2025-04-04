import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.*;
public class Timer implements Runnable {

    public int threadNum ;
    public int timerAmount ;
    public JLabel timerDisplay;

    public Timer(int i, JLabel timerDisplay) {
        this.threadNum = i ;
        this.timerDisplay = timerDisplay;
    }

    public void run() {
        timerAmount = 0 ;
        while (true) {
            //System.out.println("Thread: " + threadNum + " Time: " + i) ;
            try {
                Thread.sleep(1000) ;
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            timerAmount++ ;
            int seconds = timerAmount % 60 ;
            int minutes = (timerAmount % 3600) / 60 ;
            int hours = timerAmount / 3600 ;
            LocalTime time = LocalTime.of(hours ,minutes, seconds);
            String timeString = time.format(DateTimeFormatter.ofPattern("mm:ss"));
            timerDisplay.setText("Timer: " + timeString);
        }
    }
}