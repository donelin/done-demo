package com.done;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Done Lin on 2018/4/1.
 * 测试分布式锁
 */
@Slf4j
@Component
public class CuratorLock {
    @Autowired
    private CuratorFramework client;
    //指定锁路径
    private final String lockPath = "/curator/interProcessMutex";
    private int count=0;
    public void testLock() {
        //创建锁，为可重入锁，即是获锁后，还可以再次获取，本例以此为例
        InterProcessMutex lock = new InterProcessMutex(client, lockPath);
        //创建锁，为不可重入锁，即是获锁后，不可以再次获取，这里不作例子，使用和重入锁类似
        //InterProcessSemaphoreMutex lock = new InterProcessSemaphoreMutex(client, lockPath);
        try{
            lock.acquire();
            count++;
            Thread.sleep(1500);
            count--;
            log.info("count={},thread={}",count,Thread.currentThread().getName());
        }catch (Exception e){
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }finally {
            try {
                if(lock!=null){
                    lock.release();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
