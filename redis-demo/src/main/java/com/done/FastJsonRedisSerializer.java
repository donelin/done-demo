package com.done;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.util.Assert;

import java.nio.charset.Charset;

/**
 * @author linf
 * @since 2016/9/8
 */
public class FastJsonRedisSerializer implements RedisSerializer<Object> {

    private final Charset charset;

    public FastJsonRedisSerializer() {
        this(Charset.forName("UTF8"));
    }

    public FastJsonRedisSerializer(Charset charset)
    {
        Assert.notNull(charset);
        this.charset = charset;
    }


    @Override
    public byte[] serialize(Object s) throws SerializationException {

        if (s instanceof String) {
            return s == null ? null : ((String) s).getBytes(charset);
        } else {
            return JSON.toJSONString(s,SerializerFeature.WriteClassName).getBytes(charset);
        }
    }

    @Override
    public Object deserialize(byte[] bytes) throws SerializationException {
        if (bytes == null) {
            return null;
        }
        String temp = new String(bytes, charset);
        return temp.startsWith("{") && temp.endsWith("}")?JSON.parse(temp):temp.startsWith("[") && temp.endsWith("]")? JSONArray.parse(temp):temp;
    }
}
