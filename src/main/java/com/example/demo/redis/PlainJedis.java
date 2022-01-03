package com.example.demo.redis;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;

@Component
@SuppressWarnings("UNUSED_SYMBOL")
public class PlainJedis implements IRedis {

    private static final String connectionString = "localhost";


    @Override
    @Async
    public void save(String key, String value) {
        try (Jedis jedis = new Jedis(connectionString,6379)) {
            jedis.set(key, value);
        }
    }

    @Override
    public String get(String key) {
        try (Jedis jedis = new Jedis(connectionString,6379)) {
            return jedis.get(key);
        }
    }

    @Override
    public void delete(String key) {
        try (Jedis jedis = new Jedis(connectionString,6379)) {
            jedis.del(key);
        }
    }

    @Override
    public void save(String key, String value, int TTL) {
        try (Jedis jedis = new Jedis(connectionString,6379)) {
            jedis.set(key, value);
            jedis.expire(key, TTL);
        }
    }

    @Override
    public String ping() {
        try (Jedis jedis = new Jedis(connectionString,6379)) {
            return jedis.ping();
        }
    }
}
