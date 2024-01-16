package com.yupi.springbootinit.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yuapi.common.model.entity.UserInterfaceInfo;

import java.util.List;

/**
* @author 35064
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Mapper
* @createDate 2024-01-04 16:50:32
* @Entity com.yupi.springbootinit.model.entity.UserInterfaceInfo
*/
public interface UserInterfaceInfoMapper extends BaseMapper<UserInterfaceInfo> {

    // select interfaceInfoId,sum(totalNum)
    // as totalNum from user_Interface_info
    // group by interfaceId
    // order by totalNum desc
    // limit {}
    List<UserInterfaceInfo> listTopInvokeInterfaceInfo(int limit);
}




