package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.yupi.springbootinit.model.entity.interfaceInfo;
import com.yupi.springbootinit.model.entity.User;
import javax.annotation.Resource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 帖子收藏服务测试
 *
 * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
 * @from <a href="https://yupi.icu">编程导航知识星球</a>
 */
@SpringBootTest
class interfaceInfoFavourServiceTest {

    @Resource
    private interfaceInfoFavourService interfaceInfoFavourService;

    private static final User loginUser = new User();

    @BeforeAll
    static void setUp() {
        loginUser.setId(1L);
    }

    @Test
    void dointerfaceInfoFavour() {
        int i = interfaceInfoFavourService.dointerfaceInfoFavour(1L, loginUser);
        Assertions.assertTrue(i >= 0);
    }

    @Test
    void listFavourinterfaceInfoByPage() {
        QueryWrapper<interfaceInfo> interfaceInfoQueryWrapper = new QueryWrapper<>();
        interfaceInfoQueryWrapper.eq("id", 1L);
        interfaceInfoFavourService.listFavourinterfaceInfoByPage(Page.of(0, 1), interfaceInfoQueryWrapper, loginUser.getId());
    }
}
