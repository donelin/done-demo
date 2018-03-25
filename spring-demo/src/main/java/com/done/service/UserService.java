package com.done.service;

import com.done.controller.dto.UserDto;
import com.done.controller.support.First;
import com.done.dao.UserDao;
import com.done.interceptor.support.UserLog;
import com.done.model.persist.User;
import com.done.model.persist.UserOperationLog;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.internal.engine.groups.Group;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.text.ParseException;

/**
 * Created by Done Lin on 2017/12/17.
 */
@Service
//@Log4j
@Slf4j
@Validated
public class UserService {

    @Autowired
    private UserDao userDao;
    @Autowired
    private UserOperationLogService logService;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @UserLog(UserLog.OperationType.ADD)
    public void saveUser(String userId,User user){
        userDao.save(user);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    @UserLog(UserLog.OperationType.DELETE)
    public void deletUser(String userId){
        userDao.deletUser(userId);
    }


    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void  saveUserAndAddUserOperationLog(){
        log.debug("开始：saveUserAndAddUserOperationLog()");
        User user = new User();
        user.setId(""+System.currentTimeMillis());
        user.setName("林锋");
        UserService userService = (UserService) AopContext.currentProxy();
        userService.saveUser(user.getId(),user);

        UserOperationLog log = new UserOperationLog(System.currentTimeMillis()+"",user.getId(), UserLog.OperationType.ADD);
        logService.saveUserOperationLog(log);
    }

    public User validateBaseTypeParams(@NotBlank(message = "userId不能为空") String userId,@NotNull(message = "age不能为空")Integer age) {
        return new User();
    }

    public User validateBeanParams(@Valid @NotNull  UserDto dto) {
        return new User();
    }
}
