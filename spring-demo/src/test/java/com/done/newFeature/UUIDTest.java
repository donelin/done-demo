package com.done.newFeature;

import com.done.util.UUIDUtils;
import lombok.extern.log4j.Log4j;
import org.junit.Test;

/**
 * Created by Done Lin on 2018/1/28.
 */
@Log4j
public class UUIDTest {


    @Test
    public void testUUID(){

        log.info(UUIDUtils.UUID32());
    }
}
