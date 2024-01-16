package com.yupi.springbootinit.service.impl.inner;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.yuapi.common.model.entity.InterfaceInfo;
import com.yuapi.common.service.InnerInterfaceInfoService;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import com.yupi.springbootinit.provider.DemoService;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author louis
 * @version 1.0
 * @date 2024/1/6 21:08
 */
@DubboService
@Component
public class InnerInterfaceInfoServiceImpl implements InnerInterfaceInfoService {
    @Resource
    private InterfaceInfoMapper interfaceInfoMapper;

    @Override
    public InterfaceInfo getInterfaceInfo(String url, String method) {
            if(StringUtils.isAnyBlank(url,method)){
                throw new BusinessException(ErrorCode.PARAMS_ERROR);
            }
        QueryWrapper<InterfaceInfo> interfaceInfoQueryWrapper = new QueryWrapper<>();
        interfaceInfoQueryWrapper.eq("url",url);
        interfaceInfoQueryWrapper.eq("method",method);
        return interfaceInfoMapper.selectOne(interfaceInfoQueryWrapper);
    }
}
