package com.yupi.springbootinit.utils;

import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;

import java.util.Collections;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/16 16:01
 */
public class SimpleRedisLock implements ILock{
    // 业务名称
    private String name;
    private StringRedisTemplate stringRedisTemplate;

    public SimpleRedisLock(String name, StringRedisTemplate stringRedisTemplate) {
        this.name = name;
        this.stringRedisTemplate = stringRedisTemplate;
    }

    private static final String KEY_PREFIX = "lock:";
    private static final String ID_PREFIX = UUID.randomUUID().toString() + "-";

    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT;
    // 提前进行初始化
    static {
        UNLOCK_SCRIPT = new DefaultRedisScript<>();
        UNLOCK_SCRIPT.setLocation(new ClassPathResource("unlcok.lua"));
        UNLOCK_SCRIPT.setResultType(Long.class);
    }
    @Override
    public boolean tryLock(long timeoutSec) {
        // 1. 获取线程id + 拼接线程id
        String threadId = ID_PREFIX + Thread.currentThread().getId();
        // 2. 获取锁（相当于set lock thread1 NX EX 10）
        Boolean success = stringRedisTemplate.opsForValue().setIfAbsent(KEY_PREFIX + name, threadId, timeoutSec, TimeUnit.SECONDS);
        // 注意 方法返回类型为基本类型 而返回的是包装类型（为了防止NPE,均采用下列方法【其他的类型也是一样的】）
        return Boolean.TRUE.equals(success);
    }

//    @Override
//    public void unlock() {
//        // 1. 获取线程id
//        String threadId = ID_PREFIX + Thread.currentThread().getId();
//        // 2. 获取当前锁的标识
//        String id = stringRedisTemplate.opsForValue().get(KEY_PREFIX + name);
//        // 3. 判断当前线程是否持有锁
//        if(threadId.equals(id)){
//            // 3.1 释放锁（这个过程不是原子操作，应当将判断和释放合成一个原子操作,即变成事务操作）
//            // 可采用lua脚本
//            Boolean success = stringRedisTemplate.delete(KEY_PREFIX + name);
//        }
//    }

    /**
     * 采用lua脚本实现释放锁
     */
    @Override
    public void unlock() {
        // 执行lua脚本
        stringRedisTemplate.execute(
                UNLOCK_SCRIPT,
                Collections.singletonList(KEY_PREFIX + name),
                ID_PREFIX + Thread.currentThread().getId());
    }
}
