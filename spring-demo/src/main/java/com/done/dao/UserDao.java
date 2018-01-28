package com.done.dao;

import com.done.model.persist.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by Done Lin on 2017/12/17.
 */
@Repository
public class UserDao {

    @Autowired
    private JdbcTemplate jt;

    public void save(User user){
        jt.update("INSERT INTO USER VALUES('"
                + user.getId() + "', '"
                + user.getName() + "')");
    }

    public void deletUser(String userId) {
        jt.update("delete from USER where id='"+userId+"'");
    }
}
