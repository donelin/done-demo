package com.done.service.support;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Created by Done Lin on 2018/1/28.
 */
public class Student {

    @NotBlank(message = "student name不能为空")
    @Setter
    @Getter
    private String name;
}
