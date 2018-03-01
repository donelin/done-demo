package com.done.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: done
 * @Date: 2018/3/1 17:34
 * @Description: TODO
 */
@Controller
@RequestMapping("/home")
public class HomeAction {

    @RequestMapping("/say")
    @ResponseBody
    public String home() {
        return "hello world";
    }
}
