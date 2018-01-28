package com.done.util;

import org.hibernate.id.UUIDHexGenerator;

/**
 * Created by Done Lin on 2018/1/28.
 */
public class UUIDUtils {

    public static String UUID32() {
        return new UUIDHexGenerator().generate(null, null).toString();
    }
}
