package com.done.service;

import com.done.dao.UserOprationLogDao;
import com.done.model.persist.UserOperationLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Done Lin on 2017/12/17.
 */
@Service("userOperationLogService")
public class UserOperationLogService {

    @Autowired
    private UserOprationLogDao logDao;

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void saveUserOperationLog(UserOperationLog log){
        logDao.save(log);
    }

    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void batchSaveUserOperationLog(List<UserOperationLog> logs){
        logDao.batchSave(logs);
    }

    @Cacheable(cacheNames = {"userOperationLogCache"})
    public UserOperationLog getUserOperationById(String id){
        return logDao.getUserOperationById(id);
    }


    @Transactional(propagation = Propagation.REQUIRED,readOnly = false)
    public void saveByParameterName(UserOperationLog log){
        logDao.saveByParameterName(log);
    }
}
