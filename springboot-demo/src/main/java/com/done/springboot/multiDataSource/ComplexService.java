package com.done.springboot.multiDataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Done Lin on 2018/4/21.
 */
@Service
public class ComplexService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;


    @Transactional(readOnly = false,propagation = Propagation.REQUIRED)
    public void  save(){
        userDao.save();
        roleDao.save();
    }
}
