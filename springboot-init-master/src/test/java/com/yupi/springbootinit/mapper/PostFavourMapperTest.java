//package com.yupi.springbootinit.mapper;
//
//import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
//import com.baomidou.mybatisplus.core.metadata.IPage;
//import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
//import javax.annotation.Resource;
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.boot.test.context.SpringBootTest;
//
///**
// * 帖子收藏数据库操作测试
// *
// * @author louis
// * 
// */
//@SpringBootTest
//class interfaceInfoFavourMapperTest {
//
//    @Resource
//    private interfaceInfoFavourMapper interfaceInfoFavourMapper;
//
//    @Test
//    void listUserFavourinterfaceInfoByPage() {
//        IPage<interfaceInfo> page = new Page<>(2, 1);
//        QueryWrapper<interfaceInfo> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("id", 1);
//        queryWrapper.like("content", "a");
//        IPage<interfaceInfo> result = interfaceInfoFavourMapper.listFavourinterfaceInfoByPage(page, queryWrapper, 1);
//        Assertions.assertNotNull(result);
//    }
//}