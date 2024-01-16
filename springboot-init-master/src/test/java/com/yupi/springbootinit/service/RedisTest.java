package com.yupi.springbootinit.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import javax.annotation.Resource;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/10 21:09
 */
@SpringBootTest
public class RedisTest {
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private StringRedisTemplate stringRedisTemplate;
    // string 入库
    @Test
    public void testForValue1(){
        String key = "zszxz";
        String value = "知识追寻者";
        stringRedisTemplate.opsForValue().set(key, value);
        System.out.println("made");
    }
}
