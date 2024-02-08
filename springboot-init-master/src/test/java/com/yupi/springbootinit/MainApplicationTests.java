package com.yupi.springbootinit;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuapi.common.model.entity.User;
import com.yupi.springbootinit.config.WxOpenConfig;
import javax.annotation.Resource;

import com.yupi.springbootinit.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 主类测试
 *
 * @author louis
 * 
 */
@SpringBootTest
class MainApplicationTests {

    @Resource
    private UserService userService;

    @Resource
    private WxOpenConfig wxOpenConfig;

    @Test
    void contextLoads() {
        // System.out.println(wxOpenConfig);
        QueryWrapper<com.yuapi.common.model.entity.User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("userName","louis");
        User one = userService.getOne(userQueryWrapper);
        System.out.println(one);
    }

}
