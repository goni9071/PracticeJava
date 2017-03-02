package timer;

import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

public class TimerTest {
  public static void main(String[] args) throws InterruptedException {
    Timer timer = new Timer("Test");

    TimerTask timerTask = new TimerTask() {
      public void run() {
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
        }
        System.out.println(System.currentTimeMillis());
      }
    };

    timer.schedule(timerTask, 0, 1000 * 1); // 1 초

    TimeUnit.SECONDS.sleep(20);

    timer.cancel();
  }

}
