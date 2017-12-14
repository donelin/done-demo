package com.done.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * Created by Done Lin on 2017/9/2.
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "clazz")
public class Class {
    @Id
    private String classId;

    @Field("name")
    private String className;

    @Field("persons")
    private List<Person> personList;

    public Class(String className, List<Person> personList){
        this.className=className;
        this.personList=personList;
    }


    @Data
    public class Person{

        @Field("personId")
        private String  personId;

        @Field("personName")
        private String name;

        @Field("age")
        private int age;
    }
}
