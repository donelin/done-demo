package com.done.web.config;


import com.done.web.bean.Aeep;
import com.done.web.interceptor.CycleBeanPostprocessor;
import com.done.web.bean.Jeep;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: done
 * @Date: 2018/3/5 10:42
 * @Description: springmvc的配置
 */
@Slf4j
@Configuration
public class SpringMVCConfig extends WebMvcConfigurerAdapter {


    @Bean
    public Jeep jeep() {
        return new Jeep();
     }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new MyInterceptors()).addPathPatterns("/**");
    }

    @Bean
    public ServletRegistrationBean defultServlet(){
        //注解扫描上下文
        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        //base package
        applicationContext.scan("com.done");
        //通过构造函数指定dispatcherServlet的上下文
        DispatcherServlet defultServlet = new DispatcherServlet(applicationContext);

        //用ServletRegistrationBean包装servlet
        ServletRegistrationBean registrationBean= new ServletRegistrationBean(defultServlet);
        registrationBean.setLoadOnStartup(1);
        //指定urlmapping
        registrationBean.addUrlMappings("/web/*");
        registrationBean.addUrlMappings("/api/*");
        //指定name，如果不指定默认为dispatcherServlet
        registrationBean.setName("defultServlet");
        return registrationBean;
    }



    class MyInterceptors implements  HandlerInterceptor{
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            log.info("=========== MyInterceptors preHandle=============");
            return true;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        }
    }

}
