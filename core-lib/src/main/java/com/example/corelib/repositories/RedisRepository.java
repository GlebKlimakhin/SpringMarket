package com.example.corelib.repositories;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisRepository {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    public void saveToken(String token){
        redisTemplate.opsForValue().set("token:" + token, 1, Duration.ofHours(1));
    }

    public boolean checkToken(String token){
        return redisTemplate.opsForValue().get("token:" + token) != null;
    }
}
