package com.done;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ZookeeperDemoApplication {

	public static void main(String[] args) {
		SpringApplication app = new SpringApplication(ZookeeperDemoApplication.class);
		ConfigurableApplicationContext context = app.run(args);
		CuratorLock lock = context.getBean(CuratorLock.class);
		for(int i = 0; i < 10; i++){
			new Thread(new Runnable() {
				@Override
				public void run() {
					lock.testLock();
				}
			},"t" + i).start();
		}
		//context.close();
	}


}
