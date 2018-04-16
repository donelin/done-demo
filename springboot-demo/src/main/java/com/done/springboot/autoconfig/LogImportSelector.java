package com.done.springboot.autoconfig;

import org.springframework.context.annotation.ImportSelector;
import org.springframework.core.type.AnnotationMetadata;

/**
 * Created by Done Lin on 2018/4/7.
 */
public class LogImportSelector implements ImportSelector {


    @Override
    public String[] selectImports(AnnotationMetadata importingClassMetadata) {
        return new String[]{"com.done.springboot.autoconfig.LogBean"};
    }
}
