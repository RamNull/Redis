package com.example.demo.redis;

import io.lettuce.core.RedisClient;
import io.lettuce.core.RedisURI;
import io.lettuce.core.api.StatefulRedisConnection;
import io.lettuce.core.api.sync.RedisCommands;
import io.lettuce.core.support.ConnectionPoolSupport;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Component
@SuppressWarnings("UNUSED_SYMBOL")
public class LettuceWithPooling implements IRedis {

    static final RedisURI redisURI = RedisURI.Builder.redis("localhost", 6379).build();
    static final RedisClient redisClient = RedisClient.create(redisURI);
    static final GenericObjectPool<StatefulRedisConnection<String, String>> pool =
            ConnectionPoolSupport.createGenericObjectPool(redisClient::connect, new GenericObjectPoolConfig<>());


    @Override
    @Async
    public void save(String key, String value) {
        try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
            RedisCommands<String, String> syncCommands = connection.sync();
            syncCommands.set(key, value);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public String get(String key) throws Exception {
        try (StatefulRedisConnection<String, String> connection = pool.borrowObject()) {
            RedisCommands<String, String> syncCommands = connection.sync();
            return syncCommands.get(key);
        }
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
