package com.done.service;

import com.done.controller.dto.UserDto;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by Done Lin on 2018/1/21.
 */
@Service("valdate")
//@Log4j
@Slf4j
public class ValdateImpl  implements IValdateInterface{
    @Override
    @Transactional(readOnly = true,propagation = Propagation.SUPPORTS)
    public void validateBaseType(Integer age) {
        log.info("age= "+age);
    }

    @Override
    public void validateBean(UserDto dto) {
        log.info("dto= "+dto);
    }
}
