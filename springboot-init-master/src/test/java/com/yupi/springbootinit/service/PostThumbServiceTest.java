//package com.yupi.springbootinit.service;
//
//import com.yupi.springbootinit.model.entity.User;
//import javax.annotation.Resource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * 帖子点赞服务测试
// *
// * @author louis
// * 
// */
//@SpringBootTest
//class interfaceInfoThumbServiceTest {
//
//    @Resource
//    private interfaceInfoThumbService interfaceInfoThumbService;
//
//    private static final User loginUser = new User();
//
//    @BeforeAll
//    static void setUp() {
//        loginUser.setId(1L);
//    }
//
//    @Test
//    void dointerfaceInfoThumb() {
//        int i = interfaceInfoThumbService.dointerfaceInfoThumb(1L, loginUser);
//        Assertions.assertTrue(i >= 0);
//    }
//}
