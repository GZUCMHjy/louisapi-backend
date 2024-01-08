//package com.yupi.springbootinit.mapper;
//
//import java.util.Date;
//import java.util.List;
//import javax.annotation.Resource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * 帖子数据库操作测试
// *
// * @author louis
// * 
// */
//@SpringBootTest
//class interfaceInfoMapperTest {
//
//    @Resource
//    private interfaceInfoMapper interfaceInfoMapper;
//
//    @Test
//    void listinterfaceInfoWithDelete() {
//        List<interfaceInfo> interfaceInfoList = interfaceInfoMapper.listinterfaceInfoWithDelete(new Date());
//        Assertions.assertNotNull(interfaceInfoList);
//    }
//}