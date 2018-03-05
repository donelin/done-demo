package com.done.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: done
 * @Date: 2018/3/1 17:34
 */
@Controller
@RequestMapping("/home")
@Slf4j
public class HomeAction {


    @RequestMapping("/say")
    @ResponseBody
    public String home(String name) {
        log.debug(" debug hello:{}",name);
        log.info(" info hello:{}",name);
        log.warn(" warn hello:{}",name);
        log.error(" error hello:{}",name);
        return "hello world";
    }
}
