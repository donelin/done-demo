package com.done.springboot.extend;

import com.alibaba.fastjson.JSON;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Created by Done Lin on 2018/4/7.
 * 容器启动完后最后一步回掉
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println(" springboot 启动成功。。。" + JSON.toJSONString(args));
    }
}
