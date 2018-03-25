package com.done.service;

import com.alibaba.fastjson.JSON;
import com.done.model.persist.User;
import com.done.service.support.Student;
import com.done.service.support.UserClass;
import com.done.util.ValidateUtils;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by Done Lin on 2018/1/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-context.xml")
//@Log4j
@Slf4j
public class BeanValidateTest {

    @Test
    public  void testBeanValidate(){
        UserClass clazz = new UserClass();
        clazz.setClassId(UUID.randomUUID().toString());
        clazz.setClassName("开心班");
        clazz.setOpend(true);
        clazz.setStudyTime(10);
        List students = new ArrayList<Student>();
        Student student = new Student();
        student.setName("ok");
        students.add(student);
        clazz.setStudents(students);

        ValidateUtils.validate(clazz);

        log.info("clazz = " + JSON.toJSONString(clazz));

    }

}
