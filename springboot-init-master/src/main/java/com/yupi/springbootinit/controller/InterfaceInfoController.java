package com.yupi.springbootinit.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.louis.louisapiclientsdk.client.LouisApiClient;
import com.louis.louisapiclientsdk.common.BaseResult;
import com.yuapi.common.model.entity.InterfaceInfo;
import com.yuapi.common.model.entity.User;
import com.yuapi.common.model.entity.UserInterfaceInfo;
import com.yupi.springbootinit.annotation.AuthCheck;
import com.yupi.springbootinit.common.*;
import com.yupi.springbootinit.constant.CommonConstant;
import com.yupi.springbootinit.constant.UserConstant;
import com.yupi.springbootinit.exception.BusinessException;
import com.yupi.springbootinit.exception.ThrowUtils;
import com.yupi.springbootinit.model.dto.interfaceinfo.InterfaceInfoAddRequest;
import com.yupi.springbootinit.model.dto.interfaceinfo.InterfaceInfoQueryRequest;
import com.yupi.springbootinit.model.dto.interfaceinfo.InterfaceInfoUpdateRequest;
import com.yupi.springbootinit.model.enums.InterfaceInfoStatusEnum;
import com.yupi.springbootinit.service.InterfaceInfoService;
import com.yupi.springbootinit.service.UserInterfaceInfoService;
import com.yupi.springbootinit.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import com.fasterxml.jackson.core.type.TypeReference;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.List;

/**
 * 帖子接口
 *
 * @author louis
 * 
 */
@RestController
@RequestMapping("/interfaceInfo")
@Slf4j
public class InterfaceInfoController {
    @Resource
    private InterfaceInfoService interfaceInfoService;

    @Resource
    private UserService userService;


    @Resource
    private UserInterfaceInfoService userInterfaceInfoService;
    /**
     * 接口客户端
     */
    @Resource
    private LouisApiClient louisApiClient;

    private final static Gson GSON = new Gson();

    /**
     * 创建
     *
     * @param interfaceInfoAddRequest
     * @param request
     * @return
     */
    @PostMapping("/add")
    public BaseResponse<Long> addInterfaceInfo(@RequestBody InterfaceInfoAddRequest interfaceInfoAddRequest, HttpServletRequest request) {
        if (interfaceInfoAddRequest == null) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoAddRequest, interfaceInfo);
        interfaceInfoService.validInterfaceInfo(interfaceInfo, true);
        User loginUser = userService.getLoginUser(request);
        interfaceInfo.setUserId(loginUser.getId());
        interfaceInfo.setMethod(interfaceInfoAddRequest.getMethod());
        interfaceInfo.setUrl(interfaceInfo.getUrl());
        interfaceInfo.setRequestHeader(interfaceInfoAddRequest.getRequestHeader());
        interfaceInfo.setResponseHeader(interfaceInfoAddRequest.getResponseHeader());
        interfaceInfo.setUsername(loginUser.getUserAccount());
        interfaceInfo.setName(interfaceInfoAddRequest.getName());
        boolean result = interfaceInfoService.save(interfaceInfo);
        ThrowUtils.throwIf(!result, ErrorCode.OPERATION_ERROR);
        long newinterfaceInfoId = interfaceInfo.getId();
        return ResultUtils.success(newinterfaceInfoId);
    }

    /**
     * 删除
     *
     * @param deleteRequest
     * @param request
     * @return
     */
    @PostMapping("/delete")
    public BaseResponse<Boolean> deleteInterfaceInfo(@RequestBody DeleteRequest deleteRequest, HttpServletRequest request) {
        if (deleteRequest == null || deleteRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User user = userService.getLoginUser(request);
        long id = deleteRequest.getId();
        // 判断是否存在
        InterfaceInfo oldinterfaceInfo = interfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldinterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        // 仅本人或管理员可删除
        if (!oldinterfaceInfo.getUserId().equals(user.getId()) && !userService.isAdmin(request)) {
            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
        }
        boolean b = interfaceInfoService.removeById(id);
        return ResultUtils.success(b);
    }

    /**
     * 更新（仅管理员）
     *
     * @param interfaceInfoUpdateRequest
     * @return
     */
    @PostMapping("/update")
    @AuthCheck(mustRole = UserConstant.ADMIN_ROLE)
    public BaseResponse<Boolean> updateInterfaceInfo(@RequestBody InterfaceInfoUpdateRequest interfaceInfoUpdateRequest) {
        if (interfaceInfoUpdateRequest == null || interfaceInfoUpdateRequest.getId() <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoUpdateRequest, interfaceInfo);
        // 参数校验
        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
        long id = interfaceInfoUpdateRequest.getId();
        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        ThrowUtils.throwIf(oldInterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
        boolean result = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(result);
    }
    @GetMapping("list/page")
    public BaseResponse<Page<InterfaceInfo>> listInterfaceInfoByPage(InterfaceInfoQueryRequest interfaceInfoQueryRequest, HttpServletRequest request){
        if(interfaceInfoQueryRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        BeanUtils.copyProperties(interfaceInfoQueryRequest,interfaceInfoQuery);
        long current = interfaceInfoQueryRequest.getCurrent();
        long pageSize = interfaceInfoQueryRequest.getPageSize();
        String sortField = interfaceInfoQueryRequest.getSortField();
        String sortOrder = interfaceInfoQueryRequest.getSortOrder();
        if(pageSize > 50){
            // 限制爬虫
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderBy(StringUtils.isNotBlank(sortField),sortOrder.equals(CommonConstant.SORT_ORDER_ASC),sortField);
        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, pageSize), queryWrapper);
        return ResultUtils.success(interfaceInfoPage);
    }

    /**
     * 获取列表（仅管理员查看）
     * @param interfaceInfoQueryRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @GetMapping("/list")
    public BaseResponse<List<InterfaceInfo>> listInterfaceInfo(InterfaceInfoQueryRequest interfaceInfoQueryRequest){
        InterfaceInfo interfaceInfoQuery = new InterfaceInfo();
        if(interfaceInfoQueryRequest == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        QueryWrapper<InterfaceInfo> queryWrapper = new QueryWrapper<>();
        List<InterfaceInfo> interfaceInfoList = interfaceInfoService.list(queryWrapper);
        return ResultUtils.success(interfaceInfoList);
    }


    /**
     * 发布接口
     * @param idRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @PostMapping("/online")
    public BaseResponse<Boolean> onlineInterfaceInfo(@RequestBody IdRequest idRequest){
        Long id = idRequest.getId();
        if(id <= 0 || id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 查找对应的接口
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        if(interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"未找到该接口");
        }
        com.louis.louisapiclientsdk.model.User user = new com.louis.louisapiclientsdk.model.User();
        user.setName("test");
        String usernameByPost = louisApiClient.getUsernameByPost(user);
        if(StringUtils.isBlank(usernameByPost)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"接口验证失败");
        }
        // 仅本人或者管理员发布接口
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.ONLINE.getValue());
        boolean b = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(b);
    }

    /**
     * 下线接口
     * @param idRequest
     * @return
     */
    @AuthCheck(mustRole = "admin")
    @PostMapping("/offline")
    public BaseResponse<Boolean> offlineInterfaceInfo(@RequestBody IdRequest idRequest){
        Long id = idRequest.getId();
        if(id <= 0 || id == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        // 查找对应的接口
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        if(interfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR,"未找到该接口");
        }
        com.louis.louisapiclientsdk.model.User user = new com.louis.louisapiclientsdk.model.User();
        user.setName("test");
        String usernameByPost = louisApiClient.getUsernameByPost(user);
        if(StringUtils.isBlank(usernameByPost)){
            throw new BusinessException(ErrorCode.SYSTEM_ERROR,"接口验证失败");
        }
        // 仅本人或者管理员下线接口
        interfaceInfo.setStatus(InterfaceInfoStatusEnum.OFFLINE.getValue());
        boolean b = interfaceInfoService.updateById(interfaceInfo);
        return ResultUtils.success(b);
    }
    @PostMapping("/invoke")
    public BaseResponse<Object> invokeInterfaceInfo(@RequestBody InterfaceInfoInvoke interfaceInfoInvoke,HttpServletRequest request){
        if(interfaceInfoInvoke == null || interfaceInfoInvoke.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        Long id = interfaceInfoInvoke.getId();

        // 判断是否存在
        InterfaceInfo oldInterfaceInfo = interfaceInfoService.getById(id);
        if(oldInterfaceInfo == null){
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        if(oldInterfaceInfo.getStatus() == InterfaceInfoStatusEnum.OFFLINE.getValue()){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"接口已关闭");
        }
        User loginUser = userService.getLoginUser(request);
        Long userId = loginUser.getId();
        // 添加用户接口信息
        saveUserInterfaceInfo(userId,id);

        String accessKey = loginUser.getAccessKey();
        // 起初sk是空的（后端内部自己流通生成的）
        String secretKey = loginUser.getSecretKey();
        // 用户自己的ak 和 sk (在转发到网关项目接口携带ak、sk)
        LouisApiClient tempClient = new LouisApiClient(accessKey,secretKey);
        // json 和 gson
        Gson gson = new Gson();
        // 将前端传来的json格式的字符串转化为Java对象
        // 保证前端传来name和后端中对象的属性name要保持一致 否则解析赋值就会null
        // 获取前端用户输入的json格式的字符串
        String requestParams = interfaceInfoInvoke.getUserRequestParams();
        InterfaceInfo targetInterfaceInfo = interfaceInfoService.getById(id);
        // 判断是否需要传参(todo 详细校验传参类型)
        if(targetInterfaceInfo.getRequestParams().isEmpty() && requestParams != null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        String methodName = targetInterfaceInfo.getName();
        com.louis.louisapiclientsdk.model.User user = gson.fromJson(requestParams, com.louis.louisapiclientsdk.model.User.class);
        // 调用自己封装的sdk（专门存放接口的sdk）
        // 执行这一个调用sdk方法 跳转到网关（做两个项目interfaceInfo和backend的统一校验和业务逻辑处理）
        String res = "";
        if(user == null){
            // 无传参类型接口1——Bing7Pictures
            if(methodName.contains("getBingOneDay7Pictures")){
                res = tempClient.getBingOneDay7Pictures();
                ObjectMapper objectMapper = new ObjectMapper();
                try{
                    JsonNode jsonNode = objectMapper.readTree(res);
                    // 现在，您可以使用jsonNode对象访问JSON的各个部分
                    // 例如，获取result数组
                    JsonNode resultNode = jsonNode.get("result");
                    List<ImageResult> resultList = objectMapper.convertValue(resultNode, new TypeReference<List<ImageResult>>() {});
                    return ResultUtils.success(resultList);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            // todo ...多个无参接口
            // if(method.contains("getXXX1")){
            //     res = tempClient.getXXX1();
            // }
            // if(method.contains("getXXX2")){
            //     res = tempClient.getXXX2();
            // }
            // if(method.contains("getXXX3")){
            //     res = tempClient.getXXX3();
            // }

        }
        else{
            // 有传参类型接口(todo 如何判断有参的不同接口呢？？？)
            res = tempClient.getUsernameByPost(user);
        }

        // todo 不能只是返回字符串数据（显得data很冗长，不美观）
        return ResultUtils.success(res);

    }
    @GetMapping("/getBingOneDay7Pictures")
    @Deprecated
    public BaseResponse<Object> getBingOneDay7Pictures(@RequestBody InterfaceInfoInvoke interfaceInfoInvoke,HttpServletRequest request) throws Exception {
        if(interfaceInfoInvoke.getId() == null || interfaceInfoInvoke.getId() <= 0){
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        User loginUser = userService.getLoginUser(request);
        Long userId = loginUser.getId();
        Long id = interfaceInfoInvoke.getId();
        // 添加用户接口信息
        saveUserInterfaceInfo(userId,id);
        String accessKey = loginUser.getAccessKey();
        // 起初sk是空的（后端内部自己流通生成的）
        String secretKey = loginUser.getSecretKey();
        // 用户自己的ak 和 sk (在转发到网关项目接口携带ak、sk)
        LouisApiClient tempClient = new LouisApiClient(accessKey,secretKey);
        // （1）通过sdk客户端项目将请求发送到网关项目
        // （2）网关校验请求合法性（过滤）
        // （3）结束后，调用接口项目接口 （接口项目返回的数据可以进行封装）
        // （4）网关拿到接口项目返回的数据，将其字符串化
        // （5）最后返回给backend
        // （6）backend将json格式的字符串转换成Java对象
        // （7）返回给前端
        String jsonStr = tempClient.getBingOneDay7Pictures();
        // json格式的字符串转换成Java对象
        Gson gson = new Gson();
        BaseResult baseResult = gson.fromJson(jsonStr, BaseResult.class);
        return ResultUtils.success(jsonStr);
    }
    @Transactional
    public void saveUserInterfaceInfo(Long userId,Long id){
        QueryWrapper<UserInterfaceInfo> userInterfaceInfoQW = new QueryWrapper<>();
        userInterfaceInfoQW.eq("userId",userId).eq("interfaceInfoId",id);
        UserInterfaceInfo userInterfaceInfo = userInterfaceInfoService.getOne(userInterfaceInfoQW);
        if(userInterfaceInfo == null){
            UserInterfaceInfo addUserInterfaceInfo = new UserInterfaceInfo();
            addUserInterfaceInfo.setUserId(userId);
            // 初始化调用剩余次数为10
            // 调用总次数为0
            addUserInterfaceInfo.setTotalNum(0);
            addUserInterfaceInfo.setLeftNum(10);
            addUserInterfaceInfo.setInterfaceInfoId(id);
            userInterfaceInfoService.save(addUserInterfaceInfo);
        }
    }

    /**
     * 根据 id 获取
     * @param id
     * @return
     */
    @PostMapping("/getInterfaceInfo")
    public BaseResponse<InterfaceInfo> getInterfaceInfoById(long id, HttpServletRequest request) {
        if (id <= 0) {
            throw new BusinessException(ErrorCode.PARAMS_ERROR);
        }
        InterfaceInfo interfaceInfo = interfaceInfoService.getById(id);
        if (interfaceInfo == null) {
            throw new BusinessException(ErrorCode.NOT_FOUND_ERROR);
        }
        return ResultUtils.success(interfaceInfo);
    }
//
//    /**
//     * 分页获取列表（封装类）
//     *
//     * @param interfaceInfoQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/list/page/vo")
//    public BaseResponse<Page<InterfaceInfoVO>> listinterfaceInfoVOByPage(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest,
//            HttpServletRequest request) {
//        long current = interfaceInfoQueryRequest.getCurrent();
//        long size = interfaceInfoQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<interfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size),
//                interfaceInfoService.getQueryWrapper(interfaceInfoQueryRequest));
//        return ResultUtils.success(interfaceInfoService.getinterfaceInfoVOPage(interfaceInfoPage, request));
//    }
//
//    /**
//     * 分页获取当前用户创建的资源列表
//     *
//     * @param interfaceInfoQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/my/list/page/vo")
//    public BaseResponse<Page<InterfaceInfoVO>> listMyinterfaceInfoVOByPage(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest,
//            HttpServletRequest request) {
//        if (interfaceInfoQueryRequest == null) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        User loginUser = userService.getLoginUser(request);
//        interfaceInfoQueryRequest.setUserId(loginUser.getId());
//        long current = interfaceInfoQueryRequest.getCurrent();
//        long size = interfaceInfoQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.page(new Page<>(current, size),
//                interfaceInfoService.getQueryWrapper(interfaceInfoQueryRequest));
//        return ResultUtils.success(interfaceInfoService.getInterfaceInfoVOPage(interfaceInfoPage, request));
//    }
//
//    // endregion
//
//    /**
//     * 分页搜索（从 ES 查询，封装类）
//     *
//     * @param interfaceInfoQueryRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/search/page/vo")
//    public BaseResponse<Page<InterfaceInfoVO>> searchinterfaceInfoVOByPage(@RequestBody InterfaceInfoQueryRequest interfaceInfoQueryRequest,
//            HttpServletRequest request) {
//        long size = interfaceInfoQueryRequest.getPageSize();
//        // 限制爬虫
//        ThrowUtils.throwIf(size > 20, ErrorCode.PARAMS_ERROR);
//        Page<InterfaceInfo> interfaceInfoPage = interfaceInfoService.searchFromEs(interfaceInfoQueryRequest);
//        return ResultUtils.success(interfaceInfoService.getinterfaceInfoVOPage(interfaceInfoPage, request));
//    }
//
//    /**
//     * 编辑（用户）
//     *
//     * @param interfaceInfoEditRequest
//     * @param request
//     * @return
//     */
//    @PostMapping("/edit")
//    public BaseResponse<Boolean> editinterfaceInfo(@RequestBody InterfaceInfoEditRequest interfaceInfoEditRequest, HttpServletRequest request) {
//        if (interfaceInfoEditRequest == null || interfaceInfoEditRequest.getId() <= 0) {
//            throw new BusinessException(ErrorCode.PARAMS_ERROR);
//        }
//        InterfaceInfo interfaceInfo = new InterfaceInfo();
//        BeanUtils.copyProperties(interfaceInfoEditRequest, interfaceInfo);
//        List<String> tags = interfaceInfoEditRequest.getTags();
//        if (tags != null) {
//            interfaceInfo.setTags(GSON.toJson(tags));
//        }
//        // 参数校验
//        interfaceInfoService.validInterfaceInfo(interfaceInfo, false);
//        User loginUser = userService.getLoginUser(request);
//        long id = interfaceInfoEditRequest.getId();
//        // 判断是否存在
//        interfaceInfo oldinterfaceInfo = interfaceInfoService.getById(id);
//        ThrowUtils.throwIf(oldinterfaceInfo == null, ErrorCode.NOT_FOUND_ERROR);
//        // 仅本人或管理员可编辑
//        if (!oldinterfaceInfo.getUserId().equals(loginUser.getId()) && !userService.isAdmin(loginUser)) {
//            throw new BusinessException(ErrorCode.NO_AUTH_ERROR);
//        }
//        boolean result = interfaceInfoService.updateById(interfaceInfo);
//        return ResultUtils.success(result);
//    }

}
