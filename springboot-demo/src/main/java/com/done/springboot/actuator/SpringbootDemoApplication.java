package com.done.springboot.actuator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.InputStream;
import java.util.Properties;
/**
 * Created by Done Lin on 2018/4/16.
 *  ganglia
 *
 * 应用配置类端点
 *  /autoconfig
 *  /beans
 *  /mappings
 *  /configprops
 *  /env
 *
 * 度量指标类端点:内存使用情况、HTTP请求统计、外部资源指标等
 *  /metrics:该端点用来返回当前应用的各类重要度量指标，比如：内存信息、线程信息、垃圾回收信息等。
 *         heap.*：堆内存使用情况,threads.*：线程使用情况,
 *         gc.*：垃圾收集器的详细信息，包括垃圾回收次数:gc.ps_scavenge.count、垃圾回收消耗时间gc.ps_scavenge.time
 *         gauge.*:HTTP请求的性能指标之一，它主要用来反映一个绝对数值。
 *         比如上面示例中的gauge.response.home.index，它表示上一次hello请求的延迟时间为15毫秒。
 *         counter.*：HTTP请求的性能指标之一，它主要作为计数器来使用，记录了增加量和减少量。
 *           如上示例中counter.status.200.home.index:10，它代表了hello请求返回200状态的次数为10
 *  /health：该端点用来获取应用的各类健康指标信息
 *  /dump：该端点用来暴露程序运行中的线程信息
 *  /trace：该端点用来返回基本的HTTP跟踪信息
 *
 * 操作控制类:
 *  /shutdown:用来关闭应用的端点.post请求，默认不启用，
 *      需要在配置文件开启配置endpoints.shutdown.enabled=true
 *      在配置了上述属性之后，只需要访问该应用的/shutdown端点就能实现关闭该应用的远程操作
 *
 */
@SpringBootApplication
public class SpringbootDemoApplication {



    public static void  main(String[] args) throws Exception {
        InputStream inputStream = SpringbootDemoApplication.class.getClassLoader().getResourceAsStream("config/springboot.properties");
        Properties properties = new Properties();
        properties.load(inputStream);
        SpringApplication app = new SpringApplication(SpringbootDemoApplication.class);
        app.setDefaultProperties(properties);
        ConfigurableApplicationContext context = app.run(args);

    }
}
