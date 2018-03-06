package com.done.web;

import com.done.web.setting.CoreSettings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private CoreSettings coreSettings;

    @RequestMapping("/say")
    @ResponseBody
    public String home(String name) {
        log.debug(" debug hello:{}",name);
        log.info(" info hello:{}",name);
        log.warn(" warn hello:{}",name);
        log.error(" error hello:{}",name);
        log.debug(" hello:{}",coreSettings.getUrl());
        return "hello world";
    }
}
