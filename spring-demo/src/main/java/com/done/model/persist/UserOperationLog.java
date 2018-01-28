package com.done.model.persist;

import com.done.interceptor.support.UserLog;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * Created by Done Lin on 2017/12/17.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserOperationLog implements Serializable {

    private String id;

    private String userId;

    private UserLog.OperationType type;

}
