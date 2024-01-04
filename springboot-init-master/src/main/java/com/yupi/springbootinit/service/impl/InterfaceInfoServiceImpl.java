package com.yupi.springbootinit.service.impl;
import java.util.Date;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.mapper.InterfaceInfoMapper;
import com.yupi.springbootinit.model.entity.InterfaceInfo;
import com.yupi.springbootinit.model.entity.Post;
import com.yupi.springbootinit.service.InterfaceInfoService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
* @author 35064
* @description 针对表【interface_info(接口信息表)】的数据库操作Service实现
* @createDate 2023-12-23 13:42:57
*/
@Service
public class InterfaceInfoServiceImpl extends ServiceImpl<InterfaceInfoMapper, InterfaceInfo>
    implements InterfaceInfoService {

    @Override
    public void validInterfaceInfo(InterfaceInfo interfaceInfo, boolean add) {

        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }

        // alt + enter 自动生成get方法（GenerateAllSetter插件）
        String interfaceInfoName = interfaceInfo.getName();

        String method = interfaceInfo.getMethod();
        String url = interfaceInfo.getUrl();
        // 创建时，参数不能为空
        if (add) {
            ThrowUtils.throwIf(StringUtils.isAnyBlank(interfaceInfoName), ErrorCode.PARAMS_ERROR);
        }
        // 有参数则校验
        if (StringUtils.isNotBlank(interfaceInfoName) && interfaceInfoName.length() > 80) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR, "接口名字过长");
        }
    }

    @Override
    public InterfaceInfo getInterfaceInfo(InterfaceInfo interfaceInfo, HttpServletRequest request) {
        return null;
    }
}




