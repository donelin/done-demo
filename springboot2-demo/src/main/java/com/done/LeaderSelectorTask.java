package com.done;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by Done Lin on 2018/4/1.
 * 测试选举主节点做分布式定时器
 */
@Slf4j
//@Component
public class LeaderSelectorTask extends LeaderSelectorListenerAdapter implements ApplicationListener<ContextRefreshedEvent>,InitializingBean, DisposableBean {

    @Autowired
    private CuratorFramework client;
    private final String path = "/leaderSelector/task_leader_selector";
    private LeaderSelector leaderSelector;
    //防止启动容器刷新2次（执行多次onApplicationEvent方法）
    private boolean started = false;
    //对象锁
    private Object lock = new Object();
    //启动一个线程去跑这个任务
    private ExecutorService executorService = Executors.newCachedThreadPool();

    /**
     * client成为leader后，会调用此方法
     */
    @Override
    public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
        log.info("已成为Leader，开始执行定时任务......");
        while (true) {
            try {
                this.task();
            } catch (Exception ex) {
                log.error("教学计划发布信息刷新出现异常", ex);
            }
            Thread.sleep(20 * 1000); //20秒执行一次
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        leaderSelector = new LeaderSelector(client,path,this);
        //autoRequeue()方法的调用确保此实例在释放领导权后还可能获得领导权
        leaderSelector.autoRequeue();
    }

    @Override
    public void destroy() throws Exception {
        leaderSelector.close();
        executorService.shutdown();
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        synchronized (lock){
            if(!started){
                leaderSelector.start();
                started=true;
            }
        }
    }

    public void task(){
        log.info("........正在执行任务...........");
    }
}
