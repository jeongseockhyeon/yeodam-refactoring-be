package com.example.yeodamrefactoring.global.Dao;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class RedisDao {

    private final RedisTemplate<String, String> redisTemplate;

    public void setRefreshToken(String key, String refreshToken, long refreshTokenValidTime) {
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(refreshToken.getClass()));
        redisTemplate.opsForValue().set(key, refreshToken, refreshTokenValidTime, TimeUnit.MINUTES);
    }

    public String getRefreshToken(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    public void deleteRefreshToken(String key) {
        redisTemplate.delete(key);
    }

    public boolean hasKey(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    public void setBlackList(String accessToken, String msg, Long minutes){
        redisTemplate.setValueSerializer(new Jackson2JsonRedisSerializer<>(msg.getClass()));
        redisTemplate.opsForValue().set(accessToken, msg, minutes, TimeUnit.MINUTES);
    }

    public String getBlackList(String key){
        return redisTemplate.opsForValue().get(key);
    }

    public boolean deleteBlackList(String key){
        return Boolean.TRUE.equals(redisTemplate.delete(key));
    }

}
