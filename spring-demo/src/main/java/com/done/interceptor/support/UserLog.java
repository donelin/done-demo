package com.done.interceptor.support;

import java.lang.annotation.*;

/*
 * 方法上标识此注解，说明方法调用需要加入用户日志
 * Created by Done Lin on 2018/1/6.
 *
 *
 * @Documented 注解表明这个注解应该被 javadoc工具记录. 默认情况下,javadoc是不包括注解的.
 * 但如果声明注解时指定了 @Documented,则它会被 javadoc 之类的工具处理, 所以注解类型信息也会被包括在生成的文档中
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface UserLog {

    //操作类型
    OperationType value() ;

    enum OperationType{
        ADD("添加"),
        UPDATE("更新"),
        DELETE("删除");

        private OperationType(String title){
            this.title= title;
        }
        private String title;
        public String getTile(){
            return  this.title;
        }
        public void setTitle(String title){
            this.title=title;
        }
    }
}
