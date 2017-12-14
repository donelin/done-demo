package com.done;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

/**
 * Created by Done Lin on 2017/11/16.
 */
public class RateLimiter {

    //频率（单位：次）
    private long limit;
    //间隔时间(单位：秒)
    private long intervalInMills;
    //操作redis对象
    private RedisTemplate rt;

    private  ValueOperations ops;

    public RateLimiter(RedisTemplate template,long intervalInMills,long limit) {
        this.rt = template;
        this.ops= rt.opsForValue();
        this.intervalInMills = intervalInMills;
        this.limit = limit;
    }

    public boolean access(String userId) {
        String key = getKey(userId);
        Long  value= ops.increment(key,1L);
        if(1L==value){
            //设置过期时间
            ops.getOperations().expire(key,intervalInMills, TimeUnit.SECONDS);
        }else if(this.limit<value){
            return false;
        }
        return true;
    }

    private String getKey(String userId) {
        return "rate:limiter:" + intervalInMills + ":" + limit + ":" + userId;
    }
}