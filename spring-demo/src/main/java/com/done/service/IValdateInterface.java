package com.done.service;

import com.done.controller.dto.UserDto;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Done Lin on 2018/1/21.
 */
@Validated
public interface IValdateInterface {


    public void validateBaseType(@NotNull(message="age不能为空")   Integer age);


    public void validateBean(@Valid @NotNull(message="dto不能为空")   UserDto dto);
}
