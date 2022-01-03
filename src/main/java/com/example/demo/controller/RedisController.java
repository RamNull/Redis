package com.example.demo.controller;

import com.example.demo.Handler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.stream.IntStream;

@RestController
@SuppressWarnings("UNUSED_SYMBOL")
public class RedisController {


    @Autowired
    Handler handler;

    @GetMapping("/normalRedis")
    public String normalRedisGet(String key) {
        return null;
    }

    @GetMapping("/redisWithPolling")
    public String redisWithPollingGet(String key) {
        return null;
    }

    @GetMapping("/normalLettuce")
    public String normalLettuceGet(String key) {
        return null;
    }

    @GetMapping("/lettuceWithPolling")
    public String lettuceWithPolling(String key) {
        return null;
    }

    @PostMapping("/normalRedis")
    public String normalRedisSet()  {
        String uuid = UUID.randomUUID().toString();
        IntStream.range(0, 10).parallel().forEach(i ->
            handler.setPlainJedisSave(uuid)
        );
        return uuid;
    }

    @PostMapping("/normalLettuce")
    public String normalLettuceSet() {
        String uuid = UUID.randomUUID().toString();
        IntStream.range(0, 10).parallel().forEach(i -> handler.setPlainLettuceSave(uuid));
        return uuid;
    }

    @PostMapping("/poolRedis")
    public String poolRedisSet() {
        String uuid = UUID.randomUUID().toString();
        IntStream.range(0, 10).parallel().forEach(i -> handler.setPoolJedisSave(uuid));
        return uuid;
    }

    @PostMapping("/poolLettuce")
    public String poolLettuceSet() {
        String uuid = UUID.randomUUID().toString();
        IntStream.range(0, 10).parallel().forEach(i -> handler.setPoolLettuceSave(uuid));
        return uuid;
    }

    @GetMapping("/poolLettuce")
    public String poolLettuceGet() throws Exception {
        String uuid = "key00001c26-c8f3-447c-96c2-f389e424a8da";
        return handler.getPoolLettuce(uuid);
    }



    @PostMapping("/poolLettuceAsync")
    public String poolLettuceAsyncSet() {
        String uuid = UUID.randomUUID().toString();
        IntStream.range(0, 10).parallel().forEach(i -> handler.setPoolLettuceSaveAsync(uuid));
        return uuid;
    }

    @GetMapping("/poolLettuceAsync")
    public String poolLettuceAsyncGet() throws Exception {
        return handler.getPoolLettuceAsync("key");
    }

}
