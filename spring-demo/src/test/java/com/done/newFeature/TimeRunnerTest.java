package com.done.newFeature;

import lombok.extern.log4j.Log4j;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Done Lin on 2018/1/8.
 */
@Log4j
public class TimeRunnerTest {
    public static void main(String[] args){
        Timer timer = new Timer();
        for(int i=0;i<10;i++){
            SimpleTimerTask task = new SimpleTimerTask();
            timer.schedule(task,(i*1000));
        }
    }
}


class SimpleTimerTask extends TimerTask {
    @Override
    public void run() {
        SystemLog.outputstream();
    }
}

@Log4j
class SystemLog{
  public static void  outputstream()  {
      try {
          log.info("excute task.");
          Thread.sleep(5000);
          log.info(Thread.currentThread().getName());
          System.out.println("执行我一次");
      } catch (Exception e) {
          e.printStackTrace();
      }
  }
}
