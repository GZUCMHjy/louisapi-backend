package com.yupi.springbootinit.service;

import io.lettuce.core.ScriptOutputType;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;


/**
 * @author louis
 * @version 1.0
 * @date 2024/1/4 18:58
 */
@SpringBootTest
public class UserInterfaceInfoServiceTest {
    @Resource
    UserInterfaceInfoService userInterfaceInfoService;
    @Test
    public void invokeCount() {
        boolean b = userInterfaceInfoService.invokeCount(1L, 1L);
        System.out.println(b);
    }
}