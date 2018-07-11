package com.done.spring4x;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Created by Done Lin on 2018/4/2.
 */
public class App {

    public static void  main(String[] args){
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(MyConfig.class);
        System.out.println(context.getBean(MyBean.class));
        System.out.println(context.getBean(Jeep.class));
        System.out.println(context.getBean("jeep"));
        System.out.println(context.getBean(JeepFactoryBean.class));
        System.out.println(context.getBean("&jeep"));
        System.out.println(context.getBean("myDog"));
        System.out.println(((MyCat)context.getBean("myCat")).getContext());
        context.getBeansOfType(MyPig.class).values().forEach(System.out::println);
        context.close();
    }
}
