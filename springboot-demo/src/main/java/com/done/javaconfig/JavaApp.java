package com.done.javaconfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

/**
 * Created by Done Lin on 2018/3/3.
 */
@Slf4j
public class JavaApp {
    public static void main(String[] args) {
        // 通过Java配置来实例化Spring容器
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(SpringConfig.class);

        // 在Spring容器中获取Bean对象
        UserService userService = context.getBean(UserService.class);

        // 调用对象中的方法
        List<User> list = userService.queryUserList();
        for (User user : list) {
            log.info(user.getUsername() + ", " + user.getPassword() + ", " + user.getPassword());
        }

        // 销毁该容器
        context.destroy();
    }

}
