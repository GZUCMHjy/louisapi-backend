package com.yupi.springbootinit.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.yuapi.common.model.entity.UserInterfaceInfo;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/6 21:14
 */
public interface UserInterfaceInfoService extends IService<UserInterfaceInfo> {

    void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo,boolean add);
    boolean invokeCount(long interfaceInfoId,long userId);
}
