package com.done.service.support;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by Done Lin on 2018/1/28.
 */
public class UserClass {

    @Setter
    @Getter
    @NotBlank(message = "classId不能为空")
    private String classId;

    @Setter
    @Getter
    @NotBlank(message = "className不能为空")
    private String className;

    @Setter
    @Getter
    @NotNull(message = "opend不能为空")
    private Boolean opend;

    @Setter
    @Getter
    @NotNull(message = "studyTime不能为空")
    private Integer studyTime;

    @Setter
    @Getter
    @NotEmpty(message = "students不能为空")
    @Valid
    private List<Student> students;
}
