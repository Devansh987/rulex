package com.rulex.rulex.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class redistest {

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void sendMail(){
        redisTemplate.opsForValue().set("Email","d98291579@gmil.com");
        redisTemplate.opsForValue().get("Email");
        int a = 1;
    }
}

