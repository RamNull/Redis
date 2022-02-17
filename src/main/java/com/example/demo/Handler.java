package com.example.demo;

import com.example.demo.redis.IRedis;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;


@Component
public class Handler {

    @Autowired
    IRedis plainJedis;

    @Autowired
    IRedis plainLettuce;

    @Autowired
    IRedis jedisWithPooling;

    @Autowired
    IRedis lettuceWithPooling;

    @Autowired
    IRedis lettuceWithPoolingAsync;

    public void setPlainJedisSave(String key) {
        String date = new DateTime().toString();
        plainJedis.save("key".concat(key), "date is ".concat(date));
    }

    public void setPlainLettuceSave(String key) {
        String date = new DateTime().toString();
        plainLettuce.save("key".concat(key), "date is ".concat(date));
    }


    public void setPoolJedisSave(String key) {
        String date = new DateTime().toString();
        jedisWithPooling.save("key".concat(key), "date is ".concat(date));
    }

    public void setPoolLettuceSave(String key) {
        try {
            String date = new DateTime().toString();
            lettuceWithPooling.save("key".concat(UUID.randomUUID().toString()), "date is ".concat(date));
        }catch (Exception ex)
        {
            ex.printStackTrace();
            throw ex;
        }
    }

    public String getPoolLettuce(String key) throws Exception {
        return lettuceWithPooling.get(key);
    }


    public void setPoolLettuceSaveAsync(String key) {
        String date = new DateTime().toString();
        lettuceWithPoolingAsync.save("key".concat(key), "date is ".concat(date));
    }

    public String getPoolLettuceAsync(String key) throws Exception {
        return lettuceWithPoolingAsync.get(key);
    }
}
