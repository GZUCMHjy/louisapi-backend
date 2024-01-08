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
// * @author louis
// * 
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