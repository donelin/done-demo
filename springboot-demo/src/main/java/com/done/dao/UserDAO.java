package com.done.dao;

import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.FileNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Done Lin on 2018/3/14.
 */
@Repository
@Slf4j
public class UserDAO{


    @Autowired
    private JdbcTemplate template;

        @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
        public void save(String... names) throws FileNotFoundException {
            String sql;
            try {
                for(String name:names) {
                    sql = "insert into user values('1','" + name + "')";
                    template.execute(sql);
                }
                //throw new NullPointerException("空指针异常");
            }catch (Exception e){
                //throw new FileNotFoundException("文件不存在异常");
            }finally{
                UserDAO userDAO = (UserDAO) AopContext.currentProxy();
                userDAO.findAndSave();
            }
        }

        @Transactional(readOnly = false,propagation = Propagation.REQUIRES_NEW)
        public void findAndSave(){
            String sql="select * from user";
            List<User> users = template.query(sql, new Object[]{}, new BeanPropertyRowMapper<User>(User.class));
            log.info("users{}", JSON.toJSONString(users));

            List<String> names = users.stream().map(o->o.getName()).collect(Collectors.toList());
            names.add("lingxiaoling");
            for(String name:names) {
                sql = "insert into username values('" + name + "')";
                template.execute(sql);
            }
        }



}

@Data
class User{
    private String id;
    private String name;
}