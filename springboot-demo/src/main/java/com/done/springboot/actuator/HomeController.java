package com.done.springboot.actuator;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Done Lin on 2018/4/16.
 */
@Controller
@RequestMapping("home")
public class HomeController {

    @RequestMapping(value="index")
    @ResponseBody
    public String index(String name){
        if("error".equals(name)){
            throw  new RuntimeException("模拟异常");
        }
        return "welcome!";
    }
}
