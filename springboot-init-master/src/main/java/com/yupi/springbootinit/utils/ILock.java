package com.yupi.springbootinit.utils;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/16 16:00
 */
public interface ILock {
    /**
     * 尝试获取锁
     * 非阻塞式
     * @param timeoutSec 过期时间
     * @return 是否成功获取锁
     */
    public boolean tryLock(long timeoutSec);

    /**
     * 释放锁
     * @return 是否释放成功
     */
    public void unlock();
}
