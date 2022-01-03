package com.example.demo.redis;

import org.springframework.stereotype.Component;

@Component
public interface IRedis {


    void save(String key,String value);
    String get(String key) throws Exception;
    void delete(String key);
    void save(String key,String value,int TTL);
    String ping();

}
