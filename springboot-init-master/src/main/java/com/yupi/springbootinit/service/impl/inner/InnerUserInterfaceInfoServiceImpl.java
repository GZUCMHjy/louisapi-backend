package com.yupi.springbootinit.service.impl.inner;

import com.yuapi.common.service.InnerInterfaceInfoService;
import com.yuapi.common.service.InnerUserInterfaceInfoService;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/6 21:11
 * 提供网关（远程）调用的接口方法
 */
@DubboService
@Component
public class InnerUserInterfaceInfoServiceImpl implements InnerUserInterfaceInfoService {

    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    @Override
    public boolean invokeCount(long interfaceId, long userId) {

        return userInterfaceInfoService.invokeCount(interfaceId,userId);
    }
}
