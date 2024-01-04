package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.Post;

import javax.servlet.http.HttpServletRequest;


/**
* @author 35064
* @description 针对表【interface_info(接口信息表)】的数据库操作Service
* @createDate 2023-12-23 13:42:57
*/
public interface InterfaceInfoService extends IService<InterfaceInfo> {
    /**
     * 校验
     * @param interfaceInfo 接口信息
     * @param add 添加类型
     */
    void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add);

     InterfaceInfo getInterfaceInfo(InterfaceInfo interfaceInfo, HttpServletRequest request);
}
