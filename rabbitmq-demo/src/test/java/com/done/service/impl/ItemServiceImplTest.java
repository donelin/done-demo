package com.done.service.impl;

import com.done.service.IItemService;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by Done Lin on 2017/3/11.
 */
@Log4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:config/spring.xml")
public class ItemServiceImplTest {
    @Autowired
   private IItemService itemService;

    @Test
    public void getItemByOrder() throws Exception {
        log.info("-----------------------------------");
        System.out.println(itemService.getItemByOrder());
    }

}