package com.done.springboot.jdbc;

import org.springframework.stereotype.Service;

/**
 * Created by Done Lin on 2018/4/8.
 */
@Service("userService")
public class UserServiceImpl implements IUserService {
    @Override
    public void testAop() {
        System.out.println("==========testAop========");
    }
}
