package com.done.newFeature;

import lombok.extern.log4j.Log4j;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by Done Lin on 2018/1/9.
 */
public class ExcutorTest {

    public static void main(String[] args){
       // ExecutorService executor = Executors.newFixedThreadPool(25);
        //SyncTaskExecutor executor = new SyncTaskExecutor();
        //SimpleAsyncTaskExecutor executor = new SimpleAsyncTaskExecutor();
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(20);
        executor.initialize();
        for(int i = 0;i<26;i++){
            executor.execute(new SimpleTask());
        }

    }
}


@Log4j
class SimpleTask implements Runnable{

    @Override
    public void run() {
        try {
            log.info(Thread.currentThread()+" 线程开始运行-----------");
            Thread.sleep(5000);
            log.info(Thread.currentThread()+" 线程运行结束|||||||||||||");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
