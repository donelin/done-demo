package com.done.springboot.multiDataSource;

import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = TestBeanConfiguration.class)
public class SpringbootDemoApplicationTests {

	@Autowired
	private TestBean bean;

	@Autowired
	private MuliMongodbService service;

	@Test
	public void testSave() {
		service.save();
		System.out.println(JSON.toJSONString(bean));
	}

}
