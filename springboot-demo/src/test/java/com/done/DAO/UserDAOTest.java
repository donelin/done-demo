package com.done.DAO;

import com.alibaba.fastjson.JSON;
import com.done.javaconfig.SpringConfig;
import com.done.javaconfig.UserDAO;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author: done
 * @Date: 2018/3/6 14:41
 */

@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {SpringConfig.class})
public class UserDAOTest {

    @Autowired
    private UserDAO dao;


    @Test
    public  void  testQueryUserList(){
        log.info("user:{}", JSON.toJSONString(dao.queryUserList()));
    }
}
