package com.done.service;

import com.alibaba.fastjson.JSON;
import com.done.interceptor.support.UserLog;
import com.done.model.persist.User;
import com.done.model.persist.UserOperationLog;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.incrementer.MySQLMaxValueIncrementer;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Done Lin on 2017/12/17.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
@Log4j
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserOperationLogService logService;


    @Test
    public  void testSaveUser(){
        User user = new User();
        user.setId(""+System.currentTimeMillis());
        user.setName("林锋");
        userService.saveUser("789654123",user);
    }

    @Test
    public  void testDeleteUser(){
        User user = new User();
        user.setId(""+System.currentTimeMillis());
        user.setName("林锋");
        userService.deletUser("1515229756464");
    }


    @Test
    public  void testSaveUserAndAddUserOperationLog(){
        userService.saveUserAndAddUserOperationLog();
    }


    @Test
    public void batchSaveUserOperationLog(){
        List<UserOperationLog> logs = new ArrayList();
        UserOperationLog log1 = new UserOperationLog("a"+System.currentTimeMillis(),"963258", UserLog.OperationType.ADD);
        UserOperationLog log2 = new UserOperationLog("b"+System.currentTimeMillis(),"147852", UserLog.OperationType.UPDATE);
        logs.add(log1);
        logs.add(log2);
        logService.batchSaveUserOperationLog(logs);
    }

    @Test
    public void getUserOperationById(){
        UserOperationLog result1 = logService.getUserOperationById("b1515254987424");
        UserOperationLog result2 = logService.getUserOperationById("b1515254987424");
        log.debug("result1="+ JSON.toJSONString(result1));
        log.debug("result2="+ JSON.toJSONString(result1));
    }


    @Test
    public void saveByParameterName(){
        UserOperationLog log = new UserOperationLog(""+System.currentTimeMillis(),"11000", UserLog.OperationType.UPDATE);
        logService.saveByParameterName(log);
    }

}
