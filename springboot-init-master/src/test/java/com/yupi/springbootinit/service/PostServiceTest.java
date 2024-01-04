//package com.yupi.springbootinit.service;
//
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//
//import javax.annotation.Resource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * 帖子服务测试
// *
// * @author <a href="https://github.com/liyupi">程序员鱼皮</a>
// * @from <a href="https://yupi.icu">编程导航知识星球</a>
// */
//@SpringBootTest
//class interfaceInfoServiceTest {
//
//    @Resource
//    private interfaceInfoService interfaceInfoService;
//
//    @Test
//    void searchFromEs() {
//        interfaceInfoQueryRequest interfaceInfoQueryRequest = new interfaceInfoQueryRequest();
//        interfaceInfoQueryRequest.setUserId(1L);
//        Page<interfaceInfo> interfaceInfoPage = interfaceInfoService.searchFromEs(interfaceInfoQueryRequest);
//        Assertions.assertNotNull(interfaceInfoPage);
//    }
//
//}