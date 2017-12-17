package com.done.test;

import com.alibaba.fastjson.JSON;
import com.done.domain.Class;
import com.mongodb.WriteResult;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.test.context.ContextConfiguration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Done Lin on 2017/9/2.
 */
@Log4j
@RunWith(JUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:/config/spring.xml")
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testInsert(){
        List persons = new ArrayList();
        Class.Person p = new Class().new Person();
        p.setPersonId("10000000");
        p.setName("林锋");
        p.setAge(45);
        persons.add(p);
        Class clazz = new Class("class10",persons);
        mongoTemplate.insert(clazz);
       // log.info("insert = "+JSON.toJSONString(clazz));
    }

    @Test
    public void testFind(){
        List<Class> result = mongoTemplate.find(Query.query(Criteria.where("name").is("class10")),Class.class);
        log.info("result = "+JSON.toJSONString(result));
    }

    @Test
    public void testUpdate(){
        Update update = Update.update("age",281).set("name","Sim123456");
        mongoTemplate.updateMulti(Query.query(Criteria.where("age").is(181)),update,Class.class);
    }

    @Test
    public void testFindAndModify(){
        Update update = Update.update("age",1081).set("name","chunlan");
       // Class p = mongoTemplate.findAndModify(Query.query(Criteria.where("age").is(381)),update,Class.class);
        Class p = mongoTemplate.findAndModify(Query.query(Criteria.where("age").is(581)),update,new FindAndModifyOptions().returnNew(true),Class.class);
        log.info(p);
    }

    @Test
    public void testDelete(){
        WriteResult result = mongoTemplate.remove(Query.query(Criteria.where("age").is(181)),Class.class);
        log.info(result);
    }

    @Test
    public void testQuery(){
        Query query = new Query(Criteria.where("age").lt(19).andOperator(Criteria.where("name").is("林锋"))).with(new Sort(new Sort.Order(Sort.Direction.DESC, "createTime")));
        List<Class>  result =mongoTemplate.find(query,Class.class);
        //mongoTemplate.count(query,Class.class);
        // log.info(result);
    }


    @Test
    public void dropCollection(){
        mongoTemplate.dropCollection("person");
        log.info("=========>删除完成");
    }



    @Test
    public void someCasesTest(){
        log.info(" ----------------  ");
        for(int i = 0;i<500;i++){
            Random randomNumber = new Random();
            log.info(randomNumber.nextInt(11));
        }
    }

}
