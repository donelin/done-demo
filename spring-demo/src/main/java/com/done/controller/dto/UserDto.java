package com.done.controller.dto;

import com.done.controller.support.First;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotNull;

/**
 * Created by Done Lin on 2018/1/21.
 */
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    @NotBlank(message = "userId不能为空")
    private String userId;

    @NotBlank(message = "userName不能为空")
    private String userName;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
