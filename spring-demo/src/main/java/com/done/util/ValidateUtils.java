package com.done.util;

import org.hibernate.validator.HibernateValidator;
import javax.validation.*;
import java.util.Set;

/**
 * 用于JSR303校验
 *
 * Created by Done Lin on 2018/1/24.
 */
public abstract class ValidateUtils {

    private static Validator validator=null;
    static{
        ValidatorFactory vf= Validation.byProvider(HibernateValidator.class).configure().buildValidatorFactory();
        validator=vf.getValidator();
    }
    public static <T>  void validate(T object){
        Set<ConstraintViolation<T>> set= validator.validate(object);
        if(!set.isEmpty()){
            StringBuilder sb=new StringBuilder();
            for(Object cv:set){
                sb.append("\n").append(((ConstraintViolation<?>)cv).getMessage());
            }
            throw new ConstraintViolationException(sb.toString(),set);
        }
    }

}
