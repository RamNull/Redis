package com.example.demo.redis;

import net.logstash.logback.argument.StructuredArguments;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class CustomLogger {

    private  final Logger logger = LoggerFactory.getLogger(getClass());

    @Around(value = "execution(* com.example.demo.redis.*.*(..)) and args(key,value)")
    public void around(ProceedingJoinPoint jp,String key,String value) throws Throwable {
        //Log before execution
        jp.proceed();
        logDetails( key, value);
    }

    @Async
    private void logDetails(String key,String value) {
        logger.info("details",key,value);
    }
}
