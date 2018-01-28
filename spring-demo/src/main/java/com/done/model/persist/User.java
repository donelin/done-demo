package com.done.model.persist;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by Done Lin on 2017/12/17.
 */
@Data
public class User implements Serializable {

    private String id;

    private String name;
}
