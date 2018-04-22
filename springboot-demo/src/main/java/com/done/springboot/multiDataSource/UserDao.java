package com.done.springboot.multiDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Done Lin on 2018/4/21.
 */
@Slf4j
@Repository
public class UserDao {

    @Autowired
    @Qualifier("userJdbcTemplate")
    private JdbcTemplate template;


    public void save(){
        String sql = "insert into user values('3500','林锋')";
        template.execute(sql);
    }

}
