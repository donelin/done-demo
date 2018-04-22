package com.done.springboot.multiDataSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Done Lin on 2018/4/21.
 */
@Slf4j
@Repository
public class RoleDao {


    @Autowired
    @Qualifier("roleJdbcTemplate")
    private JdbcTemplate template;


    public void save(){
        String sql = "insert into role values('7000','高级开发人员')";
        template.execute(sql);
    }
}
