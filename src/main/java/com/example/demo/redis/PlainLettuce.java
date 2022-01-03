package com.example.demo.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.async.RedisAsyncCommands;
import org.springframework.stereotype.Component;

@Component
@SuppressWarnings("UNUSED_SYMBOL")
public class PlainLettuce implements IRedis {

    static final RedisURI redisURI = RedisURI.Builder.redis("localhost", 6379).build();
    static final RedisClient redisClient = RedisClient.create(redisURI);
    static final  StatefulRedisConnection<String, String> connection = redisClient.connect();


    @Override
    public void save(String key, String value) {
        RedisAsyncCommands<String, String> asyncCommands = connection.async();
        asyncCommands.set(key, value);
    }

    @Override
    public String get(String key) {
        return null;
    }

    @Override
    public void delete(String key) {

    }

    @Override
    public void save(String key, String value, int TTL) {

    }

    @Override
    public String ping() {
        return null;
    }
}
