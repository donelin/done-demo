package com.done.controller;

import com.alibaba.fastjson.JSON;
import com.done.controller.dto.UserDto;
import com.done.dao.UserDao;
import com.done.model.persist.User;
import com.done.service.IValdateInterface;
import com.done.service.UserService;
import lombok.extern.log4j.Log4j;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Done Lin on 2018/1/10.
 */
@Controller
@RequestMapping("/user")
@Log4j
//@Validated
public class UserController {

    @Autowired
    private UserService userService;


    @Autowired
    private IValdateInterface valdate;

    @RequestMapping("sayHello")
    public String sayHello(){

        return "hello";
    }

    @RequestMapping("sayHelloString")
    @ResponseBody
    public User getUserInfo(User user){
        return user;
    }


    @RequestMapping("validateControllerParams")
    @ResponseBody
    public String validateControllerParams(@NotNull(message = "age不能为空") Integer age){
       //  userService.findUserById(null);
         return "success";
    }

    @RequestMapping("validateControllerBeanParams")
    @ResponseBody
    public String validateControllerBeanParams(@Valid @ModelAttribute UserDto user){
        log.info(JSON.toJSONString(user));
        return "user success";
    }


    @RequestMapping("validateServiceBaseTypeParams")
    @ResponseBody
    public String validateServiceBaseTypeParams(){
        userService.validateBaseTypeParams("12321",78421);
        return "success";
    }

    @RequestMapping("validateServiceBeanParams")
    @ResponseBody
    public String validateServiceBeanParams(){
        userService.validateBeanParams(null);
        return "user success";
    }


    @RequestMapping("validateInterfaceBaseTypeParams")
    @ResponseBody
    public String validateInterfaceBaseTypeParams(){
        valdate.validateBaseType(123456789);
        return "success";
    }

    @RequestMapping("validateInterfaceBeanParams")
    @ResponseBody
    public String validateInterfaceBeanParams(){
        valdate.validateBean(new UserDto());
        return "user success";
    }


}
