package com.yupi.springbootinit.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yuapi.common.model.entity.UserInterfaceInfo;

import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.mapper.UserInterfaceInfoMapper;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import com.yupi.springbootinit.utils.SimpleRedisLock;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
* @author 35064
* @description 针对表【user_interface_info(用户调用接口关系)】的数据库操作Service实现
* @createDate 2024-01-04 16:50:32
*/
@Service
public class UserInterfaceInfoServiceImpl extends ServiceImpl<UserInterfaceInfoMapper, UserInterfaceInfo>
    implements UserInterfaceInfoService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void validUserInterfaceInfo(UserInterfaceInfo userInterfaceInfo, boolean add) {
        // 创建时，参数不能为空
        if (add) {
            if (userInterfaceInfo.getInterfaceInfoId() <= 0 || userInterfaceInfo.getUserId() <= 0){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求接口不存在");
            }
        }
        // 有参数则校验
        if (userInterfaceInfo.getLeftNum() < 0 ) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"调用次数不够");
        }
    }

    /**
     * 统计接口调用次数
     * @param interfaceId 接口id
     * @param userId 调用者id
     * @return
     */
    @Override
    public boolean invokeCount(long interfaceId, long userId) {
        if(interfaceId <= 0 || userId <= 0 ){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 减数操作
        UpdateWrapper<UserInterfaceInfo> userInterfaceInfoUpdateWrapper = new UpdateWrapper<>();
        userInterfaceInfoUpdateWrapper.eq("interfaceInfoId",interfaceId)
                        .eq("userId",userId);
        // 锁的范围是用户id(对并发性影响并不大)
        SimpleRedisLock simpleRedisLock = new SimpleRedisLock("invoke:" + String.valueOf(userId), stringRedisTemplate);
        boolean isLock = simpleRedisLock.tryLock(200);
        if(!isLock){
            // 失败
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"不允许连续多次调用接口");
        }
        try{
            userInterfaceInfoUpdateWrapper.gt("leftNum" ,0);
            userInterfaceInfoUpdateWrapper.setSql("totalNum = totalNum + 1")
                    .setSql("leftNum = leftNum - 1");
        }finally {
            simpleRedisLock.unlock();
        }
        return update(userInterfaceInfoUpdateWrapper);
    }
}




