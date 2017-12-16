package com.done;

import com.alibaba.fastjson.JSON;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * Created by Done Lin on 2017/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")
@Log4j
public class RedisTemplateTest {

    @Autowired
    private RedisTemplate redisTemplate;


    @Test
    public void testStringOperations(){
        ValueOperations<String, String> stringOperations  = redisTemplate.opsForValue();
        // String类型数据存储，不设置过期时间，永久性保存
        stringOperations.set("date1", "2017-12-10");

        // String类型数据存储，设置过期时间为80秒，采用TimeUnit控制时间单位
        stringOperations.set("date2", "2017-12-02", 80, TimeUnit.SECONDS);
        stringOperations.setIfAbsent("date1", "2017-12-03");
        stringOperations.setIfAbsent("date3", "2017-12-05");

        String date1 = stringOperations.get("date1");
        String date2 = stringOperations.get("date2");
        String date3 = stringOperations.get("date3");
        log.info("date1 = " + date1 + " , date2 = " + date2 + " , date3 = "+date3);

    }

    @Test
    public void testValueOperations(){
        ValueOperations<String, Object> valueOperations  = redisTemplate.opsForValue();
        Element ele1 = new Element("43","linf");
        valueOperations.set("ele1",ele1);
        List<Element> list  = new ArrayList<Element>();
        Element ele2 = new Element("75","qin");
        list.add(ele1);
        list.add(ele2);
        valueOperations.set("ele2",list);
        redisTemplate.expire("ele2",600,TimeUnit.SECONDS);
        Element getEle1 = (Element) valueOperations.get("ele1");
        List<Element> getEle2 = (List<Element>) valueOperations.get("ele2");
        log.info("getEle1 = " + getEle1 + " , getEle2 = " + JSON.toJSONString(getEle2));
    }

    @Test
    public void testListOperations(){
        ListOperations<String, Element> listOperations  = redisTemplate.opsForList();
        for (int i = 0; i < 5; i++) {
            Element ele = new Element((42+i)+"","linf"+i);
            listOperations.leftPush("list1", ele);
            listOperations.rightPush("list2", ele);
        }
        long list1Size = listOperations.size("list1");
        long list2Size = listOperations.size("list2");
        List<Element> list1 = listOperations.range("list1",0,-1);
        List<Element> list2 = listOperations.range("list2",0,-1);
        log.info("list1 size = " + list1Size + " ,  " + JSON.toJSONString(list1));
        log.info("list2 size = " + list2Size + " ,  " + JSON.toJSONString(list2));
    }

    @Test
    public void testSetOperations(){
        SetOperations<String, Element> setOperations  = redisTemplate.opsForSet();
        for (int i = 0; i < 5; i++) {
            Element ele = new Element((42+i)+"","linf"+i);
            setOperations.add("set1", ele);
        }
        long setSize = setOperations.size("set1");
        Set<Element> result = setOperations.members("set1");
        log.info("set1 size = " + setSize + " ,  " + JSON.toJSONString(result));
    }

    @Test
    public void testZSetOperations(){
        ZSetOperations<String, Element> zSetOperations  = redisTemplate.opsForZSet();
        for (int i = 0; i < 5; i++) {
            Element ele = new Element((42+i)+"","linf"+i);
            zSetOperations.add("zset1", ele,i);
        }
        for (int i = 10; i > 5; i--) {
            Element ele = new Element((42+i)+"","linf"+i);
            zSetOperations.add("zset1", ele,i);
        }
        long zsetSize = zSetOperations.size("zset1");
        Set<Element> result1 = zSetOperations.rangeByScore("zset1",0,zsetSize);
        Set<Element> result2 = zSetOperations.range("zset1",0,-1);
        log.info("zset1 rangeByScore size = " + zsetSize + " ,  " + JSON.toJSONString(result1));
        log.info("zset1 size = " + zsetSize + " ,  " + JSON.toJSONString(result2));
    }

    @Test
    public void testHashOperations(){
        HashOperations<String, String,Object> hashOperations  = redisTemplate.opsForHash();
        List<Element> arrays = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Element ele = new Element((42+i)+"","linf"+i);
            arrays.add(ele);
        }
        hashOperations.put("hash1","map1", 9500);
        hashOperations.put("hash1","map2", arrays);

        long hashSize = hashOperations.size("hash1");
        List<Element> list = (List<Element>) hashOperations.get("hash1","map2");
        log.info("hashSize = " + hashSize + " , hash1  map2 " + JSON.toJSONString(list));
    }


    @Test
    public void testValue(){
        BoundValueOperations<String, Integer> valueOperations  = redisTemplate.boundValueOps("money");
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(1);
        valueOperations.increment(-1);
        valueOperations.increment(-1);
        log.info("money = " + valueOperations.get());
    }

}
